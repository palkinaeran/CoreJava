package work;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LogDataSink {
	
		private final String filePath;
		private PrintWriter writer;
		
		public LogDataSink(String filePath) {
			this.filePath=filePath;
			try {
				this.writer=new PrintWriter(new BufferedWriter(new FileWriter(filePath,true)));
			}catch(IOException e) {
				throw new RuntimeException("Error initializing LogDataSink",e);
			}
		}
		public synchronized void saveLog(String log) {
			try {
				writer.println(log);
				writer.flush();
			}catch(Exception e) {
				System.err.println("Error writing log to file: "+e.getMessage());
			}
		System.out.println("Processed Log: "+log);
		
	}
	public void close() {
		if(writer!=null) {
			writer.close();
		}
	}

}

