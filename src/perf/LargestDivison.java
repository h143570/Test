package perf;

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
    private static long                        startK;
    private static long                        maxElementK;
    private static long                        maxElementKMinusOne;

    private static long                        maxElement30;

    private static final Long                  MINUS_ONE                            = -1L;

    public LargestDivison() {
        super();

        if (preGenCache == null) {

            preGenCache = new long[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS];

            preGenCache[0] = 7;
            int i = 1;
            long numb = 11;

            while (i < MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS) {
                if ((numb % 3) != 0) {
                    if ((numb % 5) != 0) {
                        //                if (doWorkInternallyRemainder(numb, (long) Math.sqrt(numb), 3) == 1) {
                        if (doWorkInternallyRemainder3(numb, (long) Math.sqrt(numb), 0) == 1) {
                            //                if (isPrime(numb)) {
                            preGenCache[i] = numb;
                            i++;
                            if (i % 10000 == 0) {
                                System.out.println(i);
                            }
                        }
                    }
                }
                numb += 2;
            }
            maxElement = preGenCache[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS - 1];
            System.out.println(maxElement);

            if ((maxElement - 1) % 6 == 0) {
                startK = (maxElement - 1) / 6;
            } else if ((maxElement + 1) % 6 == 0) {
                startK = (maxElement + 1) / 6;
            }
            maxElementK = startK * 6;
            maxElementKMinusOne = maxElementK - 1;

            maxElement30 = (maxElement / 30) * 30;
            System.out.println(startK);

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
            if ((tmpLong % 3) == 0) {
                return tmpLong / 3;
            }
            if ((tmpLong % 5) == 0) {
                return tmpLong / 5;
            }

            long tmpResult = doWorkInternallyFast(tmpLong);
            if (tmpResult == 0L) {
                tmpResult = doWorkInternallyRemainder3(tmpLong, (long) Math.sqrt(tmpLong), maxElement30);
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
        for (long j : preGenCache) {
            if (n % j == 0) {
                return n / j;
            }
        }
        return 0;
    }

    private static final long doWorkInternallyRemainder(final long n, final long sqrt, final long start) {
        for (long i = start; i <= sqrt; i += 2) {
            if (n % i == 0) {
                return n / i;
            }
        }
        return 1;
    }

    private static final long doWorkInternallyRemainder2(final long n, final long sqrt, final long start) {
        for (long i = start; i <= sqrt; i += 6) {
            if (n % i == 0) {
                return n / i;
            }
            if (n % (i + 2) == 0) {
                return n / (i + 2);
            }
        }
        return 1;
    }

    private static final long doWorkInternallyRemainder3(final long n, final long sqrt, final long start) {
        for (long i = start; i <= sqrt; i += 30) {

            if ((i > 0) && (n % (i + 1) == 0)) {
                return n / (i + 1);
            }
            if (n % (i + 7) == 0) {
                return n / (i + 7);
            }
            if (n % (i + 11) == 0) {
                return n / (i + 11);
            }
            if (n % (i + 13) == 0) {
                return n / (i + 13);
            }
            if (n % (i + 17) == 0) {
                return n / (i + 17);
            }
            if (n % (i + 19) == 0) {
                return n / (i + 19);
            }
            if (n % (i + 23) == 0) {
                return n / (i + 23);
            }
            if (n % (i + 29) == 0) {
                return n / (i + 29);
            }

        }
        return 1;
    }

    //    private static final boolean isPrime(final long number) {
    //        if ((number & 1) == 0) {
    //            return false;
    //        }
    //        if ((number % 3) == 0) {
    //            return false;
    //        }
    //
    //        if ((number % 5) == 0) {
    //            return false;
    //        }
    //
    //        int mod = (int) (number % 30);
    //        for (int j : modulus) {
    //            if (mod == j) {
    //                return true;
    //            }
    //        }
    //
    //        return false;
    //
    //    }

}