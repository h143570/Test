package eclipseprojektcreator;

import eclipseprojektcreator.domain.application.ApplicationStructureDescriptor;
import eclipseprojektcreator.domain.application.ub.UBApplicationStrucutreDescriptor;
import eclipseprojektcreator.domain.module.ModuleDescriptor;
import eclipseprojektcreator.domain.module.ub.UBModuleDescriptor;
import eclipseprojektcreator.explorer.ApplicationStructureExplorer;
import eclipseprojektcreator.explorer.UBEnabledApplicationStrucutreExplorer;
import eclipseprojektcreator.generator.projectStructure.ProjectStructureGenerator;
import eclipseprojektcreator.generator.projectStructure.ub.UBEnabledProjectStructureGenerator;

public class EclipseProjectCreator {

    private static ApplicationStructureExplorer<ApplicationStructureDescriptor<ModuleDescriptor>> explorer;
    private static ProjectStructureGenerator<ApplicationStructureDescriptor<ModuleDescriptor>>    generator;

    public static void main(String... args) {

        explorer = new UBEnabledApplicationStrucutreExplorer<UBApplicationStrucutreDescriptor<UBModuleDescriptor>>();
        generator = new UBEnabledProjectStructureGenerator<UBApplicationStrucutreDescriptor<UBModuleDescriptor>>();
    }

}
