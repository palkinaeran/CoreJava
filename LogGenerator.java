package work;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class LogGenerator implements Runnable{
	
	private final BlockingQueue<String> queue;
	private final Random random = new Random();
	
	public LogGenerator(BlockingQueue<String> queue){
		this.queue=queue;
	}
	
	@Override
	public void run() {
		try {
		String log = generateLog();
		queue.put(log);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
	}
	
	private String generateLog() {
		
		String[] levels = {"INFO","DEBUG","ERROR"};
		
		String level = levels[random.nextInt(levels.length)];
		
		String message = "Sample message:"+random.nextInt(1000);
		return level+": "+ message;
	}

}
