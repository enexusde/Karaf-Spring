package de.e_nexus;

import java.util.Properties;

import javax.inject.Named;

@Named
public class StartupKarafConfig implements FrameworkConfigContributor {
    /**
     * Set the default installations that needs to be installed in order to have
     * a useable Karaf-Container.
     */
    @Override
    public void contributeKarafConfiguration(final Properties config,
            final Properties s, final Properties features,
            final Properties paxurl, final Properties users) {
        s.setProperty(join("/", "mvn:org.apache.felix",
                "org.apache.felix.fileinstall", "3.7.4"), "12");
        s.setProperty(join("/", "mvn:org.apache.karaf.features",
                "org.apache.karaf.features.core", "4.4.5"), "15");
        s.setProperty(
                "mvn:org.apache.felix/org.apache.felix.configurator/1.0.16",
                "11");
        s.setProperty("mvn:org.apache.felix/org.apache.felix.converter/1.0.14",
                "9");
        s.setProperty(join("/", "mvn:org.apache.karaf.features",
                "org.apache.karaf.features.extension", "4.4.5"), "1");
        s.setProperty(
                "mvn:org.apache.sling/org.apache.sling.commons.johnzon/1.2.16",
                "11");
        s.setProperty(join("/", "mvn:org.apache.felix",
                "org.apache.felix.configadmin.plugin.interpolation", "1.2.8"),
                "11");
        s.setProperty(
                "mvn:org.apache.felix/org.apache.felix.configadmin/1.9.26",
                "10");
        s.setProperty(join("/", "mvn:org.apache.karaf.services",
                "org.apache.karaf.services.eventadmin", "4.4.5"), "5");
        s.setProperty("mvn:org.fusesource.jansi/jansi/2.4.1", "8");
        s.setProperty("mvn:org.ops4j.pax.url/pax-url-aether/2.6.14", "5");
        s.setProperty(join("/", "mvn:org.apache.karaf.config",
                "org.apache.karaf.config.core", "4.4.5"), "11");
        s.setProperty("mvn:org.osgi/org.osgi.util.function/1.2.0", "9");
        s.setProperty("mvn:org.ops4j.pax.logging/pax-logging-api/2.2.6", "8");
        s.setProperty("mvn:org.ops4j.pax.logging/pax-logging-log4j2/2.2.6",
                "8");
        s.setProperty("mvn:org.apache.felix/org.apache.felix.coordinator/1.0.2",
                "9");
        s.setProperty("mvn:org.osgi/org.osgi.util.promise/1.3.0", "9");
        s.setProperty("mvn:org.apache.felix/org.apache.felix.metatype/1.2.4",
                "9");
        s.setProperty("mvn:org.apache.felix/org.apache.felix.cm.json/1.0.6",
                "11");
    }

}
