package designPattern.SingletonPattern;

/**
 * 双检锁单例模式,方法内代码块加锁,减税锁的粒度
 */
public class SingletonTwoSyn {
    private static SingletonTwoSyn singletonTwoSyn = null;
    private SingletonTwoSyn(){}

    public static SingletonTwoSyn getInstance() {
        //先判断不存在 在上锁 减耗内存
        if (singletonTwoSyn == null) {
            synchronized (SingletonTwoSyn.class) {
                if (singletonTwoSyn == null) {
                    singletonTwoSyn = new SingletonTwoSyn();
                }
            }
        }
        return singletonTwoSyn;
    }
}

/**
 缺点：多线程中可能会出现指令重排的现象：
    编译器和处理器为了优化程序性能会对指令的序列进行重新排序,
    通常带有数据依赖性的关系不会进行重排，没有依赖关系则可能会重排
 比如：double pi  = 3.14;    //A
 double r   = 1.0;     //B
 double area = pi * r * r; //C
 A和C之间存在数据依赖关系，同时B和C之间也存在数据依赖关系。
 因此在最终执行的指令序列中，C不能被重排序到A和B的前面（C排到A和B的前面，程序的结果将会被改变）。
 但A和B之间没有数据依赖关系，编译器和处理器可以重排序A和B之间的执行顺序
 单线程不会重排是因为JMM遵守了as-if-serial语义：
    不管怎么重排序（编译器和处理器为了提高并行度），（单线程）程序的执行结果不能被改变。
 上面的单例例子中，singleton = new Singleton() 执行时，java内存会有以下操作
 memory = allocate();　　// 1：分配对象的内存空间
 ctorInstance(memory);　// 2：初始化对象
 instance = memory;　　// 3：设置置实例对象指向刚分配的内存地址
 在多线程中就有可能会出现指令重排：
 memory = allocate();　　// 1：分配对象的内存空间
 instance = memory;　　// 3：设置实例对象指向刚分配的内存地址
 // 注意，此时对象还没有被初始化！
 ctorInstance(memory);　// 2：初始化对象

 A线程执行到3时singleton刚分配完内存地址还没有被初始化，
 B线程来了遇到if(singleton == null)会直接跳过拿到没有一个没有初始化的对象，得到的对象完全没有意义
 */