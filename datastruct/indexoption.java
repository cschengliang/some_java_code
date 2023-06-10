package datastruct;

public class indexoption {
    //定义一个大小为1000的数组
    static int[] data = new int[100];

    //交换数组奇数位和偶数位的值，方法名为swap1
    public static void swap1(int... data) {
        for (int i = 0; i < data.length; i++) {
            if (i % 2 == 0) {
                int temp = data[i + 1];
                data[i + 1] = data[i];
                data[i] = temp;
            }
        }
    }

    //数组反转，推荐一个符合功能的方法名
    public static void reverse(int[] data) {
        int len = data.length;
        for (int i = 0; i < len / 2; i++) {
            int temp = data[i];
            data[i] = data[len - i - 1];
            data[len - i - 1] = temp;
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    //选择排序
    public static void selectSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = data[min];
                data[min] = data[i];
                data[i] = temp;
            }
        }
    }

    //   快速排序，添加注释
    public static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int middle = getMiddle(data, low, high);
            quickSort(data, low, middle - 1);
            quickSort(data, middle + 1, high);
        }
    }

    public static int getMiddle(int[] data, int low, int high) {
        int temp = data[high];

        while (low < high) {
            while (low < high && data[low] <= temp) {
                low++;
            }
            data[high] = data[low];
            while (low < high && data[high] >= temp) {
                high--;
            }
            data[low] = data[high];
        }

        data[high] = temp;
        return high;
    }

//    public static int getMiddle(int [] data,int low,int high){
//        int temp = data[low];
//        while (low < high){
//            while (low < high && data[high] >= temp){
//                high--;
//            }
//            data[low] = data[high];
//            while (low < high && data[low] <= temp){
//                low++;
//            }
//            data[high] = data[low];
//        }
//        data[low] = temp;
//        return low;
//
//    }
    //输出数组的值，方法名showData
    public static void showData(int... data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        swap1(data);
        reverse(data);
        quickSort(data, 0, data.length - 1);
        showData(data);

    }
}
