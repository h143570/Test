package ideProjektCreator.generator.project.ub;

import ideProjektCreator.domain.application.ApplicationStructureDescriptor;
import ideProjektCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjektCreator.domain.ide.IDEProjectDescriptor;
import ideProjektCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjektCreator.domain.module.ModuleDescriptor;
import ideProjektCreator.domain.module.ub.UBModuleDescriptor;
import ideProjektCreator.generator.project.ProjectGenerator;

public class UBEclipseProjectGenerator<A extends UBApplicationStructureDescriptor<M>, M extends UBModuleDescriptor, P extends EclipseIDEProjectDescriptor>
    implements ProjectGenerator<ApplicationStructureDescriptor<ModuleDescriptor>, ModuleDescriptor, IDEProjectDescriptor> {

}
