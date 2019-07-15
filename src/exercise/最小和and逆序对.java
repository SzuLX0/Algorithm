package exercise;

/**
 * 小和问题和逆序对问题
 *         小和问题
 *         在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组
 *         的小和。
 *         例子：
 *         [1,3,4,2,5]
 *         1左边比1小的数，没有；
 *         3左边比3小的数，1；
 *         4左边比4小的数，1、3；
 *         2左边比2小的数，1；
 *         5左边比5小的数，1、3、4、2；
 *         所以小和为1+1+3+1+1+3+4+2=16
 */

public class 最小和and逆序对 {

    /**
     * 利用归并排序的思想，在合并的时候找到小和
     * @param arr
     * @return
     */
    public static int getSmallSum(int[] arr){
        if(arr == null || arr.length<2)
            return 0;
        return mergeSort(arr, 0, arr.length-1);
    }

    private static int mergeSort(int[] arr, int l, int r) {
        if(l==r){
            return 0;
        }
        int mid = l + ((r-l)>>1);
        return mergeSort(arr, l, mid) + mergeSort(arr, mid+1, r) + merge(arr, l, mid, r);

    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r-l+1];
        int p = l;
        int q = mid+1;
        int i = 0;
        int res = 0;

        while(p<=mid && q<=r){
            //例如：{1，3，4} 与 {2，5}合并，当发现左边1比右边2小，则2右边所有的数都比1大
            //此时产生了2*1小和
            res += arr[p]<arr[q] ? arr[p]*(r-q+1) : 0;
            help[i++] = arr[p]<arr[q] ? arr[p++] : arr[q++];
        }

        while(p<=mid){
            help[i++] = arr[p++];
        }

        while(q<=r){
            help[i++] = arr[q++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l+j] = help[j];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 5};
        int[] a1 = {1, 3, 4, 2, 5};
        int[] a2 = {7, 5, 6, 4};
        System.out.println(getSmallSum(a));
        getSmallSum1(a2);
    }


    /**拓展：
     * 逆序对问题
     * 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序
     * 对。
     *
     * 思路：同样利用归并排序合并时的过程
     */

    public static void getSmallSum1(int[] arr){
        if(arr == null || arr.length<2)
            return ;
        mergeSort1(arr, 0, arr.length-1);
    }

    private static void mergeSort1(int[] arr, int l, int r) {
        if(l==r){
            return ;
        }
        int mid = l + ((r-l)>>1);
        mergeSort1(arr, l, mid);
        mergeSort1(arr, mid+1, r);
        merge1(arr, l, mid, r);
    }

    public static void merge1(int[] arr, int l, int mid, int r){
        int[] help = new int[r-l+1];
        int p = l;
        int q = mid+1;
        int i=0;

        while(p<=mid && q<=r){
            if(arr[p]>arr[q]){
                System.out.println("("+arr[p]+" , "+arr[q]+")");
            }
            help[i++] = arr[p]<arr[q] ? arr[q++] : arr[p++];
        }

        while(p<=mid){
            help[i++] = arr[p++];
        }

        while(q<=r){
            help[i++] = arr[q++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l+j] = help[j];
        }

    }
}
