package designPattern.SingletonPattern;

/**
 * 静态内部类模式的单例
 */
public class SingletonHolder{
    //私有构造
    private SingletonHolder(){}
    //直接从静态方法获取实例,去掉了成员变量
    public static SingletonHolder getInstance() {
        return Holder.singletonHolder;
    }
    //私有的静态内部类
    private static class Holder{
        //final防止更改
        private static final SingletonHolder singletonHolder = new SingletonHolder();
    }
}
/**
 该单例只有在Holder.singleton,才会对单例对象进行初始化，
 而反射是不能通过外部类获取到内部类的，避免了反射入侵。由于是静态内部类，只有在第一次引用才会被加载，多线程安全。
 缺点：虽然不会创建静态内部类对象，但class对象还是会被创建并放入永久代。
 而且这个单例对象一旦创建，后期如果销毁便不能再次创建
 */