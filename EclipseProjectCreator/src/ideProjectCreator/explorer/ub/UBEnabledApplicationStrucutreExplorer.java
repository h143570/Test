package ideProjectCreator.explorer.ub;

import ideProjectCreator.domain.application.ApplicationStructureDescriptor;
import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;
import ideProjectCreator.explorer.ApplicationStructureExplorer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
    public UBApplicationStructureDescriptor<UBModuleDescriptor> parseApplicationStrucutre(String fullPath) {

        UBApplicationStructureDescriptor<UBModuleDescriptor> result = null;

        DirectoryParser directoryParser = new DirectoryParser(fullPath);
        if (directoryParser.parse()) {
            if (validateUBPresence(directoryParser)) {
                result = new UBApplicationStructureDescriptor<UBModuleDescriptor>(fullPath);
                UBModuleDescriptor main = getModuleDescriptor(directoryParser, "core");
                main.setRelativePath("\\");
                result.setMainModule(main);
                if (directoryParser.getDirectories().containsKey("modules")) {
                    File modulesPath = new File(fullPath + "\\modules");
                    String[] modulesContent = modulesPath.list();
                    for (String moduleName : modulesContent) {
                        String moduleRelativePath = "\\modules\\" + moduleName;
                        String fullModulePath = fullPath + moduleRelativePath;
                        DirectoryParser directoryParserForModule = new DirectoryParser(fullModulePath);
                        UBModuleDescriptor module = getModuleDescriptor(directoryParserForModule, moduleName);
                        module.setRelativePath(moduleRelativePath);
                        result.addModule(module);
                    }
                }
            }
        }

        return result;
    }

    private UBModuleDescriptor getModuleDescriptor(DirectoryParser directoryParser, String name) {
        directoryParser.parse();
        UBModuleDescriptor result = new UBModuleDescriptor();
        result.setName(name);
        result.setSrcPresent(directoryParser.getDirectories().containsKey("src"));
        result.setResourcesPresent(directoryParser.getDirectories().containsKey("resources"));
        result.setTestPresent(directoryParser.getDirectories().containsKey("test"));
        result.setIvyConfigPresent(directoryParser.getFiles().containsKey("ivy.xml"));
        result.setMainModule(directoryParser.getDirectories().containsKey("build"));
        result.setWebModule(directoryParser.getDirectories().containsKey("webapp"));
        result.setStaticWebModule(directoryParser.getDirectories().containsKey("web"));
        if (result.isMainModule()) {
            File checkStyle = new File(directoryParser.getFullPath() + "\\build\\rules\\checkstyle_rules.xml");
            result.setCheckStylePresent(checkStyle.exists());
        }

        return result;
    }

    private boolean validateUBPresence(DirectoryParser directoryParser) {
        boolean defaultProperyPresent = directoryParser.getFiles().containsKey("default.properties");
        boolean buildXmlPresent = directoryParser.getFiles().containsKey("build.xml");
        boolean buildDirectoryPresent = directoryParser.getDirectories().containsKey("build");
        return defaultProperyPresent && buildXmlPresent && buildDirectoryPresent;

    }

    private static class DirectoryParser {

        private Map<String, File> files       = new HashMap<String, File>();
        private Map<String, File> directories = new HashMap<String, File>();
        private String            fullPath;

        public DirectoryParser(String fullPath) {
            this.fullPath = fullPath;
        }

        public boolean parse() {
            boolean result = false;
            File base = new File(fullPath);

            if (base.isDirectory()) {

                String[] contents = base.list();
                for (String content : contents) {
                    File current = new File(fullPath + "\\" + content);
                    if (current.isDirectory()) {
                        directories.put(content, current);
                    } else {
                        files.put(content, current);
                    }
                }
                result = true;
            }
            return result;
        }

        public Map<String, File> getFiles() {
            return files;
        }

        public Map<String, File> getDirectories() {
            return directories;
        }

        public String getFullPath() {
            return fullPath;
        }

    }
}
