package harper.github.io.basic;

import java.util.concurrent.TimeUnit;

/**
 * 线程的六个状态
 *
 * @Project ThreadStateSample(harper.github.io.basic)
 * @Author Harper Yang
 * @Date 2020/3/12 23:44
 * @Version v2.5.0
 */
public class ThreadStateSample {

    public static void main(String[] args) {

        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();

    }

    // 该线程不断的进行睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {

            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    // 该线程在Blocked.class 实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while ((true)) {
                    SleepUtils.second(100);
                }
            }
        }
    }



    public static class SleepUtils {
        public static final void second(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
