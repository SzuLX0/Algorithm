package 排序;

public class HeapSort {

    /**
     * 1. 数组与完全二叉树转换： i的左子树：2*i+1, i的右子树： 2*i+2
     * 2. 堆-->完全二叉树
     *      1） 大根堆：任何一颗子树的最大值是这棵子树的头部
     *      2） 小根堆：任何一颗子树的最小值是这棵子树的头部
     *
     *  3. 构建堆复杂度：O(N),每加入一个点比较树的高度次。 heapInsert
     *  4. heapify:数值变化，重新调整
     */


    /**
     * 自下往上调整：可用来添加一个元素，或者最大堆中某个元素增大，可以用来调整堆的结构
     * 添加一个元素的复杂度为树的高度，构建一个最大堆的复杂度为：O(log 1+log 2+...+log N )= O(N)
     * @param arr
     * @param index：
     */
    public static void heapInsert(int[] arr, int index){
        while( arr[index] > arr[(index-1)/2]){  //父结点：(index-1)/2
            swap(arr, index, (index-1)/2);      //交换父结点与最大值
            index = (index-1)/2;    //更新结点位置
        }
    }

    /**
     * 调整堆的结构：最大堆中某个元素变小时进行向下调整，若元素值变大可调用heapInsert向上调整
     * 思路：从该结点左右孩子中找到较大的那个，与发生改变的结点进行比较，
     * 若改变后仍是最大值，则无需调整；否则将最大值进行交换，对交换后的结点重复该过程
     * @param arr：数组
     * @param heapsize：堆大小
     * @param index：值发生改变的索引
     */
    public static void heapify(int[] arr, int heapsize, int index){
        int left = index*2+1;   //左孩子
        while(left<heapsize){   //左孩子没有越界
            int largest = (left+1 < heapsize && arr[left]<arr[left+1])  //右孩子没有越界且大于左孩子
                    ? left+1
                    : left;

            largest = arr[largest]>arr[index]   //比较调整后与孩子的值，选出最大值
                    ? largest
                    : index;

            if(largest == index)
                break;  //如果变小后还是最大值，则无需调整

            swap(arr, index, largest);  //将值较大的孩子换上去
            //更新索引的值
            index = largest;
            left = index*2+1;
        }
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 堆排序
     *  1）构建一个大根堆
     *  2）每次将队顶的值与堆底交换，调整堆
     */
    public static void heapSort(int[] arr){

        if(arr == null || arr.length<2)
            return;

        int heapSize = arr.length;

        //构建一个最大堆
        for (int i = 0; i < heapSize; i++) {
            heapInsert(arr, i);
        }

        //每次将堆最大值放到堆末尾，重新调整堆
        swap(arr, --heapSize, 0);
        while(heapSize>0){
            heapify(arr,heapSize,0);
            swap(arr, --heapSize, 0);
        }
    }


    /**
     * 给一个数字流，随时给出中位数：
     *      构建两个堆
     */


    /**
     * 综合排序:
     *      1)基本类型:快排；因为基本类型排序不在乎稳定性，都是一样
     *      2）自定义类型：归并
     *      3）数组长度小于60：插入；常数项低
     */


    /***
     * 快排partition部分没有稳定性； 01 stable sort可以实现
     * 若问：时间复杂度O(N), 空间复杂度O(1)，能否实现稳定的将一个数组奇数、偶数各放一边，本质上与快排一样，不能实现
     *
     * 50min
     */

    /**
     * 桶排序
     * 计数排序
     */


    /**
     * 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时
     * 间复杂度O(N)，且要求不能用非基于比较的排序。
     */
}
