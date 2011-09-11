package ideProjectCreator.generator.project.ub;

import ideProjectCreator.domain.application.ApplicationStructureDescriptor;
import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.IDEProjectDescriptor;
import ideProjectCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjectCreator.domain.module.ModuleDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;
import ideProjectCreator.generator.project.ProjectGenerator;

public class UBEclipseProjectGenerator<A extends UBApplicationStructureDescriptor<M>, M extends UBModuleDescriptor, P extends EclipseIDEProjectDescriptor>
    implements ProjectGenerator<ApplicationStructureDescriptor<ModuleDescriptor>, ModuleDescriptor, IDEProjectDescriptor> {

}
