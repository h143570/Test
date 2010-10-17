/**
 */
public class AutoboxingTest {

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        autoBoxingTest();
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        autoBoxingTest2();
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void autoBoxingTest() {
        Integer intValue = 1;

        for (int i = 0; i < 10000000; i++) {
            intValue += 1;
        }
    }

    public static void autoBoxingTest2() {
        int intValue = 1;

        for (int i = 0; i < 10000000; i++) {
            intValue += 1;
        }
    }
}
