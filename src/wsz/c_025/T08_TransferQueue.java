package wsz.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {

    public static void main(String[] args) throws InterruptedException{
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            try{
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");  //没有就阻塞了

/*        new Thread(()->{
            try{
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

    }
}
