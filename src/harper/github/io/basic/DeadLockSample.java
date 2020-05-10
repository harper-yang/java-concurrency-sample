package harper.github.io.basic;

/**
 * 死锁 sample
 *
 * @Project DeadLockSample(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/9 13:19
 * @Version v2.5.0
 */
public class DeadLockSample {

    private static String A = "A";

    private static String B = "B";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }

        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }

        });

        t1.start();
        t2.start();
    }
}
