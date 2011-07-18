package perf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LargestDivison {

    private static final int                   CACHE_SIZE                           = 99950;
    private static final int                   MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS = 94235;

    private static long[]                      preGenCache;
    private static ConcurrentMap<String, Long> cache                                = new ConcurrentHashMap<String, Long>(CACHE_SIZE + 50, 1, 100);
    private static long                        maxElements                          = 0;
    private static long                        maxCombinations                      = 1;

    private static final Long                  MINUS_ONE                            = -1L;

    private static final long[]                primes                               = new long[]{2, 3, 5, 7, 11, 13};
    //    private static long                        firstQuarter                         = 0;
    //    private static long                        secondQuarter                        = 0;
    //    private static long                        thirdQuarter                         = 0;
    private static long[]                      primeSuffixes;

    private static long[][]                    primeSuffixes2;
    private static long[]                      primeSuffix2UpperBounds;

    //    private static long[]                      primeSuffixesFirst;
    //    private static long[]                      primeSuffixesSecond;
    //    private static long[]                      primeSuffixesThird;
    //    private static long[]                      primeSuffixesFourt;

    public LargestDivison() {
        super();

        if (preGenCache == null) {

            for (long prim : primes) {
                maxCombinations *= prim;
            }

            List<Long> nonDivisable = new ArrayList<Long>();
            cont: for (long l = 1; l < maxCombinations; l++) {
                for (long prim : primes) {
                    if (l % prim == 0) {
                        continue cont;
                    }
                }
                nonDivisable.add(l);
            }
            System.out.println(maxCombinations);
            System.out.println(nonDivisable.size());
            System.out.println((double) maxCombinations / (double) nonDivisable.size());

            primeSuffixes = new long[nonDivisable.size() - 1];
            for (int i = 1; i < nonDivisable.size(); i++) {
                primeSuffixes[i - 1] = nonDivisable.get(i);
            }

            int size = nonDivisable.size() - 1;
            int quater = 4;
            int sizeQuarter = size / quater;
            int sizeQuarterMod = size % quater;
            int index = 0;
            primeSuffix2UpperBounds = new long[quater - 1];
            primeSuffixes2 = new long[quater][];
            for (int i = 0; i < quater; i++) {
                int sizeInternal = i == 0 ? sizeQuarter : i < sizeQuarterMod ? sizeQuarter : sizeQuarter - 1;
                for (int j = 0; j < sizeInternal; j++) {
                    index++;
                    long[] primeSuffix = primeSuffixes2[i];
                    if (primeSuffix == null) {
                        primeSuffix = new long[sizeInternal];
                        primeSuffixes2[i] = primeSuffix;
                    }
                    //                    if (j < primeSuffix.length) {
                    primeSuffix[j] = nonDivisable.get(index);
                    if (i < quater - 1) {
                        primeSuffix2UpperBounds[i] = primeSuffix[j];
                    }
                    //                    }

                    //                    switch (i) {
                    //                    case 0:
                    //                        if (primeSuffixesFirst == null) {
                    //                            primeSuffixesFirst = new long[sizeQuarter];
                    //                        }
                    //                        primeSuffixesFirst[j] = nonDivisable.get(index);
                    //                        firstQuarter = primeSuffixesFirst[j];
                    //                    case 1:
                    //                        int sizeInternal = sizeQuarterMod < 3 ? sizeQuarter : sizeQuarter - 1;
                    //                        if (primeSuffixesSecond == null) {
                    //                            primeSuffixesSecond = new long[sizeInternal];
                    //                        }
                    //                        if (j >= sizeInternal) {
                    //                            break;
                    //                        }
                    //                        primeSuffixesSecond[j] = nonDivisable.get(index);
                    //                        secondQuarter = primeSuffixesSecond[j];
                    //                    case 2:
                    //                        sizeInternal = sizeQuarterMod < 2 ? sizeQuarter : sizeQuarter - 1;
                    //                        if (primeSuffixesThird == null) {
                    //                            primeSuffixesThird = new long[sizeInternal];
                    //                        }
                    //                        if (j >= sizeInternal) {
                    //                            break;
                    //                        }
                    //                        primeSuffixesThird[j] = nonDivisable.get(index);
                    //                        thirdQuarter = primeSuffixesThird[j];
                    //                    case 3:
                    //                        sizeInternal = sizeQuarterMod < 1 ? sizeQuarter : sizeQuarter - 1;
                    //                        if (primeSuffixesFourt == null) {
                    //                            primeSuffixesFourt = new long[sizeInternal];
                    //                        }
                    //                        if (j >= sizeInternal) {
                    //                            break;
                    //                        }
                    //                        primeSuffixesFourt[j] = nonDivisable.get(index);
                    //                    }
                }
            }

            preGenCache = new long[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS];

            preGenCache[0] = 11;
            int i = 1;
            long numb = 13;

            genconn: while (i < MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS) {

                for (long prim : primes) {
                    if (numb % prim == 0) {
                        numb += 2;
                        continue genconn;
                    }
                }

                if (doWorkInternallyRemainder4(numb, (long) Math.sqrt(numb)) == 1) {
                    preGenCache[i] = numb;
                    i++;
                    if (i % 10000 == 0) {
                        System.out.println(i);
                    }
                }
                numb += 2;
            }
            long maxElement = preGenCache[MAX_NUMBER_OF_PRE_GEN_CACHE_ELEMENTS - 1];
            System.out.println(maxElement);

            maxElements = (maxElement / maxCombinations) * maxCombinations;

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

            for (long prim : primes) {
                if (tmpLong % prim == 0) {
                    return tmpLong / prim;
                }
            }

            long tmpResult = doWorkInternallyFast(tmpLong);
            if (tmpResult == 0L) {
                tmpResult = doWorkInternallyRemainder4(tmpLong, (long) Math.sqrt(tmpLong));
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

    //    private static final long doWorkInternallyRemainder4(final long n, final long sqrt) {
    //        for (long i = maxElements; i <= sqrt; i += maxCombinations) {
    //
    //            long m = i + 1;
    //            if ((i > 0) && (n % m == 0)) {
    //                return n / m;
    //            }
    //            for (long l : primeSuffixes) {
    //                m = i + l;
    //                if ((n % m == 0)) {
    //                    return n / m;
    //                }
    //            }
    //        }
    //        return 1;
    //    }

    private static final long doWorkInternallyRemainder4(final long n, final long sqrt) {

        for (long i = maxElements; i <= sqrt; i += maxCombinations) {

            long bound = i % maxCombinations;
            long[] primeSuffix = primeSuffixes;
            for (int index = 0; index < primeSuffix2UpperBounds.length; index++) {

                if (index == 0) {
                    primeSuffix = primeSuffixes2[index];
                } else if (bound > primeSuffix2UpperBounds[index - 1] && bound <= primeSuffix2UpperBounds[index]) {
                    primeSuffix = primeSuffixes2[index];
                    break;
                } else if (primeSuffix2UpperBounds[index] > bound) {
                    primeSuffix = primeSuffixes2[primeSuffix2UpperBounds.length];
                }

            }

            long m = i + 1;
            if ((i > 0) && (n % m == 0)) {
                return n / m;
            }
            for (long l : primeSuffix) {
                m = i + l;
                if ((n % m == 0)) {
                    return n / m;
                }
            }
        }
        return 1;
    }
}