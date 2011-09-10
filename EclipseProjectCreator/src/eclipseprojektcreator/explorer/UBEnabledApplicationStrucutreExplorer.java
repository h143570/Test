package eclipseprojektcreator.explorer;

import eclipseprojektcreator.domain.application.ApplicationStructureDescriptor;
import eclipseprojektcreator.domain.application.ub.UBApplicationStrucutreDescriptor;
import eclipseprojektcreator.domain.module.ModuleDescriptor;
import eclipseprojektcreator.domain.module.ub.UBModuleDescriptor;

/**
 * UB assumes that the application is organized into several modules.
 *
 *
 * @author Cobalt
 *
 * @param <T> A compatible {@link ApplicationStructureDescriptor}
 */
public class UBEnabledApplicationStrucutreExplorer<T extends UBApplicationStrucutreDescriptor<UBModuleDescriptor>> implements
    ApplicationStructureExplorer<ApplicationStructureDescriptor<ModuleDescriptor>> {

}
