package harper.github.io.basic;

public class VolatileSample {

    volatile long v1 = 0L;

    // 写 = 普通变量的写
    private void set(long i) {
        v1 = i;

    }

    // 复合读写操作
    private void getAndIncrement() {
        v1++;
    }

    // 读 = 普通变量的读
    private long get() {
        return v1;
    }
}
