package ideProjektCreator.generator.projectStructure.ub;

import ideProjektCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjektCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjektCreator.domain.module.ub.UBModuleDescriptor;
import ideProjektCreator.generator.projectStructure.ProjectStructureGenerator;

import java.util.List;

public class UBEnabledEclipseProjectStructureGenerator<A extends UBApplicationStructureDescriptor<UBModuleDescriptor>, P extends EclipseIDEProjectDescriptor>
    implements ProjectStructureGenerator<A, P> {

    @Override
    public List<P> createProjectStrucuture(A applicationStrucutreDescriptor) {
        // TODO Auto-generated method stub
        return null;
    }

}
