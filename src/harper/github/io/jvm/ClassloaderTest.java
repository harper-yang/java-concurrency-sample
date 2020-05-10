package harper.github.io.jvm;

import java.io.IOException;
import java.io.InputStream;

public class ClassloaderTest {

    // 第一个是自定义的类加载器加载的，第二个是系统自带的程序类加载器加载的
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("harper.github.io.jvm.ClassloaderTest").newInstance();
        System.out.println(obj.getClass());

        System.out.println(obj instanceof ClassloaderTest  );
    }

}