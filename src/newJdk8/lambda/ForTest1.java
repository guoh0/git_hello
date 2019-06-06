package newJdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForTest1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        list.forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });
        //Consumer是函数式接口,所以可以用lambda
        //声明类型需要加括号
//        list.forEach((Integer i) -> System.out.println(i));
//        list.forEach(i -> System.out.println(i));
        //method 方法引用方式
//        list.forEach(System.out::println);
    }
}
