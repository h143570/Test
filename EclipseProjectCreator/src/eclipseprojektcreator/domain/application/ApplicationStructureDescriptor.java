package eclipseprojektcreator.domain.application;

import java.util.LinkedHashMap;
import java.util.Map;

import eclipseprojektcreator.domain.BuildSystemType;
import eclipseprojektcreator.domain.module.ModuleDescriptor;

/**
 * Describes an Application structure.
 *
 * Each Application can contain one or more {@link ModuleDescriptor}.
 *
 * @param <T> A compatible {@link ModuleDescriptor}
 *
 * @author Cobalt
 *
 */
public class ApplicationStructureDescriptor<T extends ModuleDescriptor> {

    protected BuildSystemType buildSystemType;
    protected String          fullPath;
    protected Map<String, T>  modules = new LinkedHashMap<String, T>();
    protected String          libPath;
    protected String          testLibPath;

    public ApplicationStructureDescriptor(String fullPath) {
        this.fullPath = fullPath;
    }

    public void addModule(T module) {
        modules.put(module.getName(), module);
    }

    public BuildSystemType getBuildSystemType() {
        return buildSystemType;
    }

    public String getFullPath() {
        return fullPath;
    }

    public Map<String, T> getModules() {
        return modules;
    }

    public String getLibPath() {
        return libPath;
    }

    public void setLibPath(String libPath) {
        this.libPath = libPath;
    }

    public String getTestLibPath() {
        return testLibPath;
    }

    public void setTestLibPath(String testLibPath) {
        this.testLibPath = testLibPath;
    }

}
