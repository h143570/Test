package ideProjectCreator.generator.projectStructure;

import ideProjectCreator.domain.application.ApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.IDEProjectDescriptor;
import ideProjectCreator.domain.module.ModuleDescriptor;

import java.util.List;

public interface ProjectStructureGenerator<A extends ApplicationStructureDescriptor<ModuleDescriptor>, P extends IDEProjectDescriptor> {

    /**
     * Creates the list of {@link IDEProjectDescriptor} from the provided {@link ApplicationStructureDescriptor}.
     *
     * @param applicationStrucutreDescriptor an applicable {@link ApplicationStructureDescriptor}
     *
     * @return {@link IDEProjectDescriptor}
     */
    List<P> createProjectStrucuture(A applicationStrucutreDescriptor);

}
