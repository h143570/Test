package ideProjektCreator.explorer;

import ideProjektCreator.domain.application.ApplicationStructureDescriptor;
import ideProjektCreator.domain.module.ModuleDescriptor;

/**
 * Parses the directory structure to attempt to identify the application structure.
 *
 *
 * @param <A> A compatible {@link ApplicationStructureDescriptor}
 *
 * @author Cobalt
 *
 */
public interface ApplicationStructureExplorer<A extends ApplicationStructureDescriptor<ModuleDescriptor>> {

    /**
     * Parses the Application structure.
     *
     * @param fullPath the path of the project
     *
     * @return an applicable {@link ApplicationStructureDescriptor}
     *
     */
    A parseApplicationStrucutre(String fullPath);

}
