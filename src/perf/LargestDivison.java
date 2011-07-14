package perf;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LargestDivison {

    private static final int                   CACHE_SIZE                           = 500;
    private static final int                   MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS = 100000;

    //    private static Map<String, Long> preGenCache;
    private static long[]                      preGenCache;
    private static ConcurrentMap<String, Long> cache                                = new ConcurrentHashMap<String, Long>(CACHE_SIZE + 100, 1, 10);
    private static long                        maxElement;

    private static final Long                  MINUS_ONE                            = -1L;

    public LargestDivison() {
        super();

        if (preGenCache == null) {

            preGenCache = new long[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS];

            preGenCache[0] = 3;
            int i = 1;
            long numb = 5L;

            while (i < MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS) {
                if (doWorkInternallyRemainder(numb, (long) Math.sqrt(numb), 3) == 1) {
                    preGenCache[i] = numb;
                    i++;
                }
                numb += 2;
            }
            maxElement = preGenCache[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS - 1];
            System.out.println(preGenCache[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS - 1]);

        }
    }

    /**
     *
     * Get largest real division of the given number. If number is a prime, it gives back 1. If number is less than 2, or it cannot be parsed to long,
     * method returns -1.
     *
     * @param number to get the real largest divisions.
     * @return the real largest divisions, 1 if number is a prime. -1 in case of error.
     */

    public long getLargestDivison(String number) {
        Long result = cache.get(number);
        if (result == null) {
            long tmpLong = 0;
            try {
                tmpLong = Long.parseLong(number);
            } catch (NumberFormatException nfe) {
            }
            if (tmpLong < 2) {
                return MINUS_ONE;
            }
            if ((tmpLong & 1) == 0) {
                return tmpLong >> 1;
            }

            //            BigInteger n = new BigInteger(number);
            //            if (n.isProbablePrime(20)) {
            //                result = PollardRho.rho(n).longValue();
            //            } else {
            //                result = 1L;
            //            }

            long tmpResult = doWorkInternallyFast(tmpLong);
            if (tmpResult == 0L) {
                tmpResult = doWorkInternallyRemainder(tmpLong, (long) Math.sqrt(tmpLong), maxElement);
            }
            result = tmpResult;
            storeInCache(number, result);
        }

        return result;
    }

    /**
     * Give back your name.
     * @return your name.
     */
    public static String getYourName() {
        return "Bessenyei Balazs";
    }

    private void storeInCache(String number, Long result) {
        if (cache.size() >= CACHE_SIZE) {
            Iterator<Entry<String, Long>> it = cache.entrySet().iterator();
            it.next();
            it.remove();
        }
        cache.put(number, result);
    }

    private static final long doWorkInternallyFast(final long n) {
        long result = 0;
        for (long j : preGenCache) {
            if (n % j == 0) {
                return n / j;
            }
        }
        return result;
    }

    private static final long doWorkInternallyRemainder(final long n, final long sqrt, final long start) {
        long result = 1;
        for (long i = start; i <= sqrt; i += 2) {
            if (n % i == 0) {
                return n / i;
            }
        }
        return result;
    }

    private static class PollardRho {
        private final static BigInteger   ZERO   = new BigInteger("0");
        private final static BigInteger   ONE    = new BigInteger("1");
        private final static BigInteger   TWO    = new BigInteger("2");
        private final static SecureRandom random = new SecureRandom();

        public static BigInteger rho(BigInteger n) {
            BigInteger divisor;
            BigInteger c = new BigInteger(n.bitLength(), random);
            BigInteger x = new BigInteger(n.bitLength(), random);
            BigInteger xx = x;

            // check divisibility by 2
            if (n.mod(TWO).compareTo(ZERO) == 0) {
                return TWO;
            }

            do {
                x = x.multiply(x).mod(n).add(c).mod(n);
                xx = xx.multiply(xx).mod(n).add(c).mod(n);
                xx = xx.multiply(xx).mod(n).add(c).mod(n);
                divisor = x.subtract(xx).gcd(n);
            } while ((divisor.compareTo(ONE)) == 0);

            return divisor;
        }

        public static void factor(BigInteger n) {
            if (n.compareTo(ONE) == 0) {
                return;
            }
            if (n.isProbablePrime(20)) {
                System.out.println(n);
                return;
            }
            BigInteger divisor = rho(n);
            factor(divisor);
            factor(n.divide(divisor));
        }

    }

}
