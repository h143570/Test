/**
 */
public class Odd {

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        System.out.println(isOdd(1));
        System.out.println(isOdd(-1));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(isOdd(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(isOdd(Integer.MIN_VALUE));

        System.out.println("--------------");

        System.out.println(isOdd2(1));
        System.out.println(isOdd2(-1));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(isOdd2(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(isOdd2(Integer.MIN_VALUE));

        System.out.println("--------------");

        System.out.println(isOdd3(1));
        System.out.println(isOdd3(-1));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(isOdd3(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(isOdd3(Integer.MIN_VALUE));
    }

    /**
     * Method isOdd.
     * @param n int
     * @return boolean
     */
    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    /**
     * Method isOdd2.
     * @param i int
     * @return boolean
     */
    public static boolean isOdd2(int i) {
        return i << 31 >>> 31 == 1;
    }

    /**
     * Method isOdd3.
     * @param n int
     * @return boolean
     */
    public static boolean isOdd3(int n) {
        return Math.abs(n) % 2 == 1; // It will work for positive and negative numbers.
    }
}
