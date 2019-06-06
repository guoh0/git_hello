package meicai;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LRU {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(4);//先进先出
//        poll()//队列不为空时返回队首值并移除；队列为空时返回null。非阻塞立即返回。
//        offer(e)//队列未满时，返回true；队列满时返回false。非阻塞立即返回。
        Integer[] is = new Integer[]{4,3,2,1,4,3,5,4,3,2,1,5,4};
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < is.length; i++) {
            System.out.println("准备加入缓存: "+is[i]);
            if (!queue.contains(is[i])) {
                count.incrementAndGet(); //命中加1
            }
            boolean success = queue.offer(is[i]);
            if (success) {
                System.out.println("缓存加入成功"+ is[i]);
            } else {
                System.out.println("缓存已满");
                Integer poll = queue.poll();
                System.out.println("淘汰缓存中的 "+poll);
                boolean success2 = queue.offer(is[i]);
                if (success2) {
                    System.out.println("缓存加入成功"+ is[i]);
                } else {
                    System.out.println("缓存加入失败"+ is[i]);
                }
            }
            System.out.println("当前缓存 : "+ queue);
            System.out.println("---------------------------------");
            Thread.sleep(1000);
        }
        System.out.println("最后的缓存 : "+ queue);
        System.out.println("未命中次数 : "+ count);
    }
}
