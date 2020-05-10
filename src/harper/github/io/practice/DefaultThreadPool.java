package harper.github.io.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 最大线程数
    private static final int MAX_WORKER_NUMBERS =10;

    // 默认线程数
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    // 最小线程数
    private static final int MIN_WORKER_NUMBERS = 1;

    // 任务队列
    private final LinkedList<Job> jobs = new LinkedList<>();

    // 线程池的线程数
    private final List<Worker> workers = new ArrayList<>();

    private int workerNum = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(workerNum);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : MIN_WORKER_NUMBERS;
        initializeWorkers(num);
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            // 限制新增的worker不能超过最大数
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - workerNum;
            }

            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
        }
        // 按照给定的数量停止Worker
           int count = 0;
        while (count < num) {
            Worker worker = workers.get(count);
            if (workers.remove(worker)) {
                worker.shutdown();
                count++;
            }
        }
        this.workerNum -= count;
    }


    @Override
    public int getJobSize() {
        return jobs.size();
    }
    // 初始化线程工作者
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    public class Worker implements Runnable {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job;
                synchronized (jobs) {
                    // 如果工作列表是空的，那么就wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对WorkerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }

                if (job != null) {
                    job.run();
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }

    public static void main(String[] args) {
        ThreadPool<TestThread> pool = new DefaultThreadPool<>();
        List<TestThread> list = new ArrayList<>();
        list.add(new TestThread());
        list.add(new TestThread());
        list.add(new TestThread());
        list.add(new TestThread());
        list.add(new TestThread());
        list.add(new TestThread());
        list.add(new TestThread());
        list.add(new TestThread());
        for (TestThread testThread : list) {
            pool.execute(testThread);

        }

    }

    public static class TestThread implements Runnable {

        @Override
        public void run() {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
        }
    }
}
