package meicai;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

public class ForJoinPollTask {
    private static final File file = new File("D:\\eclipse-workspace\\git_hello\\resource\\tomcat.log");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        //处理800GB的文件 1.处理的文件 2.任务的起始点 3.任务的结束点
        SumTask task = new SumTask(file,0l,file.length());
        //返回一个通用池 jdk1.8
        ForkJoinPool pool = ForkJoinPool.commonPool();
        //提交任务
        Future<Map<String, AtomicLong>> future = pool.submit(task);

        System.out.println( "线程数：" + pool.getActiveThreadCount()); //1
        System.out.println("并行数量：" + pool.getParallelism());//4 并行数量
        System.out.println("可以使用的CPU处理器" + Runtime.getRuntime().availableProcessors());//4

        System.out.println("多线程执行结果:" + future.get());
        Map<String, AtomicLong> map = future.get();
        ValueComparator valueComparator = new ValueComparator(map);
        Map<String, AtomicLong> resultMap = new TreeMap<>(valueComparator);
        for (Map.Entry<String, AtomicLong> entry : resultMap.entrySet()) {
            //取前100
        }
        //关闭任务
        pool.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("耗时 : "+ (end-start)/1000 +"秒");
    }
}

class SumTask extends RecursiveTask<Map<String, AtomicLong>> {
    private static final int THRESHOLD = 1024 * 1024;//每个小任务最多处理1M
    private File file;
    private long start;
    private long end;
    SumTask(File file, long start, long end) {
        this.file = file;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<String, AtomicLong> compute() {
        //当任务量小于1M时,拆分不了了
        RandomAccessFile randomFile = null;
        if (end - start < THRESHOLD) {
            Map<String, AtomicLong> resultMap = new HashMap<>();
            try {
                //访问模式:r只读
                randomFile = new RandomAccessFile(file, "r");
                //设置文件记录指针起始的位置
                randomFile.seek(start);
                randomFile.readLine();//先读一行

                //filePointer读取到的位置 , while一直读到最后
                while (randomFile.getFilePointer() <= end) {
                    //按行读
                    String line = randomFile.readLine();
                    //最后一行末端
                    if (null == line) {
                        break;
                    }
                    //截取ip
                    String ip = line.substring(0, line.indexOf("-["));
                    //没有key加入map
                    if (!resultMap.containsKey(ip)) {
                        resultMap.put(ip, new AtomicLong(0));
                    }
                    //获取到一次ip 次数自增
                    resultMap.get(ip).getAndIncrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (randomFile != null) {
                    try {
                        randomFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return resultMap;
        } else { //当end-start大于单个任务量1M时,将任务继续拆分下去
            //两个任务平分剩余的量 500M+800M /2 =650  500-650 650-800
            long middle = (start + end) / 2;
            //相当于递归调用自身不断拆分任务
            SumTask left = new SumTask(file, start, middle);
            SumTask right = new SumTask(file, middle, end);
            //并行执行两个小任务
            left.fork();
            right.fork();
            //结果合并
            Map<String, AtomicLong> join = left.join();
            Map<String, AtomicLong> join1 = right.join();
            //遍历join put到join1中
        for (Map.Entry<String, AtomicLong> entry : join.entrySet()) {
                if (join1.containsKey(entry.getKey())) {
                    //join1的value Add join的value
                    join1.get(entry.getKey()).getAndAdd(entry.getValue().get());
                } else {
                    join1.put(entry.getKey(), new AtomicLong(0));
                }
            }
            return join1;
        }
    }
}
class ValueComparator implements Comparator<String> {

    Map<String, AtomicLong> map;
    public ValueComparator(Map<String, AtomicLong> map) {
        this.map = map;
    }

    public int compare(String k1, String k2) {
        if (map.get(k1).get() >= map.get(k2).get()) {
            return 1;
        } else {
            return -1;
        } // returning 0 would merge keys
    }
}