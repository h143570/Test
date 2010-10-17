/**
 */
public class Bridge {
    /**
     */
    public enum Suites {
        CLUBS(20), DIAMOMDS(20), HEARTS(30), SPADES(30), NOTRUMP(40) {
            @Override
            public int getValue(int bid) {
                return (bid - 1) * 30 + 40;
            }
        };
        /**
         * Constructor for Suites.
         * @param points int
         */
        private Suites(int points) {
            this.points = points;
        }

        private int points;

        /**
         * Method getValue.
         * @param bid int
         * @return int
         */
        public int getValue(int bid) {
            return points * bid;
        }
    }

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        System.out.println(Suites.NOTRUMP.getValue(3));
        System.out.println(Suites.SPADES + " " + Suites.SPADES.points);
        System.out.println(Suites.values());
    }
}
