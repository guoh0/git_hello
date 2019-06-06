package newJdk8.lambda;

//函数式接口  只有一个抽象方法,排除object类的方法
@FunctionalInterface
interface MyInterface{
    void test();
    String toString();
}
public class FunctionaInterfaceTest {
    public void myTest(MyInterface myInterface) {
        System.out.println("--------");
        myInterface.test();
        System.out.println("--------");
    }
    public static void main(String[] args) {
        FunctionaInterfaceTest test = new FunctionaInterfaceTest();
//        test.myTest(new MyInterface() {
//            @Override
//            public void test() {
//                System.out.println("mytest");
//            }
//        });
        test.myTest(() -> {
            System.out.print("my");
            System.out.println("test");
        });

        //一下写法相当于实现了一个interface接口
        MyInterface myInterface = () ->{
            System.out.println("mytest1");
        };
        myInterface.test();
    }

}
