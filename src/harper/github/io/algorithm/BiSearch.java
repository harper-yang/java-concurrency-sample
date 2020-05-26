package harper.github.io.algorithm;

/**
 * 二分法查询
 *
 * @Project BiSearch(harper.github.io.algorithm)
 * @Author  Harper Yang
 * @Date    2020/5/25 22:15
 * @Version v2.5.0
 */
public class BiSearch {


    public static int biSearch(int[] array, int a) {

        int start = 0;

        int end = array.length - 1;

        int mid;

        while (start < end) {
            mid = (start + end) / 2;

            if (array[mid] == a) {
                return mid + 1;
            } else if (array[mid] < a) {
                start = mid + 1;
            }  else {
                end = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(biSearch(array, 3));
    }
}
