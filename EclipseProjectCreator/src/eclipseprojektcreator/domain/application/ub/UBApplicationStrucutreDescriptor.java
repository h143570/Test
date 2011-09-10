package eclipseprojektcreator.domain.application.ub;

import eclipseprojektcreator.domain.BuildSystemType;
import eclipseprojektcreator.domain.application.ApplicationStructureDescriptor;
import eclipseprojektcreator.domain.module.ub.UBModuleDescriptor;

public class UBApplicationStrucutreDescriptor<T extends UBModuleDescriptor> extends ApplicationStructureDescriptor<T> {

    protected UBModuleDescriptor mainModule;

    public UBApplicationStrucutreDescriptor(String fullPath) {
        super(fullPath);
        buildSystemType = BuildSystemType.UB;
    }

    public UBModuleDescriptor getMainModule() {
        return mainModule;
    }

    public void setMainModule(UBModuleDescriptor mainModule) {
        this.mainModule = mainModule;
    }

}
