/**
 */
public class Comp2 {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        float f1 = 2.3f;
        float[][] f2 = {{42.0f}, {1.7f, 2.3f}, {2.6f, 2.7f}};
        float[] f3 = {2.7f};
        Long x = 42L;

        // if(f1 == f2)
        // System.out.println("true1");
        if (f1 == f2[2][1]) {
            System.out.println("true2");
        }
        if (x == f2[0][0]) {
            System.out.println("true3");
        }
        // if(f1 == f2[1,1])
        // System.out.println("true4");
        if (f3 == f2[2]) {
            System.out.println("true5");
        }
    }
}
