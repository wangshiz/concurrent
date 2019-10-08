/**
 * http
 */
package wsz.c_025;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
    //Map<String, String> map = new ConcurrentHashMap<>();
    //Map<String, String> map = new ConcurrentSkipListMap<>();

    Map<String, String> map = new Hashtable<>();
    //Map<String, String> map = new HashMap<>();
    //TreeMap
    Random r = new Random();
    Thread[] ths = new Thread[100];
    CountDownLatch latch = new CountDownLatch(ths.length);




}
