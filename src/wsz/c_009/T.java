/**
 * 一个同步方法可以调用另外一个同步方法，一个县城已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁。
 * 也就是说synchronized获得的锁是重入的
 */
package wsz.c_009;

import java.util.concurrent.TimeUnit;

public class T {

    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 start");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(()->t.m1()).start();
    }


}
