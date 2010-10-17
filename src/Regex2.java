import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Regex2 {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        Pattern p = Pattern.compile(args[0]);
        Matcher m = p.matcher(args[1]);
        boolean b = false;
        while (b = m.find()) {
            System.out.print(m.start() + m.group() + " ");
        }

    }
}
