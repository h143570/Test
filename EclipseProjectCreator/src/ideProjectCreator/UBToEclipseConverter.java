package ideProjectCreator;

import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;
import ideProjectCreator.explorer.ub.UBEnabledApplicationStrucutreExplorer;
import ideProjectCreator.generator.projectStructure.ProjectStructureGenerator;
import ideProjectCreator.generator.projectStructure.ub.UBEnabledEclipseProjectStructureGenerator;
import ideProjectCreator.persistency.ProjectStructurePersister;
import ideProjectCreator.persistency.eclipse.EclipseFileBasedProjectStructurePersister;

import java.util.List;

public class UBToEclipseConverter {

    private UBEnabledApplicationStrucutreExplorer<UBApplicationStructureDescriptor<UBModuleDescriptor>>                  explorer;
    private ProjectStructureGenerator<UBApplicationStructureDescriptor<UBModuleDescriptor>, EclipseIDEProjectDescriptor> generator;
    private ProjectStructurePersister<EclipseIDEProjectDescriptor>                                                       persister;

    public void convert(String... args) {
        explorer = new UBEnabledApplicationStrucutreExplorer<UBApplicationStructureDescriptor<UBModuleDescriptor>>();
        generator = new UBEnabledEclipseProjectStructureGenerator<UBApplicationStructureDescriptor<UBModuleDescriptor>, EclipseIDEProjectDescriptor>();
        persister = new EclipseFileBasedProjectStructurePersister<EclipseIDEProjectDescriptor>();

        UBApplicationStructureDescriptor<UBModuleDescriptor> parsedApplicationStrucutre = explorer.parseApplicationStrucutre(args[0]);
        List<EclipseIDEProjectDescriptor> generatedProjects = generator.createProjectStrucuture(parsedApplicationStrucutre);

        for (EclipseIDEProjectDescriptor ideProjectDescriptor : generatedProjects) {
            persister.persistIDEProject(ideProjectDescriptor);
        }

    }

}
