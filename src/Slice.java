import java.text.NumberFormat;
import java.text.ParseException;

/**
 */
public class Slice {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        String s = "987,123456"; //Local dependent
        double d = 987.123456d;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);
        System.out.print(nf.format(d) + " ");

        try {
            System.out.println(nf.parse(s));
        } catch (ParseException e) {
            System.out.println("got exc");
        }
    }
}
