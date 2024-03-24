package de.e_nexus;

import java.util.Properties;

public interface FrameworkConfigContributor {
    /**
     * Modify configuration before Karaf starts.
     *
     * @param config   The basic configuration, never <code>null</code>
     * @param startup  The starup configuration, never <code>null</code>
     * @param features The features, never <code>null</code>
     * @param paxurl   The url for pax, never <code>null</code>
     * @param users    The users that may log-in, never <code>null</code>
     */
    void contributeKarafConfiguration(Properties config, Properties startup,
            Properties features, Properties paxurl, Properties users);

    /**
     * Joins values using a separator.
     *
     * @param separator The separator, never <code>null</code>
     * @param strings   The values, never <code>null</code>
     * @return The joined values
     */
    default String join(final String separator, final String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(separator);
            sb.append(string);
        }
        return sb.substring(separator.length());
    }
}
