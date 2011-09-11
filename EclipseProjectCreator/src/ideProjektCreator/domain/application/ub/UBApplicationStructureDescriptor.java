package ideProjektCreator.domain.application.ub;

import ideProjektCreator.domain.BuildSystemType;
import ideProjektCreator.domain.application.ApplicationStructureDescriptor;
import ideProjektCreator.domain.module.ModuleDescriptor;
import ideProjektCreator.domain.module.ub.UBModuleDescriptor;

public class UBApplicationStructureDescriptor<M extends UBModuleDescriptor> extends ApplicationStructureDescriptor<ModuleDescriptor> {

    protected M mainModule;

    public UBApplicationStructureDescriptor(String fullPath) {
        super(fullPath);
        buildSystemType = BuildSystemType.UB;
    }

    public M getMainModule() {
        return mainModule;
    }

    public void setMainModule(M mainModule) {
        this.mainModule = mainModule;
    }

}
