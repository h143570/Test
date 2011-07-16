package perf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private static long                        maxElement30;
    private static long                        maxElement210;

    private static final Long                  MINUS_ONE                            = -1L;

    //1 is removed
    private static final long[]                probablePrimeSuffixes                = new long[]{11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
        61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 121, 127, 131, 137, 139, 143, 149, 151, 157, 163, 167, 169, 173, 179, 181, 187, 191,
        193, 197, 199, 209                                                          };

    public LargestDivison() {
        super();

        if (preGenCache == null) {

            preGenCache = new long[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS];

            preGenCache[0] = 11;
            int i = 1;
            long numb = 13;

            while (i < MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS) {
                if ((numb % 3) != 0) {
                    if ((numb % 5) != 0) {
                        if ((numb % 7) != 0) {
                            if (doWorkInternallyRemainder4(numb, (long) Math.sqrt(numb), 0) == 1) {
                                preGenCache[i] = numb;
                                i++;
                                if (i % 10000 == 0) {
                                    System.out.println(i);
                                }
                            }
                        }
                    }
                }
                numb += 2;
            }
            maxElement = preGenCache[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS - 1];
            System.out.println(maxElement);

            maxElement30 = (maxElement / 30) * 30;
            maxElement210 = (maxElement / 210) * 210;

            List<Long> nonDivisable = new ArrayList<Long>();
            for (long l = 0; l < 2310; l++) {
                if (l % 2 == 0) {
                    continue;
                }
                if (l % 3 == 0) {
                    continue;
                }
                if (l % 5 == 0) {
                    continue;
                }
                if (l % 7 == 0) {
                    continue;
                }
                if (l % 11 == 0) {
                    continue;
                }
                nonDivisable.add(l);
            }
            System.out.println(nonDivisable);
            System.out.println(nonDivisable.size());

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
            if ((tmpLong % 7) == 0) {
                return tmpLong / 7;
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

    private static final long doWorkInternallyRemainder4(final long n, final long sqrt, final long start) {
        for (long i = start; i <= sqrt; i += 210) {
            if ((i > 0) && (n % (i + 1) == 0)) {
                return n / (i + 1);
            }
            for (long l : probablePrimeSuffixes) {

                if ((n % (i + l) == 0)) {
                    return n / (i + l);
                }
            }
        }
        return 1;
    }

}