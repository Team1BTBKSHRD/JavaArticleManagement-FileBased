package LogFile;

import java.nio.file.Path;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;

public class LogFile {   
	private static LogFile logFile  = null;
	private LogFile() {
		// Exists only to defeat instantiation.
	}
	public static LogFile getLogFile(){
		if(logFile == null)	logFile = new LogFile();
		return logFile;
	}

	
	public void writeLogOpenningFile(){
	    
	}
	public void writeLogClosingFile(){}
	public void writeLogModifingFile(){}
	public void writeLogDeletingFile(){}
	public void writeLogException(){}
		
		
		
	private Path fileName;
	public Path getFileName() {
		return fileName;
	}
	public void setFileName(Path fileName) {
		this.fileName = fileName;
	}

	private String getCurrentDate(){ 
    return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
	}
  

}
