/**
 */
class MyException extends Exception {
}

/**
 */
class Tire {
    void doStuff() {
    }
}

/**
 */
public class Retread extends Tire {

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        new Retread().doStuff();
    }

    /**
     * Method doStuff.
     * @throws RuntimeException
     */
    @Override
    // illegal
    // void doStuff() throws MyException{
    // legal
    // void doStuff() {
    void doStuff() throws RuntimeException {
        // void doStuff() throws ArithmeticException {
        System.out.println(7 / 0);
    }
}
