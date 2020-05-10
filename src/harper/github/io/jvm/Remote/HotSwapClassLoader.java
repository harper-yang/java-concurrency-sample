package harper.github.io.jvm.Remote;

/**
 * 为了多次载入执行类而加入的加载器。
 * 有虚拟机调用时，仍然按照原有的双亲委派规则使用loadClass 方法进行类加载
 *
 * @Project HotSwapClassLoader(harper.github.io.jvm.Remote)
 * @Author  Harper Yang
 * @Date    2020/5/2 19:05
 * @Version v2.5.0
 */
public class HotSwapClassLoader extends ClassLoader{

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());

    }
    // 所作的事情仅仅是公开父类的defineClass()
    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
