/**
 */
class DoFormat {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        String s1 = null;
        String s2 = "TrUe";
        String s3 = "yes";
        String s4 = "no";
        Boolean b1 = new Boolean("tRuE");
        boolean b2 = false;
        System.out.printf("%b %b %b %b %b", s1, s2, s3, b1, b2, s4);
    }
}