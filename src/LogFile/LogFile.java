package LogFile;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Article;
public class LogFile {   
	private static LogFile logFile  = null;
	private LogFile() {
		// Exists only to defeat instantiation.
	}
	public static LogFile getLogFile() throws IOException{	
		if(logFile == null)	logFile = new LogFile();		
		return logFile;
	}	
	public void writeLogOpenFile(String fileName) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true));
		output.write("Open File " + fileName + " at\t" + getCurrentDate());
		output.write(System.getProperty("line.separator"));
		output.close();
	}
	public void writeLogCloseFile(String fileName) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true));
		output.write("Close File " + fileName + " at\t" + getCurrentDate());
		output.write(System.getProperty("line.separator"));
		output.close();
	}
	public void writeLogAdd(Article a) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true));
		output.write("Add Item at\t" + getCurrentDate());
		output.write(System.getProperty("line.separator"));
		output.write("\t" + a.toString());
		output.write(System.getProperty("line.separator"));
		output.close();
	}
	public void writeLogEdit(Article a) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true));
		output.write("Edit Item at\t" + getCurrentDate());
		output.write(System.getProperty("line.separator"));
		output.write("\t" + a.toString());
		output.write(System.getProperty("line.separator"));
		output.close();
	}
	public void writeLogDelete(Article a) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true));
		output.write("Delete Item at\t" + getCurrentDate());
		output.write(System.getProperty("line.separator"));
		output.write("\t" + a.toString());
		output.write(System.getProperty("line.separator"));
		output.close();
	}
	public void writeLogException(Exception ex) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true));
		output.write(ex + " at\t" + getCurrentDate());
		output.write(System.getProperty("line.separator"));
		output.close();
	}		
	private String getCurrentDate(){ 
		return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
	} 
}
