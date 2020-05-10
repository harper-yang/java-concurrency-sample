package harper.github.io.practice;

public interface ThreadPool <Job extends Runnable>{

    // 执行 任务
    void execute(Job job);

    //关闭线程池
    void shutdown();

    // 增加工作者线程
    void addWorkers(int num);

    //减少工作者线程
    void removeWorker(int num);

    // 得到正在执行任务的线程数量
    int getJobSize();
}
