package algorithm;

/**
 调整数组顺序使奇数位于偶数之前
 */
public class ReorderArray {
    /**
     输入一个整数数组，实现一个函数来调整该数组中数组的顺序，使得所有的奇数位于数组的前半部分，偶数位于数组的后半部分

     思路:
     题目要求所有奇数都应该在偶数前面，所以我们应该只需要维护两个下标值，让一个下标值从前往后遍历，另外一个下标值从后往前遍历，
     当发现第一个下标值对应到偶数，第二个下标值对应到奇数的时候，我们就直接对调两个值。
     直到第一个下标到了第二个下标的后面的时候退出循环。

     我们有了这样的想法，可以先拿一个例子在心中走一遍，如果没有问题再写代码，这样也可以让面试官知道，我们并不是那种上来就开始写代码不考虑全面的程序员。
     假定输入的数组是 {1，2，3，4，5}；
     设定 odd = 0，代表第一个下标；第二个下标 even = arr.length = 4；
     从前往后移动第一个下标 odd，直到它等于偶数，即当 odd = 1 的时候，我们停止移动；
     再从后往前移动下标 even，直到它等于奇数，即当 even = 4 的时候，我们停止移动；
     满足 arr[odd] 为偶数，arr[even] 为奇数，我们对调两个值，得到新数组 {1，5，3，4，2}；
     继续循环，此时 odd = 3,even = 2，不满足 odd < even 的条件，退出循环，得到的数组符合条件
     */
    public static int[] reOrderArray(int[] array) {
        int odd = 0 , even = array.length -1;
        //循环条件 odd >= even
        while (odd < even) {
            //取模2 除2能除不尽 10除3除不尽，商3，余1，所有10%3 = 1
            //注意内层循环要加上判断条件odd < even,两个左边相邻时也要处理

            //第一个下标到偶数时停止,不再++,不到偶数一直循环 ,去负,绝对值
            while (odd < even && Math.abs(array[odd] % 2) != 0) {
                odd ++;
            }
            //第二个下标到奇数停止,到偶数继续走
            while (odd < even && Math.abs(array[even] % 2) == 0) {
                even --;
            }
            //第一个下标找到偶数 第二个下标找到了奇数, 对调
            int temp = array[odd];
            array[odd] = array[even];
            array[even] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        arr = reOrderArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] arr1 = {2, 4, 6, 8, 1, 3, 5, 7, 9};
        arr1 = reOrderArray(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        int[] arr2 = {2, 4, 6, 8, 10};
        arr2 = reOrderArray(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
    }

    //延伸思考 参考: https://yq.aliyun.com/articles/614191
}
