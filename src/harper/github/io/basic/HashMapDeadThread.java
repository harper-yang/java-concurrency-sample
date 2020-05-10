package harper.github.io.basic;

import java.util.HashMap;
import java.util.UUID;

/**
 * 会引起死循环的 hashMap
 *
 * @Project HashMapDeadThread(harper.github.io.basic)
 * @Author  Harper Yang
 * @Date    2020/3/21 0:27
 * @Version v2.5.0
 */
public class HashMapDeadThread {

    public static void main(String[] args) throws InterruptedException {

        final HashMap<String, String> map = new HashMap<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                }, "ftf" + i).start();
            }

        }, "ftf");

        thread.start();
        thread.join();

    }
}
