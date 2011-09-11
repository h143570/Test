package ideProjektCreator.explorer.ub;

import ideProjektCreator.domain.application.ApplicationStructureDescriptor;
import ideProjektCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjektCreator.domain.module.ub.UBModuleDescriptor;
import ideProjektCreator.explorer.ApplicationStructureExplorer;

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
