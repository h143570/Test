/**
 */
class A {
}

/**
 */
class B extends A {
}

/**
 */
public class ComingThru {
    static String s = "-";

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        A[] aa = new A[2];
        B[] ba = new B[2];
        sifter(aa);
        sifter(ba);
        sifter(7);
        System.out.println(s);

    }

    /**
     * Method sifter.
     * @param a2 A[][]
     */
    static void sifter(A[]... a2) {
        s += "1";
    }

    /**
     * Method sifter.
     * @param b1 B[][]
     */
    static void sifter(B[]... b1) {
        s += "2";
    }

    /**
     * Method sifter.
     * @param b1 B[]
     */
    static void sifter(B[] b1) {
        s += "3";
    }

    /**
     * Method sifter.
     * @param o Object
     */
    static void sifter(Object o) {
        s += "4";
    }

}
