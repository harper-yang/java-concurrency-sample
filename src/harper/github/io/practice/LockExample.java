package harper.github.io.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        // 不要将获取锁的过程写在try catch中，如果再获取锁时发生异常，异常抛出 的时候，也会导致锁无故释放
        lock.lock();

        try {
            // do something
        }finally {
            lock.unlock();
        }

    }

}
