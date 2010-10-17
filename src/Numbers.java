import java.util.Set;
import java.util.TreeSet;

/**
 */
public class Numbers {

    private Set<Integer> numbers = new TreeSet<Integer>();

    /**
     * Constructor for Numbers.
     * @param nums int[]
     */
    public Numbers(int... nums) {
        for (int n : nums) {
            numbers.add(n);
        }
    }

    /**
     * Method negate.
     * @return Numbers
     */
    public Numbers negate() {
        Numbers negatives = new Numbers();
        for (int n : numbers) {
            negatives.numbers.add(-n);
        }
        return negatives;
    }

    public void show() {
        for (int n : numbers) {
            System.out.print(n + " ");
        }
    }

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        new Numbers(1, 3, -5).negate().show();

    }
}
