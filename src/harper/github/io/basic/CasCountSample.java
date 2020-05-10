package harper.github.io.basic;

import com.sun.corba.se.impl.oa.poa.AOMEntry;
import harper.github.io.practice.Profiler;
import org.w3c.dom.css.Counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于cas的计数器
 *
 * @Project CasCountSample(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/10 0:48
 * @Version v2.5.0
 */
public class CasCountSample {

    private AtomicInteger atomicI = new AtomicInteger(0);

    private int i = 0;

    /**
     * 997338
     * 1000000 cas 结果正确
     * cost:97mills
     * @param args
     */
    public static void main(String[] args) {

        final CasCountSample cas = new CasCountSample();

        List<Thread> ts = new ArrayList<>(600);

        Profiler.begin();

        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.count();

                    cas.safeCount();
                }

            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }


        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println("cost:" + Profiler.end() + "mills");
//        System.out.println(System.currentTimeMillis() - start);

    }

    /**
     * 使用cas实现线程安全的计数器
     */
    private void safeCount() {
        for (; ; ) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }


}
