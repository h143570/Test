package perf;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LargestDivison {

    private static final int                   CACHE_SIZE                           = 100000;
    private static final int                   MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS = 500000;

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
                result = MINUS_ONE;
            } else {
                if (tmpLong % 2L != 0) {
                    long sqrt = (long) Math.sqrt(tmpLong);
                    if (sqrt <= maxElement) {
                        result = doWorkInternallyFast(tmpLong);
                    } else {
                        long tmpResult = doWorkInternallyFast(tmpLong);
                        if (tmpResult == 1L) {
                            tmpResult = doWorkInternallyRemainder(tmpLong, sqrt, maxElement);
                        }
                        result = tmpResult;
                    }

                } else {
                    result = tmpLong / 2L;
                }
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

    private void storeInCache(String number, Long result) {
        if (cache.size() >= CACHE_SIZE) {
            Iterator<Entry<String, Long>> it = cache.entrySet().iterator();
            it.next();
            it.remove();
        }
        cache.put(number, result);
    }

    private static final long doWorkInternallyFast(long n) {
        long result = 1;
        for (int i = 0; i < MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS; i++) {
            long j = preGenCache[i];
            if (n % j == 0) {
                result = n / j;
                break;
            }
        }
        return result;
    }

    private static final long doWorkInternallyRemainder(long n, long sqrt, long start) {
        long result = 1;
        for (long i = start; i <= sqrt; i += 2) {
            if (n % i == 0) {
                result = n / i;
                break;
            }
        }
        return result;
    }

}
