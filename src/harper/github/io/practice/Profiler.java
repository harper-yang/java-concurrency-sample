package harper.github.io.practice;

import java.util.concurrent.TimeUnit;

/**
 * thread local example 获取方法的执行时间
 *
 * @Project Profiler(com.yangzhao.thread.test1)
 * @Author  Harper Yang
 * @Date    2019/10/8 16:44
 * @Version v1.6.0
 */
public class Profiler {


    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<>();


    public static void begin() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {

        Profiler.begin();

        TimeUnit.SECONDS.sleep(2);

        TimeUnit.SECONDS.sleep(20);

        System.out.println("cost:" + Profiler.end() + "mills");
    }
}
