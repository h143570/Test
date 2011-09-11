package ideProjektCreator.domain.module;

/**
 * Describes a module in the application.
 *
 * Describes the minimal set of information gathered about a module.
 *
 * @author Cobalt
 *
 */
public class ModuleDescriptor {

    protected String  name;
    protected String  relativePath;
    protected boolean srcPresent;
    protected boolean resourcesPresent;
    protected boolean testPresent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public boolean isSrcPresent() {
        return srcPresent;
    }

    public void setSrcPresent(boolean srcPresent) {
        this.srcPresent = srcPresent;
    }

    public boolean isResourcesPresent() {
        return resourcesPresent;
    }

    public void setResourcesPresent(boolean resourcesPresent) {
        this.resourcesPresent = resourcesPresent;
    }

    public boolean isTestPresent() {
        return testPresent;
    }

    public void setTestPresent(boolean testPresent) {
        this.testPresent = testPresent;
    }

}
