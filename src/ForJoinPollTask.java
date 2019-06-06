package com.lang;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

public class ForJoinPollTask {

	private static final File file = new File("D:\\eclipse-workspace\\git_hello\\resource\\tomcat.log");

	// 8M-> 5s
	// 8 * 1024M  = 5 * 1024
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		SumTask task = new SumTask(file, 0L, file.length());
//        创建一个通用池，这个是jdk1.8提供的功能
		ForkJoinPool pool = ForkJoinPool.commonPool();
		Future<Map<String, AtomicLong>> future = pool.submit(task); //提交分解的SumTask 任务
		System.out.println("多线程执行结果：" + future.get());
		pool.shutdown(); //关闭线程池
		long end = System.currentTimeMillis();
		System.out.println("耗时 = " + (end - start)/1000 + "秒");
		System.out.println(1.422222222222222);
	}
	
}

/**
 * ClassName: SumTask <br/> Function: 继承抽象类RecursiveTask，通过返回的结果，来实现数组的多线程分段累累加 RecursiveTask 具有返回值 date: 2017年12月4日
 * 下午6:08:11 <br/>
 *
 * @author prd-lxw
 * @version 1.0
 * @since JDK 1.7
 */
class SumTask extends RecursiveTask<Map<String, AtomicLong>> {
	
	private static final int THRESHOLD = 1024 * 1024; //每个小任务 每个现成最多处理1M
	private File file;
	private long start;
	private long end;
	
	
	/**
	 * Creates a new instance of SumTask. 累加从start到end的arry数组
	 */
	SumTask(File file, long start, long end) {
		super();
		this.file = file;
		this.start = start;
		this.end = end;
	}
	
	
	@Override
	protected Map<String, AtomicLong> compute() {
		//当end与start之间的差小于threshold时，开始进行实际的累加
		if (end - start < THRESHOLD) {
			Map<String, AtomicLong> resultMap = new TreeMap<>();
			try {
				RandomAccessFile randomFile = new RandomAccessFile(file, "r");
				randomFile.seek(start);
				randomFile.readLine();
				
				while (randomFile.getFilePointer() <= end) {
					String line = randomFile.readLine();
					if (null == line) {
						break;
					}
					String ip = line.substring(0, line.indexOf("-["));
					if (!resultMap.containsKey(ip)) {
						resultMap.put(ip, new AtomicLong(0));
					}
					resultMap.get(ip).getAndIncrement();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resultMap;
		} else {//当end与start之间的差大于threshold，即要累加的数超过20个时候，将大任务分解成小任务
			long middle = (start + end) / 2;
			SumTask left = new SumTask(file, start, middle);
			SumTask right = new SumTask(file, middle, end);
			//并行执行两个 小任务
			left.fork();
			right.fork();
			//把两个小任务累加的结果合并起来
			Map<String, AtomicLong> join = left.join();
			Map<String, AtomicLong> join1 = right.join();
			for (Entry<String, AtomicLong> entry : join.entrySet()) {
				if (join1.containsKey(entry.getKey())) {
					join1.get(entry.getKey()).getAndAdd(entry.getValue().get());
				} else {
					join1.put(entry.getKey(), entry.getValue());
				}
			}
			return join1;
		}
		
	}
	
}
