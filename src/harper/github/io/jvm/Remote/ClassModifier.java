package harper.github.io.jvm.Remote;

public class ClassModifier {

    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    private static final int CONSTANT_UTF8_INFO = 1;

    private static final int[] CONSTANT_ITEM_LENGTH = {
            -1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5
    };

    private static final int u1 = 1;

    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }


}
