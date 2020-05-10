package harper.github.io.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 展示读写锁如何使用
 *
 * @Project Cache(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/18 0:08
 * @Version v2.5.0
 */
public class Cache {

    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {

            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    // 设置key对应的value，返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {

            return map.put(key, value);
        }finally {
            w.unlock();
        }
    }

    // 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
