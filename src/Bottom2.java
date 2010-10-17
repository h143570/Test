/**
 */
class Top {
    /**
     * Constructor for Top.
     * @param s String
     */
    public Top(String s) {
        System.out.print("B");
    }

    //	public Top() {
    //	}
}

/**
 */
public class Bottom2 extends Top {

    /**
     * Constructor for Bottom2.
     * @param s String
     */
    public Bottom2(String s) {
        super(s);
        System.out.print("B");
    }

}
