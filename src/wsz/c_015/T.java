/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */
package wsz.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T {

    //volatile int count = 10;

    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++)
            //同时使用Atom的方法并不构成原子性
            count.incrementAndGet(); //count++;
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "Thread" + i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }


}
