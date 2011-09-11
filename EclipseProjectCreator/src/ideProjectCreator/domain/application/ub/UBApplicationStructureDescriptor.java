package ideProjectCreator.domain.application.ub;

import ideProjectCreator.domain.BuildSystemType;
import ideProjectCreator.domain.application.ApplicationStructureDescriptor;
import ideProjectCreator.domain.module.ModuleDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;

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
