/**
 */
public class Steepper {

    /**
     */
    enum Roman {
        I, V, X, L, C, M
    }

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        int x = 7;
        int z = 2;
        Roman r = Roman.X;

        do {
            switch (r) {
            case C:
                r = Roman.L;
                break;
            case X:
                r = Roman.C;
            case L:
                if (r.ordinal() > 2) {
                    z += 5;
                }
            case M:
                x++;
            }
            z++;
        } while (x < 10);
        System.out.println(z);

    }

}
