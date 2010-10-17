/**
 */
public class Berta {
    static String s = "";

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {

        int x = 4;
        Boolean y = true;
        short[] sa = {1, 2, 3};
        doStuff(x, y);
        doStuff(x);
        doStuff(sa, sa);
        System.out.println(s);
    }

    /**
     * Method doStuff.
     * @param o Object
     */
    static void doStuff(Object o) {
        s += "1";
    }

    /**
     * Method doStuff.
     * @param o Object[]
     */
    static void doStuff(Object... o) {
        s += "2";
    }

    /**
     * Method doStuff.
     * @param i Integer[]
     */
    static void doStuff(Integer... i) {
        s += "3";
    }

    /**
     * Method doStuff.
     * @param L Long
     */
    static void doStuff(Long L) {
        s += "4";
    }

}
