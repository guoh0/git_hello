package designPattern.SingletonPattern;

/**
 * 单例模式
 * 让某一个类在系统中只有一个实例,能够极大的减少系统对同一个对象的创建和销毁,节省内存开销
 */
public class SingletonSyn {
    //私有静态变量类 永远一个
    private static SingletonSyn singletonSyn = null;
    //私有构造 防止new
    private SingletonSyn(){}
    //加锁的方法
    public static synchronized SingletonSyn getInstance(){
        if (singletonSyn == null) {
            singletonSyn = new SingletonSyn();
        }
        return singletonSyn;
    }
}
//缺点并发慢,锁的是整个方法,锁粒度大