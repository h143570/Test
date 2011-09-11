package ideProjectCreator.explorer.ub;

import ideProjectCreator.domain.application.ApplicationStructureDescriptor;
import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;
import ideProjectCreator.explorer.ApplicationStructureExplorer;

/**
 * UB assumes that the application is organized into several modules.
 *
 *
 * @author Cobalt
 *
 * @param <A> A compatible {@link ApplicationStructureDescriptor}
 */
public class UBEnabledApplicationStrucutreExplorer<A extends UBApplicationStructureDescriptor<UBModuleDescriptor>> implements
    ApplicationStructureExplorer<A> {

    @Override
    public A parseApplicationStrucutre(String fullPath) {
        // TODO Auto-generated method stub
        return null;
    }

}
