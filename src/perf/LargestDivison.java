package perf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LargestDivison {

    private static final int                   CACHE_SIZE                           = 400;
    private static final int                   MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS = 100000;

    private static Map<String, Long>           preGenCache;
    private static ConcurrentMap<String, Long> cache                                = new ConcurrentHashMap<String, Long>(CACHE_SIZE + 100, 1, 10);

    private static final Long                  MINUS_ONE                            = -1L;
    private static final long                  MAX_FAST_FACTOR                      = 3037000499L;

    public LargestDivison() {
        super();

        if (preGenCache == null) {
            preGenCache = new HashMap<String, Long>(MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS, 1);

            put(2L);
            long n = 3;
            while (n <= Long.MAX_VALUE && preGenCache.size() < 100000) {
                if (isPrime2(n) == 1) {
                    put(n);
                }
                n += 2;
            }
            System.out.println(n);
            System.out.println(preGenCache.size());
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
                result = MINUS_ONE;
            } else {
                result = isPrime2(tmpLong);
            }

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

    private static void put(long n) {
        preGenCache.put(Long.toString(n), 1L);
    }

    private void storeInCache(String number, Long result) {
        if (cache.size() >= CACHE_SIZE) {
            Iterator<Entry<String, Long>> it = cache.entrySet().iterator();
            it.next();
            it.remove();
        }
        cache.put(number, result);
    }

    private static boolean isPrime(long n) {
        boolean result = true;
        if (n % 2 != 0) {
            double sqrt = Math.sqrt(n);
            for (long i = 3; i <= sqrt; i += 2) {
                if (n % i == 0) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }

        return result;
    }

    private static long isPrime2(long n) {
        long result = n;
        if (n % 2 != 0) {
            double sqrt = Math.sqrt(n);
            for (long i = 3; i <= sqrt; i += 2) {
                if (n % i == 0) {
                    result = n / i;
                    break;
                }
            }
        } else {
            result = n / 2;
        }
        if (result == n) {
            result = 1;
        }

        return result;
    }

    private long largestDiv(long num) {
        long i = num / 2 - 1;
        for (; i > 0; i--) {
            if (num % i == 0) {
                break;
            }
        }
        return i;
    }

    private long largestDiv2(long num) {
        long result = num;
        long n = num;
        for (long i = 2; i <= n / i; i++) {

            // if i is a factor of N, repeatedly divide it out
            while (n % i == 0) {
                if (n > result) {
                    result = n;
                }
                //                System.out.print(i + " ");
                n = n / i;
            }
        }
        return result;
    }

}
