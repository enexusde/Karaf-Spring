package de.e_nexus;

import java.util.Properties;

import javax.inject.Named;

@Named
public class UserKarafConfig implements FrameworkConfigContributor {
    /**
     * Configures the users that can login into Karaf throu the shell or remote.
     */
    @Override
    public void contributeKarafConfiguration(final Properties config,
            final Properties startup, final Properties features,
            final Properties paxurl, final Properties users) {
        users.setProperty("karaf", "karaf,_g_:admingroup");
        users.setProperty("_g_:admingroup",
                "group,admin,manager,viewer,systembundles,ssh");
    }

}
