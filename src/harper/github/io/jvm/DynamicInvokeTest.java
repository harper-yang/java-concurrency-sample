package harper.github.io.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

public class DynamicInvokeTest {

    public static class GrandFather {
        void thinking2() {
            System.out.println("i am grandfather");
        }
    }

    public static class Father extends GrandFather {
        void thinking1() {
            System.out.println("i am father");
        }
    }

    public static class Son extends Father {

        void thinking() {

            // 如何填写一行代码，实现调用祖父类的thinking方法
            MethodType methodType = MethodType.methodType(void.class);
            try {
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking2",
                        methodType, getClass());
                mh.invoke(this);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Son().thinking();
    }
}
