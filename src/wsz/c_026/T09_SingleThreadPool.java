package wsz.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int j = 1;
            service.execute(()->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });

        }


    }

}
