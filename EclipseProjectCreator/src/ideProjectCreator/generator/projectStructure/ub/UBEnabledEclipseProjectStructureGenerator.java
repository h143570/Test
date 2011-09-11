package ideProjectCreator.generator.projectStructure.ub;

import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;
import ideProjectCreator.generator.projectStructure.ProjectStructureGenerator;

import java.util.ArrayList;
import java.util.List;

public class UBEnabledEclipseProjectStructureGenerator<A extends UBApplicationStructureDescriptor<UBModuleDescriptor>, P extends EclipseIDEProjectDescriptor>
    implements ProjectStructureGenerator<A, P> {

    @Override
    public List<P> createProjectStrucuture(A applicationStrucutreDescriptor) {
        List<P> result = new ArrayList<P>();
        return result;
    }

}
