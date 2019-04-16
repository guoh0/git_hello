package designPattern.SingletonPattern;

/**
 * 双检锁+volatile
 * 利用happens-before原则防止指令重排
 */
public class SingletonVolatile {
    //volatile修饰,防止指令重排
    private static volatile SingletonVolatile singletonTwoSyn = null;
    private SingletonVolatile(){}

    public static SingletonVolatile getInstance() {
        if (singletonTwoSyn == null) {
            synchronized (SingletonVolatile.class) {
                if (singletonTwoSyn == null) {
                    singletonTwoSyn = new SingletonVolatile();
                }
            }
        }
        return singletonTwoSyn;
    }
}

/**
 本次优化只是在变量类上加了volatile，原因在于volatile不会出现指令重排
 JMM(JAVA内存模型)中有一种happens-before (读:嗨喷似)的规则：通俗的说如果动作B要看到动作A的执行结果，无论AB是否在同一个线程，AB必须满足happens-before原则
 介绍原则前先了解一个概念，JMMA：java模型动作，这个动作包括：编写读，变量写，监视器加锁，释放锁，线程启动，线程等待。happens-before大部分也是对这些动作加了规则的
 完整的规则如下：
 （1）同一个线程中的每个Action动作都会比后面任何一个Action先执行。
 （2）对一个监视器的解锁都会比后续每一个对同一个监视器的加锁动作先执行
 （3）对volatile字段的写操作都会比后面相同字段的读操作先执行。
 （4）Thread.start()的调用会比启动线程里面的每个动作先执行。
 （5）Thread中的所有动作都比 其他线程对此线程的检查动作 先执行，检查动作即检查线程是否终止，通过Thread.join()或Thread.isAlive()==false检测
 （6）一个线程A调用另一个另一个线程B的interrupt()都会比 线程A发现B被A中断先执行 （B抛出异常或者A检测到B的isInterrupted()或者interrupted()）。
 （7）一个对象构造函数的结束先执行与该对象的finalize()的开始
 （8）如果A动作先比B动作执行，而B动作先C动作执行，那么A动作肯定先比C动作执行。
 在第3条中规定了对volatile字段的写操作都会比后面相同字段的读操作先执行，相当于给volatile字段的写操作上下都加了屏障，防止后面的读操作因为处理器和编译器的优化措施先执行

 缺点：该单例虽然加了volatile防止了指令重排，但是当这个单例对象有多个成员变量时，为了保证这个对象被完整的初始化，就需要给每个成员变量加volatile,那么在实际业务中你会对每个成员变量加volatile修饰？

 */