package 排序;

public class QuickSort {

    /**
     * 引入问题一 描述:
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在数
     * 组的左边，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     *
     * 解题思路:
     *      使用一个变量less指向小于区域，less初始值为最左-1，表示为空；（0，less]
     *      一个变量cur用于遍历数组，初始值为最左
     *      遍历数组，若cur所指大于num，则只移动cur；
     *      若小于等于num，则交换cur与（less+1）对应的元素，less右移，cur右移
     *
     *      例：初始值：less=-1， cur=0，num=4 , a=[1,2,6,4,5,7,9]
     *      1）less=-1， cur=0，num=4 , a=[1,2,6,4,5,7,9]  -->  a[cur]<=num, swap(a[less+1], a[cur]), less++, cur++
     *      2)less=0,cur=1,a=[1,2,6,4,5,7,9]  -->  a[cur]<=num, swap(a[less+1], a[cur]), less++, cur++
     *      3)less=1,cur=2,a=[1,2,6,4,5,7,9]  -->  a[cur]>num,cur++
     *      4)less=1,cur=3,a=[1,2,6,4,5,7,9]  -->  cur<=num, swap(a[less+1], a[cur]), less++, cur++
     *      5)less=2,cur=4,a=[1,2,4,6,5,7,9]  -->  cur<=num, swap(a[less+1], a[cur]), less++, cur++
     *      ....
     *      7)less=2,cur=6,a=[1,2,4,6,5,7,9]  -->  cur<=num, swap(a[less+1], a[cur]), less++, cur++
     *      8)终止循环
     */
    public static void exchange(int[] a, int num, int L, int R){
        int less = L-1;
        int cur = L;

        while (cur<R){
            if(a[cur]>num){
                cur++;
            }else{
                swap(a, less+1, cur);
                cur++;
                less++;
            }
        }


    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /**
     * 问题二（荷兰国旗问题）
     * 给定一个数组arr，和一个数num，请把小于num的数放在数组的
     * 左边，等于num的数放在数组的中间，大于num的数放在数组的
     * 右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     *
     * 解题思路：
     *      上一题只分了两个区域，此题分三个区域，故只需增加一个变量more，表示大于nunm的区域即可
     *      使用一个变量less指向小于区域，less初始值为最左-1，表示为空，小区域区间为(L,less]；
     *      一个变量cur用于遍历数组，初始值为最左,等于区域区间为[less+1,more-1]
     *      变量more指向大于区域，初始值为最右+1，表示大区域为空,区间为[more,R)
     *
     *      过程:
     *          1）if a[cur]<num  --> swap(a[++less], a[cur++])
     *          2）if a[cur]==num  --> cur++
     *          3）if a[cur]>num  -->  swap(a[--more],a[cur])
     *         ( 注意这里3）不移动cur，因为右边的数是没有比较过的，交换后需要重新比较)
     *         终止条件： cur>=more
     */

    public static int[] helanFlag(int[] a, int num, int L, int R){
        int less = L-1;
        int more = R+1;
        int cur = L;

        while(cur<more){
            if(a[cur]<num){
                swap(a, cur++, ++less);
            }else if(a[cur]==num){
                cur++;
            }else{
                swap(a, cur, --more);
            }
        }
        return new int[] {less+1, more-1};
    }


    /**
     * 经典快排：O(n*logN) 空间复杂度:O(log N)
     *
     * 改进：
     *      1）利用荷兰国旗问题，使相等的值只用进行一次递归
     *      2）随机选取：避免原始数据状况干扰：随机；hash
     */
    public static void quickSort(int[] a, int L, int R){
        if(L<R){
            int p = partition(a, L, R);
            quickSort(a, L, p-1);
            quickSort(a, p+1, R);
        }
    }

    private static int partition(int[] a, int L, int R) {
        int temp = a[R];    //取最后一个元素作为基准
        while(L < R){
            while(a[L]<temp && L<R) L++;
            a[R] = a[L];

            while(a[R]>=temp && L<R) R--;
            a[L] = a[R];
        }
        a[L] = temp;
        return L;
    }


    /**
     * 使用随机选取元素优化
     * @param a
     * @param L
     * @param R
     */
    public static void quickSortHelan(int[] a, int L, int R){
        if(L<R) {
            swap(a, L + (int) (Math.random() * (R - L + 1)), R);    //随机选取一个元素与最后一个元素交换，作为基准
            int[] p = helanFlagPartition(a, L, R);
            quickSort(a, L, p[0]-1);
            quickSort(a, p[1]+1, R);
        }
    }

    /**
     * 使用荷兰国旗优化
     * 最后一个元素作为基准，不参与最开始的比较，因此more区域直接跳过最后一个元素
     * 当遍历完除最后一个元素的所有元素，将最后一个元素与more区域第一个元素进行交换
     * 此时相等范围为(less+1, more)
     * @param a
     * @param L
     * @param R
     * @return
     */
    public static int[] helanFlagPartition(int[] a,int L, int R){
        int num = a[R];

        int less = L-1;
        int more = R;   //最后一个元素作为基准，不参与最开始的比较，因此more区域直接跳过最后一个元素
        int cur = L;

        while(cur<more){
            if(a[cur]<num){
                swap(a, cur++, ++less);
            }else if(a[cur]==num){
                cur++;
            }else{
                swap(a, cur, --more);
            }
        }
        swap(a, more, R); //遍历完除最后一个元素的所有元素，将最后一个元素与more区域第一个元素进行交换

        return new int[] {less+1, more};
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4,2,6,4,9,7,11,0};
        int[] b = a;
        quickSort(a, 0, a.length-1);
//        quickSortHelan(a, 0, a.length-1);

        for (int i :
                    a) {
                System.out.print(i + " ");
            }
            System.out.println();
    }


//    public static void quickSort(int[] arr) {
//        if (arr == null || arr.length < 2) {
//            return;
//        }
//        quickSort(arr, 0, arr.length - 1);
//    }
//
//    public static void quickSort(int[] arr, int l, int r) {
//        if (l < r) {
//            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
//            int[] p = partition(arr, l, r);
//            quickSort(arr, l, p[0] - 1);
//            quickSort(arr, p[1] + 1, r);
//        }
//    }
//
//    public static int[] partition(int[] arr, int l, int r) {
//        int less = l - 1;
//        int more = r;
//        while (l < more) {
//            if (arr[l] < arr[r]) {
//                swap(arr, ++less, l++);
//            } else if (arr[l] > arr[r]) {
//                swap(arr, --more, l);
//            } else {
//                l++;
//            }
//        }
//        swap(arr, more, r);
//        return new int[] { less + 1, more };
//    }
//
//    public static void swap(int[] arr, int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
}
