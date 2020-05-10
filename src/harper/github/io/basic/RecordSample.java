package harper.github.io.basic;

public class RecordSample {

    int a = 0;

    boolean flag = false;

    private void writer() {
        a = 1 ;
        flag = true;
    }

    private void reader() {
        if (flag) {
            int i = a * a;
        }
    }
}
