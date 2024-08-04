package work;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LogProcessor implements Runnable {
	
	private final BlockingQueue<String> queue;
	private final Consumer<String> logDataSink;
	
	
	public LogProcessor(BlockingQueue<String> queue, Consumer<String> logDataSink) {
		super();
		this.queue = queue;
		this.logDataSink = logDataSink;
	}


	@Override
	public void run() {
		while(true) {
			String log;
			try {
				log = queue.take();
				processLog(log);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				break;
			}
			
			 
		}
		
		
	}
	
	private void processLog(String log) {
		CompletableFuture.runAsync(()->{
			if(log.contains("ERROR")||log.contains("INFO")||log.contains("DEBUG")) {
				logDataSink.accept(log);
			}
		});
	}

}
