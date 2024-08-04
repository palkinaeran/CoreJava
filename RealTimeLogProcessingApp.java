package work;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.FileHandler;

public class RealTimeLogProcessingApp {

	public static void main(String[] args) {
		
		String logFilePath = "application.log";
		LogDataSink logDataSink = new LogDataSink(logFilePath);
		
		BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
        LogProcessor processor = new LogProcessor(logQueue, logDataSink::saveLog);
        LogGenerator generator = new LogGenerator(logQueue);

        Thread generatorThread = new Thread(generator);
        Thread processorThread = new Thread(processor);

        generatorThread.start();
        processorThread.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            processorThread.interrupt();
            generatorThread.interrupt();
            logDataSink.close();
        }));

        
        
    }


	}


