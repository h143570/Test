/**
 */
class Alpha {
    int over = 1;
}

/**
 */
class Beta extends Alpha {
    int over = 2;
}

/**
 */
public class Gamma extends Beta {

    int over = 3;

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        new Gamma().go();

    }

    void go() {
        Beta b = new Gamma();
        Alpha a = new Gamma();
        System.out.println(super.over + " " + b.over + " " + a.over);
    }

}
