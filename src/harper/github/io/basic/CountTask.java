package harper.github.io.basic;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算 1+2+3+4 的结果
 *
 * @Project CountTask(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/27 23:42
 * @Version v2.5.0
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THREADSHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THREADSHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i ;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle, end);
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算
        CountTask task = new CountTask(1, 4);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        try {
            // 获得结果
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
