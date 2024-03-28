package de.e_nexus;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.karaf.main.Main;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.launch.Framework;
import org.springframework.context.ApplicationContext;

/**
 * Initializes and starts Karaf.
 */
@Named
public class KarafInitializer {
    /**
     * The logger for this class.
     */
    private static final Logger LOG = Logger
            .getLogger(KarafInitializer.class.getCanonicalName());

    /**
     * The random value generator.
     */
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * The contributors for the configuration of Karaf.
     */
    @Inject
    private final FrameworkConfigContributor[] configurationContributor = null;

    /**
     * The finder for the local repository of Maven.
     */
    @Inject
    private final RepositoryFinder finder = null;

    /**
     * The application Context.
     */
    @Inject
    private final ApplicationContext appCtx = null;

    /**
     * All listeners that wait for status-change of Karaf.
     */
    @Inject
    private final Optional<FrameworkListener[]> frameworkListeners = null;
    /**
     * The main Karaf-instance.
     */
    private volatile Main karaf = null;

    /**
     * Checks if Karaf is up and running.
     *
     * <p>
     * If Karaf is currently starting and as long as Karaf is not compleatly
     * stopped, this method returns <code>true</code>.
     *
     * @return <code>true</code> if Karaf is running, <code>false</code>
     *         otherwise
     */
    public boolean isRunning() {
        return karaf != null && getFramework() != null
                && getFramework().getState() == Framework.ACTIVE;
    }

    /**
     * Stops Karaf.
     * <p>
     * Wait until Karaf is completely stopped.
     *
     * @throws Exception If the shutdown did not work properly
     */
    public void stop() throws Exception {
        Main shutdownKaraf = karaf;
        synchronized (this) {
            if (isRunning()) {
                karaf = null;
            }
        }
        if (shutdownKaraf != null) {
            try {
                shutdownKaraf.destroy();
                shutdownKaraf.awaitShutdown();
            } catch (Exception e) {
                karaf = shutdownKaraf;
                throw e;
            }
        }
    }

    /**
     * Initializes Karaf at start.
     *
     * @throws IOException If the storage is broken or exceeded
     * @throws Exception   If karaf could not be started but also not stopped
     */
    @PostConstruct
    public void start() throws IOException, Exception {
        if (isRunning()) {
            LOG.warning("Can not start! Preconditions failed!");
            return;
        }
        File temp = calcTempDir();
        File tmpKaraf = new File(temp, "rm-karaf");
        while (tmpKaraf.exists()) {
            tmpKaraf = new File(temp,
                    "rm-karaf-" + Long.toHexString(RANDOM.nextLong()));
        }
        if (!tmpKaraf.mkdirs()) {
            LOG.warning("Could not create directories: '" + tmpKaraf
                    + "'! Cant start karaf.");
        } else {
            tmpKaraf.deleteOnExit();
            File etc = new File(tmpKaraf, "etc");
            if (!etc.mkdir()) {
                LOG.warning("Could not create etc-directory: '" + etc
                        + "'! Cant start karaf.");
            } else {
                File system = new File(tmpKaraf, "system");
                if (!system.mkdir()) {
                    LOG.warning("Could not create system-directory: '" + system
                            + "'! Cant start karaf.");
                } else {
                    File lib = new File(tmpKaraf, "lib");
                    if (!lib.mkdir()) {
                        LOG.warning("Could not create lib-directory: '" + lib
                                + "'! Cant start karaf.");
                    } else {
                        File data = new File(tmpKaraf, "data");
                        if (!data.mkdir()) {
                            LOG.warning("Could not create data-directory: '"
                                    + data + "'! Cant start karaf.");
                        } else {
                            execute(tmpKaraf, etc);
                        }
                    }
                }
            }
        }
    }

