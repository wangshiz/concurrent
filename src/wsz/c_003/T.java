/**
 * sychronized关键字
 * 对某个对象加锁
 */
package wsz.c_003;

public class T {

    private int count = 10;

    public synchronized void m() {//等同于在方法的代码执行要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

}
