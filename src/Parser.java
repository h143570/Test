/**
 */
class Parser extends Utils {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        System.out.print(new Parser().getInt("42"));
    }

    /**
     * Method getInt.
     * @param arg String
     * @return int
     */
    @Override
    int getInt(String arg) {
        return Integer.parseInt(arg);
    }
}

/**
 */
class Utils {
    /**
     * Method getInt.
     * @param arg String
     * @return int
     * @throws Exception
     */
    int getInt(String arg) throws Exception {
        return 42;
    }
}