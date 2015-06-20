package LogFile;

import java.nio.file.Path;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.logging.*;

public class LogFile {
  
   private static Logger logger = Logger.getLogger(LogFile.class.getName());
 
   // Create a FileHandler and attach it to the root logger.
   // Create a MyHtmlFormatter and attach to the FileHandler.
   public static void  setupLogger() throws IOException {      
      Handler fileHandler = new FileHandler("log.txt");
      Formatter formatter = new LogArticleFormatter();
      logger.addHandler(fileHandler);
      fileHandler.setFormatter(formatter);
   }
 
   public void writeLog() {      
      logger.severe("This is a SEVERE-level logasdf" );
   }
   
  private static LogFile instance;
	private Path fileName;
	protected LogFile() {
		// Exists only to defeat instantiation.
	}
	public static LogFile getInstance()  throws IOException {
    setupLogger();
		if(instance == null)	instance = new LogFile();
		return instance;
	}
	public Path getFileName() {
		return fileName;
	}
	public void setFileName(Path fileName) {
		this.fileName = fileName;
	}
	private String getCurrentDate(){ 
    return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
  }
  
	public void writeLogOpenningFile(){
    
  }
	public void writeLogClosingFile(){}
	public void writeLogModifingFile(){}
	public void writeLogDeletingFile(){}
	public void writeLogException(){}
}
