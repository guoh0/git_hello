package algorithm;

/**
 * 只出现一次的数字 [ LeetCode ]
 */
public class SngleNumber {
    /** 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
        说明：
        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

        示例 1:
        输入: [2,2,1]
        输出: 1

        示例 2:
        输入: [4,1,2,1,2]
        输出: 4

     题目中的重点要求：
     　　1、线性时间复杂度：要求我们的代码时间复杂度最高为O(n)，不能有嵌套循环等。
     　　2、不使用额外空间：要求空间复杂度最高为O(1)。
     除此之外，还有重要的信息：
     除了某个元素只出现一次以外，其余每个元素均出现两次。

     思路：
     根据异或运算的特点，相同的数字经过异或运算后结果为0，除单独出现一次的数字外，其他数字都是出现两次的，
     那么这些数字经过异或运算后结果一定是0。
     而任何数字与0进行异或运算都是该数字本身。所以对数组所有元素进行异或运算，运算结果就是题目的答案
    **/
    public static int singleNumber(int[] nums) {
        int num = 0;
        for (int i =0 ;i< nums.length; i++){
            num = num ^ nums[i];
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{4,1,2,1,2}));
    }
}
