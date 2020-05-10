package harper.github.io.basic;

import harper.github.io.practice.Profiler;

/**
 * 检测并发是否一定比串行快
 *
 * @Project ConcurrencyTest(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/9 11:51
 * @Version v2.5.0
 */
public class ConcurrencyContextSample {

    private static final long COUNT = 10000L;

    /**
     * 测试结果：当循环的数字越大时，并发的优势会慢慢体现出来
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {

        Profiler.begin();

        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < COUNT; i++) {
                a += 5;
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }

        thread.join();
        System.out.println("cost:" + Profiler.end() + "mills");
    }
    private static void serial() {
        Profiler.begin();
        int a = 0;
        for (long i = 0; i < COUNT; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }

        System.out.println("cost:" + Profiler.end() + "mills");
    }


}
