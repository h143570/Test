package ideProjectCreator.persistency;

import ideProjectCreator.domain.ide.IDEProjectDescriptor;

public interface ProjectStructurePersister<P extends IDEProjectDescriptor> {

    /**
     * Persist a suitable {@link IDEProjectDescriptor}.
     *
     * @param ideProjectDescriptor a {@link IDEProjectDescriptor}
     *
     * @return true if {@link IDEProjectDescriptor} was successfully persisted.
     */
    boolean persistIDEProject(P ideProjectDescriptor);

}
