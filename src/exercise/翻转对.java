package exercise;


/**
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 *
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 解题思路：
 *      这题很容易联想到逆序对问题，利用归并排序的思想来解决。
 *      有几个比较坑的点:
 *          1)注意比较时可能会产生越界，边界值*2会越界，因此在转换成long类型，nums[i]*2L.
 *          2）考虑到有负数的情况，a<b 有可能 a>2*b,如-3<-2，-3>-4。因此在归并的时候若左边小于右边，也有可能满足条件
 *
 *  解答过程：
 *      在归并时利用三个指针，用于左、右数组left、right，一个用于找到满足条件的值flag（mid+1）；
 *      满足条件的值只可能在左边，遍历左数组同时完成合并操作
 *      1）左数组的值小于右边，help[i++] = nums[left++]
 *      2)左数组的值大于等于右边：
 *          a.判断是否满足flag<=end&&nums[left]>2L*nums[flag]，若该点满足则符合条件值有mid-left+1个，移动flag看下一个点是否满足
 *          b.移动right直到nums[right]>=nums[left]
 *
 *      例：左边=[-1,1,1] 右边=[-1,-1，1]，mid=2
 *       初始：        left    right   flag   count    help
 *                      0       3       3       0       []
 *       1)满足条件，移动两次flag并记录count：
 *                left    right   flag   count      help
 *               0       3       5       6          []
 *      2)归并过程，移动left
 *                left    right   flag   count      help
 *                1       3       5       6         [-1]
 *       3）此时右边更小，移动两次右边直到左边小
 *                  left    right   flag   count    help
 *                  1       5       5       6       [-1,-1,-1]
 *        4）完成归并过程
 *         *          left    right   flag   count    help
 *  *                  1       5       5       6       [-1,-1,-1,1,1,1]
 */
public class 翻转对 {
    public int reversePairs(int[] nums) {
        if(nums==null || nums.length<2)
            return 0;
        return help(nums, 0, nums.length-1);
    }

    private int help(int[] nums, int start, int end){
        if(start==end)
            return 0;
        int mid = start + (end-start)/2;

        return help(nums, start, mid)+help(nums, mid+1, end)+merge(nums,start, mid,end);
    }

    private int merge(int[] nums, int start, int mid, int end){
        int[] help = new int[end-start+1];
        int left = start;
        int right = mid+1;
        int i=0;
        int count = 0;
        int flag = mid+1;

        while(left<=mid){
            while(flag<=end&&nums[left]>2L*nums[flag]){
                flag++;
                count+=mid-left+1;
            }
            while(right<=end && nums[left]>nums[right]){
                help[i++] = nums[right++];
            }
            help[i++] = nums[left++];
        }

        while(right<=end)
            help[i++] = nums[right++];

        for(i=0; i<help.length; i++){
            nums[start+i] = help[i];
        }

        return count;
    }
}
