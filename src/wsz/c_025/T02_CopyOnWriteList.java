/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 */
package wsz.c_025;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        List<String> lists =
                //new ArrayList<>(); // 不加锁
                //new Vector();
                new CopyOnWriteArrayList<>();  //写的很少读的很多
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(100000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }

        runAndComputeTime(ths);
        System.out.println(lists.size());


    }

    static void runAndComputeTime(Thread[] ths) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
    }


}
