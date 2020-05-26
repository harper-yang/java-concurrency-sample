package harper.github.io.algorithm;

import javax.net.ssl.SSLContext;

/**
 * 排序 算法
 *
 * @Project Sort(harper.github.io.algorithm)
 * @Author Harper Yang
 * @Date 2020/5/25 22:20
 * @Version v2.5.0
 */
public class Sort {

    //      冒泡排序
//    （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
//    （2）这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1 个位置。
//    （3）N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
    public static void bubbleSort(int[] array) {

        int i;

        int j;


        for (i = 0; i < array.length; i++) {
            // i的循环是为了对n个数做同样的操作
            for (j = 1; j < array.length - i; j++) {
                // j的循环结果是将最大的放到最后面
                if (array[j - 1] > array[j]) { // 前面的数字大于后面的数字则交换
                    int temp;
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // 插入排序
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            // 插入的数
            int insertVal = arr[i];
            // 被插入的位置（准备与前一个数比较）
            int index = i - 1;
            while (index >= 0 && insertVal < arr[index]) {
                arr[index + 1] = arr[index];

                index--;
            }
            arr[index + 1] = insertVal;
        }
    }

    // 快速排序
    public static void quickSort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)
//如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)
//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }

        }
        if (start > low) quickSort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) quickSort(a, end + 1, high);//右边序列。从关键值索引+1 到最后一个
    }

    public static void main(String[] args) {
        int[] array = {3, 2, 1, 34, 5, 6, 100, 99, 22, 333, 88};
//        bubbleSort(array);

//        insertSort(array);

        quickSort(array,0,array.length - 1);
        for (int a : array) {
            System.out.println(a);
        }
    }
}
