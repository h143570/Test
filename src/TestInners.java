/**
 */
class AA {
    void m() {
        System.out.println("outter");
    }
}

/**
 */
public class TestInners {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        new TestInners().go();
    }

    void go() {
        new AA().m();
        /**
         */
        class AA {
            void m() {
                System.out.println("inner");
            }
        }
    }

    /**
     */
    class AA {
        void m() {
            System.out.println("middle");
        }
    }
}
