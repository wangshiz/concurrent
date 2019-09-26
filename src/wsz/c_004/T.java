/**
 * sychronized关键字
 * 对某个对象加锁
 */
package wsz.c_004;

public class T {

    private static int count = 10;

    public synchronized static void m() {//等同于在方法的代码执行要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized (T.class) {//锁定当前T类的class对象
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
