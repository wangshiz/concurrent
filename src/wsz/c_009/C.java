package wsz.c_009;

import java.util.concurrent.TimeUnit;

public class C {

    private Object o1 = new Object();
    private Object o2 = new Object();

    public void m1() {
        synchronized (o1) {
            System.out.println("m1 o1 start");
            try {
                TimeUnit.SECONDS.sleep(10000);
                synchronized (o2) {
                    System.out.println("m1 o2 start");
                    TimeUnit.SECONDS.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void m2() {
        synchronized (o2) {
            System.out.println("m2 o2 start");
            try {
                TimeUnit.SECONDS.sleep(10000);
                synchronized (o1) {
                    System.out.println("m2 o1 start");
                    TimeUnit.SECONDS.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        C c = new C();

        new Thread(()->c.m1()).start();
        new Thread(()->c.m2()).start();
    }

}
