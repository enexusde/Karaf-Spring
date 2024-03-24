package de.e_nexus;

import java.nio.file.Path;

/**
 * Guides the Karaf setup where to find the local .m2/repository-Folder.
 */
public interface RepositoryFinder {

    /**
     * Returns the local .m2/repository-Folder.
     *
     * @return The local .m2/repository-Folder
     */
    Path getRepository();

}
