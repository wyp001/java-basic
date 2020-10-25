import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 争车位
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);//模拟资源类，有3个空车位

        //模拟6个车抢占三个车位
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(); //每个线程运行到这，车位减少一个
                    System.out.println(Thread.currentThread().getName()+"\t抢占了车位");
                    TimeUnit.SECONDS.sleep(3);//抢到车位后暂停3秒
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 每个线程执行完成后释放掉该车位
                }
            },String.valueOf(i)).start();
        }


    }
}
