package de.e_nexus;

import java.util.Properties;

import javax.inject.Named;

/**
 * Configuration of the basic features Karaf uses.
 */
@Named
public class FeaturesKarafConfig implements FrameworkConfigContributor {

    /**
     * Modify the configuration in order to provide Features for the
     * Karaf-Container.
     */
    @Override
    public void contributeKarafConfiguration(final Properties config,
            final Properties startup, final Properties features,
            final Properties paxurl, final Properties users) {
        StringBuilder repos = new StringBuilder();
        repos.append("mvn:org.apache.karaf.features/specs/4.4.5/xml/features");
        repos.append(",");
        repos.append(
                "mvn:org.apache.karaf.features/enterprise/4.4.5/xml/features");
        repos.append(",");
        repos.append(
                "mvn:org.apache.karaf.features/framework/4.4.5/xml/features");
        repos.append(",");
        repos.append(
                "mvn:org.apache.karaf.features/standard/4.4.5/xml/features");
        repos.append(",");
        repos.append("mvn:org.apache.karaf.features/spring/4.4.5/xml/features");
        features.setProperty("featuresRepositories", repos.toString());
        features.setProperty("autoRefresh", "true");
        features.setProperty("featuresBootAsynchronous", "false");

        StringBuilder featuresValue = new StringBuilder();
        featuresValue.append("instance/4.4.5");
        featuresValue.append(",");
        featuresValue.append("package/4.4.5");
        featuresValue.append(",");
        featuresValue.append("log/4.4.5");
        featuresValue.append(",");
        featuresValue.append("ssh/4.4.5");
        featuresValue.append(",");
        featuresValue.append("framework/4.4.5");
        featuresValue.append(",");
        featuresValue.append("system/4.4.5");
        featuresValue.append(",");
        featuresValue.append("eventadmin/4.4.5");
        featuresValue.append(",");
        featuresValue.append("feature/4.4.5");
        featuresValue.append(",");
        featuresValue.append("shell/4.4.5");
        featuresValue.append(",");
        featuresValue.append("management/4.4.5");
        featuresValue.append(",");
        featuresValue.append("service/4.4.5");
        featuresValue.append(",");
        featuresValue.append("jaas/4.4.5");
        featuresValue.append(",");
        featuresValue.append("deployer/4.4.5");
        featuresValue.append(",");
        featuresValue.append("diagnostic/4.4.5");
        featuresValue.append(",");
        featuresValue.append("wrap/2.6.14");
        featuresValue.append(",");
        featuresValue.append("bundle/4.4.5");
        featuresValue.append(",");
        featuresValue.append("config/4.4.5");
        featuresValue.append(",");
        featuresValue.append("kar/4.4.5");
        features.setProperty("featuresBoot", featuresValue.toString());
    }
}
