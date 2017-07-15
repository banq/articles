package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 * 
 */
public class ForkJoinDemo {
	private static final int ARRAY_SIZE = 800; // 8000000;
	private static final int ACCEPTABLE_SIZE = 100; // 100000;
	private static List<String> letters = new ArrayList<String>(ARRAY_SIZE);

	private final static int threadCount = 4;
	private final static ExecutorService executorService = Executors
			.newFixedThreadPool(threadCount);
	private final static ForkJoinPool pool = new ForkJoinPool();
	
    static {
		final char key = 'A';
		// fill the big array with A-Z randomly
		for (int i = 0; i < ARRAY_SIZE; i++) {
			char t = (char) (Math.random() * 26 + 65);
			letters.add(String.valueOf(t)); // A-Z
			Random r = new Random();
			// System.out.println(Math.random()*letters.size()+"="+String.valueOf(t));
		}
		System.out.println("初始化p" + letters.size());

	}



	public static void main(String[] args) {
		final char key = 'A';
		ConcurrentHashMap<Character, AtomicInteger> map = new ConcurrentHashMap<Character, AtomicInteger>();

		//System.out.printf(" begin to Using ForkJoin \n");
		long begin = System.currentTimeMillis();
		SharedParameter sharedParameter= new SharedParameter(map);
		BusinessCompTaskSharding task = new BusinessCompTaskSharding(key,
				letters, ACCEPTABLE_SIZE, sharedParameter);
		BatchComputeResult batchComputeResult = pool.invoke(task);
		long end = System.currentTimeMillis();
		System.out.printf((end - begin) + " Using ForkJoin, found %d %d \n",
				batchComputeResult.getCount(), batchComputeResult.getLetters()
						.size());
		System.out.printf(" map size=" + map.get(key) + " helper="+ sharedParameter.getMap2().size() +" \n");

		
		pool.shutdown();
		executorService.shutdown();
	}

	
}

