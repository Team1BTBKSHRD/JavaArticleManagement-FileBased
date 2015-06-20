import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.*;

public class LogFile {
  public static Logger logger = null;
	private static LogFile instance = null;
  private Handler fileHandler;
	private Path fileName;
	protected LogFile() {
		// Exists only to defeat instantiation.
	}
  public void setupLogger() throws IOException {
      logger = Logger.getLogger("");
      fileHandler = new FileHandler("log1.log", true);
      logger.addHandler(fileHandler);
  }
	public static LogFile getInstance() {
		if(instance == null)	instance = new LogFile();
		return instance;
	}
	public Path getFileName() {
		return fileName;
	}
	public void setFileName(Path fileName) {
		this.fileName = fileName;
	}
	
	public void writeLogOpenningFile(){
    logger.setLevel(Level.ALL);
    logger.severe("This is a SEVERE-level log");
    logger.info("This is a info-level message");
      fileHandler.flush();
      fileHandler.close();
  }
//	public void writeLogClosingFile(){}
//	public void writeLogModifingFile(){}
//	public void writeLogDeletingFile(){}
//	public void writeLogException(){}
}
