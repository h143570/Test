package eclipseprojektcreator.domain.module.ub;

import eclipseprojektcreator.domain.module.ModuleDescriptor;

/**
 * UB Specific additional module information.
 *
 * @author Cobalt
 *
 */
public class UBModuleDescriptor extends ModuleDescriptor {

    protected String  dynamicWebContentRelativePath;
    protected String  staticWebcContentRelativePath;
    protected boolean ivyConfigPresent;
    protected boolean mainModule;
    protected boolean webModule;

    public String getDynamicWebContentRelativePath() {
        return dynamicWebContentRelativePath;
    }

    public void setDynamicWebContentRelativePath(String dynamicWebContentRelativePath) {
        this.dynamicWebContentRelativePath = dynamicWebContentRelativePath;
    }

    public String getStaticWebcContentRelativePath() {
        return staticWebcContentRelativePath;
    }

    public void setStaticWebcContentRelativePath(String staticWebcContentRelativePath) {
        this.staticWebcContentRelativePath = staticWebcContentRelativePath;
    }

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

}
