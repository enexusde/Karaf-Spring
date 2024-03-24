package de.e_nexus;

import java.util.Properties;

import javax.inject.Named;

@Named
public class PaxKarafConfig implements FrameworkConfigContributor {
    public static final String PREFIX = "org.ops4j.pax.url.mvn.";

    @Override
    public void contributeKarafConfiguration(final Properties config,
            final Properties startup, final Properties features,
            final Properties paxurl, final Properties users) {

        paxurl.setProperty(PREFIX + "connection.bufferSize", "8192");
        paxurl.setProperty(PREFIX + "socket.readTimeout", "30000");
        paxurl.setProperty(PREFIX + "connection.retryCount", "3");
        paxurl.setProperty(PREFIX + "socket.reuseAddress", "false");
        paxurl.setProperty(PREFIX + "socket.connectionTimeout", "5000");
        paxurl.setProperty(PREFIX + "socket.keepAlive", "false");
        paxurl.setProperty(PREFIX + "timeout", "5000");
        paxurl.setProperty(PREFIX + "defaultRepositories",
                "${karaf.home.uri}${karaf.default.repository}@id=system.repository@snapshots, ${karaf.data.uri}kar@id=kar.repository@multi@snapshots, ${karaf.base.uri}${karaf.default.repository}@id=child.system.repository@snapshots");
        paxurl.setProperty(PREFIX + "socket.tcpNoDelay", "true");
        paxurl.setProperty(PREFIX + "certificateCheck", "true");
        paxurl.setProperty(PREFIX + "repositories",
                "https://repo1.maven.org/maven2@id=central, https://repository.apache.org/content/groups/snapshots-group@id=apache@snapshots@noreleases, https://oss.sonatype.org/content/repositories/ops4j-snapshots@id=ops4j.sonatype.snapshots.deploy@snapshots@noreleases");
        paxurl.setProperty(PREFIX + "useFallbackRepositories", "false");
        paxurl.setProperty(PREFIX + "socket.linger", "-1");
    }

}
