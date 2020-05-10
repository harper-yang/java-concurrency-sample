package harper.github.io.basic;

import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockSample {

    int a = 0;

    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();
        try {
            a ++;
        }finally {
            lock.unlock();
        }
    }


    public void reader() {

        lock.lock();
        try {
            int i = a;
        }finally {
            lock.unlock();
        }
    }

}
