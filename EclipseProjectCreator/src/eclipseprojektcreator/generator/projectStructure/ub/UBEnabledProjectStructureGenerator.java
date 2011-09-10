package eclipseprojektcreator.generator.projectStructure.ub;

import eclipseprojektcreator.domain.application.ApplicationStructureDescriptor;
import eclipseprojektcreator.domain.application.ub.UBApplicationStrucutreDescriptor;
import eclipseprojektcreator.domain.module.ModuleDescriptor;
import eclipseprojektcreator.domain.module.ub.UBModuleDescriptor;
import eclipseprojektcreator.generator.projectStructure.ProjectStructureGenerator;

public class UBEnabledProjectStructureGenerator<T extends UBApplicationStrucutreDescriptor<UBModuleDescriptor>> implements
    ProjectStructureGenerator<ApplicationStructureDescriptor<ModuleDescriptor>> {

}
