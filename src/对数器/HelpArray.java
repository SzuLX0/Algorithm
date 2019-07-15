package 对数器;

import java.util.Arrays;

import static 排序.Sort.mergeSort;

/**
 * 此对数器用于验证排序算法的正确性
 */
public class HelpArray {

      /*
        对数器：
        作用：
            1） 验证结果
            2） 输出错误结果
            3） 验证贪心策略
        组成：
            1）产生随机样本的产生器
            2) 绝对正确的方法
            3） 大样本测试
     */

    //for test：随机样本生成器
    public static int[] generateRandomArray(int size, int value){
//        Math.random() ->  double[0,1]
//        int random = (int)((size+1) * Math.random()); -> [0,size]整数
//        size = 6, size+1 = 7
//        Math.random() -> [0,1) * 7 -> [0,7) double
//        double -> int[0,6]->int

        //生成随机长度的数组
        int[] arr = new int[(int)((size+1)*Math.random())];

        for(int i=0; i<arr.length; i++){
            arr[i] = (int) ((value+1)*Math.random()) - (int)((value)*Math.random());
        }
        return arr;
    }

    //for test: 绝对正确的方法
    private static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    //for test: 大样本测试
    public static void main(String [] args){
        int testTime = 50000;
        int size = 10;
        int value = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(size, value);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
//            bubbleSort(arr1);
//            selectSort(arr1);
//            insertSort(arr1);
            mergeSort(arr1);
            rightMethod(arr2);
            if(!isEqual(arr1, arr2)){
                succeed = false;
                printArray(arr3);
                printArray(arr1);
                break;
            }
        }
        System.out.println(succeed ? "nice" : "wrong");

    }

    //判断两个数组是否相等
    private static boolean isEqual(int[] arr1, int[] arr2){
        if( (arr1==null && arr2!=null) || (arr1!=null && arr2==null))
            return false;
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1.length != arr2.length)
            return false;
        for(int i=0; i<arr1.length; i++){
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    private static void printArray(int[] arr) {
        if(arr == null)
            return ;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    //复制数组
    private static int[] copyArray(int[] arr1) {
        if(arr1 == null)
            return null;

        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }

}
