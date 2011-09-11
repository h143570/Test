package ideProjectCreator;

import ideProjectCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjectCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjectCreator.domain.module.ub.UBModuleDescriptor;

public class IDEProjectCreator {

    public static void main(String... args) {

        UBToEclipseConverter<UBApplicationStructureDescriptor<UBModuleDescriptor>, EclipseIDEProjectDescriptor> converter;
        converter = new UBToEclipseConverter<UBApplicationStructureDescriptor<UBModuleDescriptor>, EclipseIDEProjectDescriptor>();

        converter.convert(args);
    }

}
