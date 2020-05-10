package harper.github.io.basic;

public class FinalSample {

    int i; // 普通变量

    final int j; // final 变量

    static FinalSample obj;

    public FinalSample() {  //构造
        i = 1;
        j = 2;
    }

    public static void writer() { // 写程序A执行
        obj = new FinalSample();
    }

    public static void reader() { // 读程序B执行
        FinalSample object = obj;   //读对象引用
        int a = obj.i;              // 读普通域
        int b = obj.j;               //读final域
    }

}
