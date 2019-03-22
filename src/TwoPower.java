/**
 如何高效判断一个数是否是2的n次幂
 */
public class TwoPower {
    /**
     思路:
     一个数是否是2的幂次方，比较常用的是递归和移位运算进行判断。递归算法的思想很简单，就是不断的模上2去判断。 
     如果一个数是2的幂，那么它的二进制表示中就只有一位1，例如：10000，1000，100等等。
     所以如果对数字1进行移位操作，总会在移到某个位的时候和这个数相等。这就是移位判断的思想。 
     下面给出实现的代码，在实现中，还采用了第三种方式，因为二进制表示的2的幂次方数中只有一个1，后面跟的是n个0；
     因此问题可以转化为判断1后面是否跟了n个0。如果将这个数减去1后会发现，仅有的那个1会变为0，而原来的那n个0会变为1；因此将原来的数与上(&)减去1后的数字，结果为零
     */

    /**
     * 递归算法
     * @param num
     * @return
     */
    public static int is2Power(int num) {
        if (num < 2) {
            return -1;
        }
        if (num == 2) {
            return 1;
        } else if (num % 2 == 0) {
            return is2Power(num / 2);
        } else {
            return -1;
        }
    }

    /**
     * 位与判断，最快
     *
     * @param num
     * @return
     */
    static int anotherIs2Power(int num) {
        if(num < 2)
            return -1;

        if((num & num - 1) == 0 )
            return 1;
        else
            return -1;
    }

    /**
     * 移位判断
     *
     * @param num
     * @return
     */
    static int binaryIs2Power(int num) {
        if(num < 2)
            return -1;

        int temp = 1;
        while (num > temp) {
            temp <<= 1;
        }

        return temp == num ? 1 : -1;
    }

    public static void main(String[] args) {
        System.out.println(is2Power(4));
    }
}