    /**
     * Return where to find the temporary directory.
     *
     * @return The temp folder, never <code>null</code>
     */
    private File calcTempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    /**
     * Starts the execution of Karaf.
     * <p>
     * If the execution is not possible, try to stop it again.
     *
     * @param tmpKaraf The temporary folder for this Karaf instance, never
     *                 <code>null</code>
     * @param etc      The etc-folder in the temporary Karaf, never
     *                 <code>null</code>
     * @throws IOException If a storage problem occurs
     * @throws Exception   If Karaf could not be started and not be stopped.
     */
    private void execute(final File tmpKaraf, final File etc)
            throws IOException, Exception {
        Properties config = new Properties();
        Properties startup = new Properties();
        Properties users = new Properties();
        Properties paxurl = new Properties();
        Properties features = new Properties();

        for (FrameworkConfigContributor contrib : configurationContributor) {
            contrib.contributeKarafConfiguration(config, startup, features,
                    paxurl, users);
        }
        storeProperties(config, new File(etc, "config.properties"));
        storeProperties(startup, new File(etc, "startup.properties"));
        storeProperties(users, new File(etc, "users.properties"));
        storeProperties(paxurl, new File(etc, "org.ops4j.pax.url.mvn.cfg"));
        storeProperties(features,
                new File(etc, "org.apache.karaf.features.cfg"));

        Path repoPath = tmpKaraf.toPath().relativize(finder.getRepository());
        System.setProperty("karaf.default.repository", repoPath.toString());
        System.setProperty("karaf.home", tmpKaraf.toString());
        if (isRunning()) {
            return;
        }
        karaf = new Main(new String[0]);
        try {
            karaf.launch();
            Framework framework = getFramework();
            BundleContext ctx = framework.getBundleContext();
            for (FrameworkListener listener : frameworkListeners
                    .orElse(new FrameworkListener[0])) {
                ctx.addFrameworkListener(listener);
            }
        } catch (Throwable ex) {
            LOG.log(Level.SEVERE, "Can not start Karaf.", ex);
            karaf.destroy();
            karaf.setExitCode(-1);
        }
    }

    /**
     * Stores the properties file to the filesystem.
     *
     * @param config The properties, never <code>null</code>
     * @param file   The designated target, never <code>null</code>
     * @throws IOException If an storage problem occurred
     */
    private static void storeProperties(final Properties config,
            final File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            config.store(fos, null);
        } catch (FileNotFoundException e) {
            throw new IOException(e);
        }
    }

    /**
     * Load a osgi-bundle-jar into Karaf using an specific location.
     *
     * @param byteArray The bytes of the osgi-bundle-jar, never
     *                  <code>null</code>
     * @param location  The location-string, might be a URL, never
     *                  <code>null</code>
     * @throws BundleException If the jar-bytes is not an osgi-bundle
     */
    public void loadBundle(final byte[] byteArray, final String location)
            throws BundleException {
        Framework f = getFramework();
        f.getBundleContext().installBundle(location,
                new ByteArrayInputStream(byteArray));
    }

    /**
     * Removes a bundle from Karaf.
     * <p>
     * If the bundle is not yet stopped, stop it first.
     *
     * @param location The location used to install that bundle, never
     *                 <code>null</code>
     * @return <code>true</code> if the bundle was found and stopped,
     *         <code>false</code> otherwise
     * @throws BundleException If too many bundles was found by the location or
     *                         the bundle could not be removed
     */
    public boolean remove(final String location) throws BundleException {
        Bundle bundle = findBundleByLocation(location);
        if (bundle != null) {
            bundle.stop();
            bundle.uninstall();
            return true;
        }
        return false;
    }

    /**
     * Starts the bundle.
     *
     * @param location The location that was used to install that bundle, never
     *                 <code>null</code>
     * @return <code>true</code> if the bundle has started, <code>false</code>
     *         if the bundle was not started and has been uninstalled
     * @throws BundleException If that bundle was not found
     */
    public boolean startBundle(final String location) throws BundleException {
        Bundle bundle = findBundleByLocation(location);
        try {
            bundle.start();
//            BundleActivator unwrap = ActivatorSupplier.unwrap(bundle);
//            if (unwrap instanceof Consumer) {
//                Consumer consumer = (Consumer) unwrap;
//                consumer.accept(appCtx);
//            }
            return true;
        } catch (Exception e) {
            bundle.uninstall();
            return false;
        }
    }

    /**
     * Find a bundle by its install location.
     *
     * @param location The location the bundle was installed with, never
     *                 <code>null</code>
     * @return The bundle or <code>null</code> if no bundle was found
     * @throws BundleException If multiple bundle was found by the location
     */
    private Bundle findBundleByLocation(final String location)
            throws BundleException {
        BundleContext bundleContext = getFramework().getBundleContext();
        Bundle[] bundles = bundleContext.getBundles();
        Set<Bundle> candidates = new LinkedHashSet<>();
        for (Bundle bundle : bundles) {
            if (bundle.getLocation().equals(location)) {
                candidates.add(bundle);
            }
        }
        if (candidates.isEmpty()) {
            return null;
        }
        if (candidates.size() != 1) {
            throw new BundleException(
                    "Cant find unique bundle with this location. Found: "
                            + candidates + ".");
        }

        return candidates.iterator().next();
    }

    /**
     * Return the framework.
     *
     * @return Either the framework or <code>null</code> if the framework has
     *         not yet been stared.
     */
    public Framework getFramework() {
        if (karaf == null) {
            return null;
        }
        return karaf.getFramework();
    }

}
