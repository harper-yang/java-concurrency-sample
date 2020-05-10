package harper.github.io.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    public static class GoodBye implements IHello {

        @Override
        public void sayHello() {
            System.out.println("say goodBye");
        }
    }

    static class DynamicProxy<T> implements InvocationHandler {
        T originalObj;

        T bind(T originalObj) {
            this.originalObj = originalObj;
            return (T) Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(),
                    this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }

        public static void main(String[] args) {
            IHello hello = new DynamicProxy<IHello>().bind(new Hello());
            hello.sayHello();
            IHello hello2 = new DynamicProxy<IHello>().bind(new GoodBye());
            hello2.sayHello();
        }
    }
}
