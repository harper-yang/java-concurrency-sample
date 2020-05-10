package harper.github.io.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 同一时刻，只允许至多两个线程同时访问
 *
 * @Project TwinsLock(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/17 22:02
 * @Version v2.5.0
 */
public class TwinsLock implements Lock {

    // 设置资源数 2
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {

            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    // 验证twinsLock是否有效
    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();

                    try {
                        ThreadStateSample.SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        ThreadStateSample.SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }

                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔一秒换行
        for (int i = 0; i < 10; i++) {
            ThreadStateSample.SleepUtils.second(1);
            System.out.println();
        }

    }
}
