/**
 * sychronized关键字
 * 对某个对象加锁
 */
package wsz.c_002;

public class T {

    private int count = 10;

    public void m() {
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
