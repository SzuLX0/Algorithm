package 排序;

import java.util.Arrays;

/**
 * 1. 排序
 * 2. 对数器：
 *         作用：
 *             1） 验证结果
 *             2） 输出错误结果
 *             3） 验证贪心策略
 *         组成：
 *             1）产生随机样本的产生器
 *             2) 绝对正确的方法
 *             3） 大样本测试
 */

public class Sort {

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }

        //改进：使用标记
        int flag=0;
        for(int end = arr.length-1; end>0; end--){
            flag = 0;
            for(int i=0; i<end; i++){
                if(arr[i]>arr[i+1]){
                    swap(arr, i, i+1);
                    flag = 1;
                }
            }
            if(flag==0)
                break;
        }
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        int temp;
        if(arr==null || arr.length<2)
            return;
        for(int i=0; i<arr.length-1; i++){
            temp = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[temp]>arr[j])
                    temp = j;
            }
            swap(arr, temp, i);
        }
    }


    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr){
        if(arr == null || arr.length<2)
            return ;

        for(int i=1; i<arr.length; i++){
            for(int j=i-1; j>=0 && arr[j]>arr[j+1]; j--){
                swap(arr, j, j+1);
            }
        }
    }

    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length<2){
            return ;
        }
        sortProcess(arr, 0, arr.length-1);
    }


    private static void sortProcess(int[] arr, int l, int r){
        if(l==r){
            return ;
        }
        int mid = l + ((r-l)>>1);

        sortProcess(arr, l, mid);
        sortProcess(arr, mid+1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r-l+1];    //生成辅助数组
        int p = l;
        int q = mid+1;
        int i=0;

        while(p<=mid && q<=r){
            help[i++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        }

        while(p<=mid)
            help[i++] = arr[p++];

        while(q<=r)
            help[i++] = arr[q++];

        for (int j = 0; j < help.length; j++) {
            arr[l+j] = help[j];
        }
    }


    private static void swap(int[] arr,int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        //交换的另一种写法:交换相同的会为0需要注意
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];
    }
}
