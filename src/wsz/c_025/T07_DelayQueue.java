package wsz.c_025;

import java.util.Random;
import java.util.concurrent.*;

public class T07_DelayQueue {

    static BlockingQueue<MyMask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyMask implements Delayed {
        long runningTime;

        MyMask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "" + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        long now = System.currentTimeMillis();

        MyMask t1 = new MyMask(now + 1000);
        MyMask t2 = new MyMask(now + 2000);
        MyMask t3 = new MyMask(now + 1500);
        MyMask t4 = new MyMask(now + 2500);
        MyMask t5 = new MyMask(now + 500);


        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }

}


