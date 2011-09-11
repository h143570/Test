package ideProjektCreator.domain.application;

import ideProjektCreator.domain.BuildSystemType;
import ideProjektCreator.domain.module.ModuleDescriptor;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class ApplicationStructureDescriptor<M extends ModuleDescriptor> {

    protected BuildSystemType buildSystemType;
    protected String          fullPath;
    protected Map<String, M>  modules = new LinkedHashMap<String, M>();
    protected String          libPath;
    protected String          testLibPath;

    public ApplicationStructureDescriptor(String fullPath) {
        this.fullPath = fullPath;
    }

    public void addModule(M module) {
        modules.put(module.getName(), module);
    }

    public BuildSystemType getBuildSystemType() {
        return buildSystemType;
    }

    public String getFullPath() {
        return fullPath;
    }

    public Map<String, M> getModules() {
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
