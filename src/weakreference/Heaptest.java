package weakreference;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.ref.WeakReference;

import javax.imageio.stream.FileCacheImageInputStream;
import javax.management.MBeanServer;
import com.sun.management.HotSpotDiagnosticMXBean;

// had to do this to get this pacjage working
// project.properties.BuildPath.Library , remove JRE Library
// then Add Library-> JRE library
import java.util.Map;
import java.util.WeakHashMap;
import java.util.HashMap;

public class Heaptest {
    private static Map<String, byte[]>     mapp;
    private static HotSpotDiagnosticMXBean hotspotMBean;

    static {
        mapp = new WeakHashMap<String, byte[]>();
    }

    public static void main(String[] args) {
        byte[] b = new byte[1024 * 1024 * 100];
        String xx = new String("mykey");
        mapp.put(xx, b);

        for (int i = 0; i < 1000 * 10; i++) {
            mapp.put("mykey" + i, new byte[1024 * 1024 * 10]);
        }

        /*
         * WeakHashMap will now contain entries of the type Entry. Entry extends
         * WeakReference implements Map.Entry - per source code for WeakHashMap.
         * class Entry { private K key; private V value; Entry() { super( K ,
         * referenceQ );
         *
         * WeakReference c'tor key = k; value = v; ..... } } So essentially
         * WeakHashMap will contain list of WeakReference type objects
         *
         * Observe entries in WeakHashMap before garbage collection.
         * Notice that object in WeakHashMap can be type casted successfully to a weak
         * reference.
         */
        System.out.println("before garbage collection ");
        //        for (Map.Entry<String, byte[]> e : mapp.entrySet()) {
        //            WeakReference<String> we = (WeakReference<String>) e;
        //            String key = e.getKey();
        //            byte[] value = e.getValue();
        //            String storedKey = we.get();
        //            if (we != null) {
        //                System.out.println(we.get());
        //            }
        //        }
        System.out.println("Map size = " + mapp.size());
        File file = new File("Y:/java1.hprof");
        if (file.exists()) {
            file.delete();
        }
        dumpHeap("Y:/java1.hprof");
        /*
         * Simulating garbage collection on xx. * When xx is garage collected,
         * Weak Reference to xx is put in the ReferenceQ. * So, this will cause
         * entry Entry< String, byte[] >:WeakReference("mykey") to be moved to *
         * the referenceQ attached to WeakReference
         */
        xx = null;
        System.gc();
        /*
         * contrary to conventional wisdom, this ACTUALY calls garbage
         * collection
         */
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
        /*
         * Most functions in WeakHashMap calls the routine for cleaning up
         * entries in reference Q. So mapp.entrySet will find entries Entryin
         * the reference Q and , it de-allocate the entry from the WeakHashMap,
         * and byte[] will garbage collected.
         */
        System.out.println("After garbage collection ");
        for (Map.Entry e : mapp.entrySet()) {
            WeakReference<String> we = (WeakReference<String>) e;
            if (we != null) {
                System.out.println(we.get());
            }
        }
        System.out.println("Map size = " + mapp.size());
        File file2 = new File("Y:/java2.hprof");
        if (file2.exists()) {
            file2.delete();
        }
        dumpHeap("Y:/java2.hprof");
    }

    static void dumpHeap(String filename) {
        if (hotspotMBean == null) {
            synchronized (Heaptest.class) {
                if (hotspotMBean == null) {
                    try {
                        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
                        hotspotMBean = ManagementFactory.newPlatformMXBeanProxy(server, //"com.sun.management.HotSpotDiagnosticMXBean"
                                "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
                    } catch (RuntimeException re) {
                        throw re;
                    } catch (Exception exp) {
                        throw new RuntimeException(exp);
                    }
                }
            }
        }
        try {
            hotspotMBean.dumpHeap(filename, true);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
        System.out.println("Heap dump done");
    }
}
