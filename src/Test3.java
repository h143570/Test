/**
 */
class Test3 {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        boolean x = true;
        boolean y = false;
        short z = 42;

        if ((x = false) || (y = true)) {
            z++;
        }
        if (z++ == 44 || ++z == 45) {
            z++;
        }

        System.out.println("z = " + z);
    }
}