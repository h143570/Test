package ideProjectCreator.domain.module.ub;

import ideProjectCreator.domain.module.ModuleDescriptor;

/**
 * UB Specific additional module information.
 *
 * @author Cobalt
 *
 */
public class UBModuleDescriptor extends ModuleDescriptor {

    protected boolean ivyConfigPresent;
    protected boolean mainModule;
    protected boolean webModule;
    protected boolean staticWebModule;
    protected boolean checkStylePresent;

    public boolean isIvyConfigPresent() {
        return ivyConfigPresent;
    }

    public void setIvyConfigPresent(boolean ivyConfigPresent) {
        this.ivyConfigPresent = ivyConfigPresent;
    }

    public boolean isMainModule() {
        return mainModule;
    }

    public void setMainModule(boolean mainModule) {
        this.mainModule = mainModule;
    }

    public boolean isWebModule() {
        return webModule;
    }

    public void setWebModule(boolean webModule) {
        this.webModule = webModule;
    }

    public boolean isStaticWebModule() {
        return staticWebModule;
    }

    public void setStaticWebModule(boolean staticWebModule) {
        this.staticWebModule = staticWebModule;
    }

    public boolean isCheckStylePresent() {
        return checkStylePresent;
    }

    public void setCheckStylePresent(boolean checkStylePresent) {
        this.checkStylePresent = checkStylePresent;
    }

    @Override
    public String toString() {
        return "UBModuleDescriptor [ivyConfigPresent=" + ivyConfigPresent + ", mainModule=" + mainModule + ", webModule=" + webModule
                + ", staticWebModule=" + staticWebModule + ", checkStylePresent=" + checkStylePresent + ", name=" + name + ", relativePath="
                + relativePath + ", srcPresent=" + srcPresent + ", resourcesPresent=" + resourcesPresent + ", testPresent=" + testPresent + "]";
    }

}
