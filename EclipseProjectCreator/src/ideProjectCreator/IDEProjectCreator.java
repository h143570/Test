package ideProjectCreator;


public class IDEProjectCreator {

    public static void main(String... args) {

        UBToEclipseConverter converter = new UBToEclipseConverter();

        converter.convert(args);
    }

}
