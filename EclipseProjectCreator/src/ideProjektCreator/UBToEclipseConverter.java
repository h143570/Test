package ideProjektCreator;

import ideProjektCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjektCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjektCreator.domain.module.ub.UBModuleDescriptor;
import ideProjektCreator.explorer.ApplicationStructureExplorer;
import ideProjektCreator.explorer.ub.UBEnabledApplicationStrucutreExplorer;
import ideProjektCreator.generator.projectStructure.ProjectStructureGenerator;
import ideProjektCreator.generator.projectStructure.ub.UBEnabledEclipseProjectStructureGenerator;
import ideProjektCreator.persistency.ProjectStructurePersister;
import ideProjektCreator.persistency.eclipse.EclipseFileBasedProjectStructurePersister;

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
