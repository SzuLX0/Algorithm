package 排序;

public class Bucket {
    /**
     * 桶排序
     * 计数排序
     */


    /**
     * 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时
     * 间复杂度O(N)，且要求不能用非基于比较的排序。
     */

    public int maximumGap(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //找到数组的最大值和最小值
        for(int i=0; i<len; i++){
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }

        if(max==min){
            return 0;
        }
        //准备三个数组保存桶的信息，准备N+1个桶
        boolean[] hasNum = new boolean[len+1];
        int[] mins = new int[len+1];
        int[] maxs = new int[len+1];

        //遍历数组，将每个数放入对应的桶中，并更新每个桶的最大值和最小值
        //更新时要先判断桶中是否有数据
        for(int i=0; i<len; i++){
            int flag = bucket(nums[i], len, max, min);

            System.out.println(flag);
            System.out.println(bucket(nums[i], len, max, min, true));
            mins[flag] = hasNum[flag]? Math.min(nums[i], mins[flag]) : nums[i];
            maxs[flag] = hasNum[flag]? Math.max(nums[i],maxs[flag]) : nums[i];
            hasNum[flag] = true;
        }

        //遍历所有的桶，找到每一个非空桶的最小值与它前面最近非空桶的最大值的差值，从中选出最大值即所求
        int res = 0;
        int lastMax = maxs[0];
        for(int i=1; i<=len; i++){
            if(hasNum[i]){
                res = Math.max((mins[i]-lastMax),res);
                lastMax = maxs[i];
            }
        }

        return res;

    }

    //注意这里用long，不然可能会溢出
    public static int bucket(long num, long len,long max, long min){
        return (int) ((num-min)*len/(max-min));
    }
    public static int bucket(int num, int len,int max, int min, boolean flag){
        return (int) ((num-min)*len/(max-min));
    }
}
