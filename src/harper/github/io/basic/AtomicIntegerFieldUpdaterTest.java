package harper.github.io.basic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {

        // 设置柯南的年龄10岁
        User c = new User("conan", 10);
        // 柯南涨了一岁
        System.out.println(updater.getAndIncrement(c));
        // 查询现在的年龄
        System.out.println(c.getOld());

    }

    public static class User {
        private String name;

        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }

}
