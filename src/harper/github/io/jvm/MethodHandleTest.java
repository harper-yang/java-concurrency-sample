package harper.github.io.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

public class MethodHandleTest {

    static class ClassA{
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {

        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();

        getPrintln(obj);
    }

    private static MethodHandle getPrintln(Object reveiwer) throws NoSuchMethodException, IllegalAccessException {
        // MethodType 代表 方法类型，第一个参数：方法的返回值，第二个参数：具体参数
        MethodType methodType = MethodType.methodType(void.class, String.class);
        // lookup()：在指定类中查找符合给定的方法名称，方法类型，并且符合掉哟个权限的方法句柄

        return lookup().findVirtual(reveiwer.getClass(),
                "println",
                methodType).bindTo(reveiwer);
    }
}
