package designPattern.SingletonPattern;

/**
 * java.lang.Runtime使用的单例-饿汉模式：
 */
public class SingletonRuntime {
    //final防止串改
    private static final SingletonRuntime singletonRuntime = new SingletonRuntime();
    private SingletonRuntime(){}
    public static SingletonRuntime getInstance() {
        return singletonRuntime;
    }
}
