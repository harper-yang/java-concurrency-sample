package harper.github.io.practice;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {


    /**
     * ThreadPoolExecutor 执行步骤
     * <p>
     * <p>
     * 1，如果当前运行的线程少于核心线程，则创建新线程来执行任务（注意这一步骤需要获取全局锁，非常消耗资源）
     * 2，如果运行的线程多余核心线程，则将任务加入到queue中
     * 3，如果queue任务满了，则创建新的线程来执行
     * 4，如果新的线程超过最大线程数，则任务被拒绝，同时执行拒绝策略
     * <p>
     * 第一步初始化会执行，所以几乎所有的任务都由第二步来执行
     */

    public static long test1() {



        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 1,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>(16), Executors.defaultThreadFactory());


        long completedTaskCount = executor.getCompletedTaskCount();

        return completedTaskCount;
    }


    public void test2() {


        // FixThreadPool ,使用固定线程数的场景，适用于负载中的场景
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // SingleThreadExecutor, 单个线程，保证顺序执行各个任务
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        // CacheThreadPool ,是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者负载较轻的服务器
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        // ScheduledThreadPoolExecutor
        ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(10); // 包含多个线程的 周期任务线程池

        ScheduledExecutorService executorService4 = Executors.newSingleThreadScheduledExecutor(); // 包含一个线程的周期任务线程池

        Future<?> future = executorService.submit(() -> {
            System.out.println("200");
        });



    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 1,
//                TimeUnit.MINUTES, new LinkedBlockingQueue<>(16), Executors.defaultThreadFactory());
//        executor.execute(() -> {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        while (true) {
//
//            System.out.println(executor.getCompletedTaskCount());
//        }

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        Future<String> submit = pool.submit(() -> {
            Thread.sleep(10000);
            return "回家好好好好好";


        });
        String s = submit.get();
        System.out.println(s);

    }
}
