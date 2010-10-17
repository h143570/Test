/**
 */
class Bird {
    {
        System.out.println("b1 ");
    }

    public Bird() {
        System.out.println("b2 ");
    }
}

/**
 */
class Raptor extends Bird {
    static {
        System.out.println("r1 ");
    }

    public Raptor() {
        System.out.println("r2 ");
    }

    {
        System.out.println("r3 ");
    }
    static {
        System.out.println("r4 ");
    }
}

/**
 */
public class Hawk extends Raptor {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        System.out.println("pre ");
        new Hawk();
        System.out.println("hawk");

    }
}
