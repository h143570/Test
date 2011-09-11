package ideProjektCreator;

import ideProjektCreator.domain.application.ub.UBApplicationStructureDescriptor;
import ideProjektCreator.domain.ide.eclipse.EclipseIDEProjectDescriptor;
import ideProjektCreator.domain.module.ub.UBModuleDescriptor;

public class IDEProjectCreator {

    public static void main(String... args) {

        UBToEclipseConverter<UBApplicationStructureDescriptor<UBModuleDescriptor>, EclipseIDEProjectDescriptor> converter;
        converter = new UBToEclipseConverter<UBApplicationStructureDescriptor<UBModuleDescriptor>, EclipseIDEProjectDescriptor>();

        converter.convert(args);
    }

}
