package ideProjektCreator.generator.project;

import ideProjektCreator.domain.application.ApplicationStructureDescriptor;
import ideProjektCreator.domain.ide.IDEProjectDescriptor;
import ideProjektCreator.domain.module.ModuleDescriptor;

public interface ProjectGenerator<A extends ApplicationStructureDescriptor<M>, M extends ModuleDescriptor, P extends IDEProjectDescriptor> {

}
