package perf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Measurement {

    private final static String             FILENAME        = "testdata.csv";
    private final static int                THREAD_COUNT    = 100;
    private final static List<Task>         TASKS           = new ArrayList<Task>(THREAD_COUNT);
    private final static ExecutorService    THREAD_EXECUTOR = Executors.newFixedThreadPool(THREAD_COUNT);
    private final static List<List<String>> TEST_DATA       = new ArrayList<List<String>>(THREAD_COUNT);
    private final static List<List<String>> GOOD_DATA       = new ArrayList<List<String>>(THREAD_COUNT);

    static {
        for (int i = 0; i < THREAD_COUNT; ++i) {
            TEST_DATA.add(new LinkedList<String>());
            GOOD_DATA.add(new LinkedList<String>());
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Measuring of " + LargestDivison.getYourName() + "'s solution.");
            System.out.println("Test data loading started.");
            long time = System.currentTimeMillis();
            loadTestData();
            time = System.currentTimeMillis() - time;
            System.out.println("Test data loading finished: " + time + " ms");

            System.out.println("Test initializing started.");
            time = System.currentTimeMillis();
            initTest("perf.LargestDivison");
            time = System.currentTimeMillis() - time;
            System.out.println("Test initializing finished: " + time + " ms");

            System.gc();
            System.out.println("Test started.");
            time = System.currentTimeMillis();
            executeTest();
            time = System.currentTimeMillis() - time;
            System.out.println("Test stopped: " + time + " ms");

            System.out.println("Test validating started.");
            time = System.currentTimeMillis();
            if (validateResult()) {
                System.out.println("Test: OK");
            } else {
                System.out.println("Test: FAILED");
            }
            time = System.currentTimeMillis() - time;
            System.out.println("Test validating stopped: " + time + " ms");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private static void loadTestData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(FILENAME));
        try {
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitted = line.split(",");
                TEST_DATA.get(i % THREAD_COUNT).add(splitted[0]);
                GOOD_DATA.get(i % THREAD_COUNT).add(splitted[1]);
                i++;
            }
            System.out.println(i + " testdata a read from " + FILENAME);
        } finally {
            scanner.close();
        }
    }

    private static void initTest(String clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < THREAD_COUNT; ++i) {
            TASKS.add(new Task(TEST_DATA.get(i)));
        }
    }

    private static void executeTest() throws InterruptedException {
        for (Task task : TASKS) {
            THREAD_EXECUTOR.execute(task);
        }
        THREAD_EXECUTOR.shutdown();
        THREAD_EXECUTOR.awaitTermination(5, TimeUnit.MINUTES);
    }

    private static boolean validateResult() {
        boolean isValid = true;
        long taskResultCount = 0;
        long goodDataCount = 0;
        for (int i = 0; i < THREAD_COUNT; ++i) {
            int j = 0;
            goodDataCount += GOOD_DATA.get(i).size();
            for (Long result : TASKS.get(i).getResult()) {
                if (!result.toString().equals(GOOD_DATA.get(i).get(j))) {
                    System.out.println(TEST_DATA.get(i).get(j) + " the largest division is: " + GOOD_DATA.get(i).get(j) + " the getting result:"
                            + result);
                    isValid = false;
                    break;
                }
                taskResultCount++;
                j++;
            }
        }
        if (taskResultCount != goodDataCount) {
            System.out.println("Incomplete result the expected was: " + goodDataCount + " the actual was: " + taskResultCount);
            isValid = false;
        }

        return isValid;
    }
}
