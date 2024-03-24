package de.e_nexus;

import java.io.File;
import java.nio.file.Path;

import javax.inject.Named;

/**
 * Returns the users .m2-repository.
 */
@Named
public class DefaultHomeRepository implements RepositoryFinder {

    /**
     * Points directly to the maven's default repository-folder.
     */
    @Override
    public Path getRepository() {
        File home = new File(System.getProperty("user.home"));
        return new File(home, ".m2/repository").toPath();
    }

}
