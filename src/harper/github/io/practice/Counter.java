package harper.github.io.practice;//package com.yangzhao.thread.test1;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 基于cas的原子操作
// *
// * @Project Counter(com.yangzhao.thread.test1)
// * @Author  Harper Yang
// * @Date    2019/10/7 18:15
// * @Version v1.6.0
// */
//public class Counter {
//    private AtomicInteger atomicI = new AtomicInteger(0);
//    private int i = 0;
//
//    public static void main(String[] args) {
//        final Counter cas = new Counter();
//        List<Thread> ts = new ArrayList<>(600);
//        long start = System.currentTimeMillis();
//        for (int j = 0; j < 100; j++) {
//            Thread t = new Thread(() -> {
//                for (int i = 0; i < 10000; i++) {
//                    cas.count();
//                    cas.safeCount();
//                }
//            });
//            ts.add(t);
//        }
//        for (Thread t : ts) {
//            t.start();
//        }    // 等待所有线程执行完成
//        //
//        for (Thread t : ts) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(cas.i);
//        System.out.println(cas.atomicI.get());
//        System.out.println(System.currentTimeMillis() - start);
//    }
//    /**        * 使用CAS实现线程安全计数器        */
//    private void safeCount() {
//        for (;;) {
//            int i = atomicI.get();
//            boolean suc = atomicI.compareAndSet(i, ++i);
//            if (suc) {
//                break;
//            }
//        }
//    }
//    /**     * 非线程安全计数器
//     */
//    private void count () {
//            i++;
//        }
//    }
//}
