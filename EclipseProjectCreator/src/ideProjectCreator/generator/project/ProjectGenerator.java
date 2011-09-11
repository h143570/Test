package ideProjectCreator.generator.project;

import ideProjectCreator.domain.application.ApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.IDEProjectDescriptor;
import ideProjectCreator.domain.module.ModuleDescriptor;

public interface ProjectGenerator<A extends ApplicationStructureDescriptor<M>, M extends ModuleDescriptor, P extends IDEProjectDescriptor> {

}
