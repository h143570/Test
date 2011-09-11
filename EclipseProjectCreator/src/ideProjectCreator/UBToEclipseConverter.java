package ideProjectCreator;

import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;
import ideProjectCreator.explorer.ApplicationStructureExplorer;
import ideProjectCreator.explorer.ub.UBEnabledApplicationStrucutreExplorer;
import ideProjectCreator.generator.projectStructure.ProjectStructureGenerator;
import ideProjectCreator.generator.projectStructure.ub.UBEnabledEclipseProjectStructureGenerator;
import ideProjectCreator.persistency.ProjectStructurePersister;
import ideProjectCreator.persistency.eclipse.EclipseFileBasedProjectStructurePersister;

import java.util.List;

public class UBToEclipseConverter<A extends UBApplicationStructureDescriptor<UBModuleDescriptor>, P extends EclipseIDEProjectDescriptor> {

    private ApplicationStructureExplorer<A> explorer;
    private ProjectStructureGenerator<A, P> generator;
    private ProjectStructurePersister<P>    persister;

    public void convert(String... args) {
        explorer = new UBEnabledApplicationStrucutreExplorer<A>();
        generator = new UBEnabledEclipseProjectStructureGenerator<A, P>();
        persister = new EclipseFileBasedProjectStructurePersister<P>();

        A parsedApplicationStrucutre = explorer.parseApplicationStrucutre(args[0]);
        List<P> generatedProjects = generator.createProjectStrucuture(parsedApplicationStrucutre);

        for (P ideProjectDescriptor : generatedProjects) {
            persister.persistIDEProject(ideProjectDescriptor);
        }

    }

}
