/**
 * 实现一个容器，提供两个方法 add size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 *
 * 给list添加之后，t2能够接到通知，但是，t2线程的死循环浪费cpu，如果不用死循环，那么该怎么做呢
 *
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 *
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 *
 * 使用Latch(门闩)代替wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这时应该考虑countdownlatch/cyclibarrier/semaphore
 */
package wsz.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer3 {

    //添加volatile,使t2能够得到通知
    volatile List list = new ArrayList<>();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }


    public static void main(String[] args) {

        MyContainer3 m = new MyContainer3();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(()->{
            if (m.size() != 5) {
                try {
                    latch.await();

                    //也可以指定等待时间
                    //latch.await(5000,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                m.add(new Object());
                System.out.println("add " + i);

                if (m.size() == 5) {
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }

}
