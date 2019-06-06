package newJdk8.lambda;

import java.util.*;
import java.util.function.Function;

public class FunctionalInterfaceTest2 {
    public static void main(String[] args) {
        MyInterface myInterface = () -> {};
        System.out.println(myInterface.getClass().getInterfaces()[0]);
        MyInterface2 myInterface2 = () -> {};
        System.out.println(myInterface2.getClass().getInterfaces()[0]);

        List<String> list = new ArrayList<>();
        List<String> list2 = Arrays.asList("hello", "word");
        list2.forEach(item -> list.add(item));
        list.forEach(item -> System.out.println(item));

        //流  传入一个源,一个动作, 继续得到流 在循环
//        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        //函数式接口的另一种实现方式: 方法引用方式  String 类的toUpperCase
        //toUpperCase() 没有参数,但是参数是调用他这个方法的对象, item就是这个对象,调用tuUpperCase
        list.stream().map(String::toUpperCase).forEach(item -> System.out.println(item));

        Function<String,String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);

        /**
         @FunctionalInterface
         public interface Function<T, R> {
         //T是传递的函数 tuUpperCase, R是返回的结果
         //Function<String,String> function = String::toUpperCase;  <String,String> 第一个是参数,第二个是结果,所以可以返回一个Function对象, ::就是Function<T, R>
            //将这个函数应用到给定的函数上,T就是函数,返回函数执行结果
            R apply(T t);
         }
`
         */

        List<String> list3 = Arrays.asList("aa", "bb", "c");
        Collections.sort(list3, Comparator.reverseOrder());
        System.out.println(list3);
    }

    @FunctionalInterface
    interface MyInterface{
        void method();
    }
    @FunctionalInterface
    interface MyInterface2{
        void method2();
    }
}
