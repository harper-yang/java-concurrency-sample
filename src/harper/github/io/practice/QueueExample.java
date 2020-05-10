package harper.github.io.practice;

import java.util.concurrent.*;

public class QueueExample {


    /**
     * 阻塞队列的四种处理方式
     */
    public void test1() throws InterruptedException {

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        //抛出异常
        queue.add("aaa");
        queue.remove();

        // 返回特殊值
        queue.offer("bbb");
        queue.poll();

        // 一直阻塞
        queue.put("ccc");
        queue.take();


    }

    /**
     * 七种队列
     */
    public void test2() {

        // 由数组组成的有界阻塞队列
        ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue<>(16);

        // 由链表组成的有界阻塞队列 , 吞吐量通常高于ArrayBlockQueue,静态工厂方法Executors.newFixedThreadPool()使用了这个队列
        LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<>(16);

        // 支持优先级排序 的无界阻塞队列
        PriorityBlockingQueue queue3 = new PriorityBlockingQueue();

        // 支持延时获取元素的无界阻塞队列
        DelayQueue<Delay> queue4 = new DelayQueue<>();

        // 不存储元素的阻塞队列， 每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直阻塞 ，吞吐量高于LinkedBlockingQueue,
        // Executors.newCachedThreadPool() 使用了这个队列
        SynchronousQueue<String> queue5 = new SynchronousQueue<>();

        // PriorityBlockingQueue ，具有优先级的无线阻塞队列


    }


    public class Delay implements Delayed {

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
