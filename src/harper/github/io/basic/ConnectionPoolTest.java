package harper.github.io.basic;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池测试
 * @link p209
 *
 * @Project ConnectionPoolTest(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/14 0:51
 * @Version v2.5.0
 */
public class ConnectionPoolTest {

    private static ConnectionPool pool = new ConnectionPool(10);

    // 保证所有的ConnectionRunner能够同时开始
    static CountDownLatch start = new CountDownLatch(1);

    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count,got,notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }

        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
    }


    static class ConnectionRunner implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGOT) {
            this.count = count;
            this.got = got;
            this.notGot = notGOT;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {

            }
            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        connection.createStatement();
                        connection.commit();
                        got.incrementAndGet();
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    count --;
                }
            }


        }
    }
}

