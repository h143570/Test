package eclipseprojektcreator.explorer;

import eclipseprojektcreator.domain.application.ApplicationStructureDescriptor;
import eclipseprojektcreator.domain.module.ModuleDescriptor;

/**
 * Parses the directory structure to attempt to identify the application structure.
 *
 *
 * @param <T> A compatible {@link ApplicationStructureDescriptor}
 *
 * @author Cobalt
 *
 */
public interface ApplicationStructureExplorer<T extends ApplicationStructureDescriptor<ModuleDescriptor>> {

}
