/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法。
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notifyAll来实现
 *
 */
package wsz.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;


    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者进行消费
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t = lists.removeFirst();
        --count;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {

        MyContainer1<String> c = new MyContainer1<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }

}
