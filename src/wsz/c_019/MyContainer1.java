/**
 * 实现一个容器，提供两个方法 add size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 *
 * 给list添加之后，t2能够接到通知，但是，t2线程的死循环浪费cpu，如果不用死循环，那么该怎么做呢
 *
 */
package wsz.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1 {

    volatile List list = new ArrayList<>();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }


    public static void main(String[] args) {

        MyContainer1 m = new MyContainer1();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                m.add(new Object());
                System.out.println("add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }, "t1").start();

        new Thread(()->{
            while (true) {
                if (m.size() == 5) {
                    break;
                }
            }
            System.out.println(m.size());
        }, "t2").start();

    }

}
