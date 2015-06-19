package LogFile;
import java.nio.file.Path;

public class LogFile {
	private static LogFile instance = null;
	private Path fileName;
	protected LogFile() {
		// Exists only to defeat instantiation.
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
	
	public void writeLogOpenningFile(){}
	public void writeLogClosingFile(){}
	public void writeLogModifingFile(){}
	public void writeLogDeletingFile(){}
	public void writeLogException(){}
}
