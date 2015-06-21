package Control;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Article;
public class LogFile {   
	private static LogFile logFile  = null;
	private LogFile() {
		// exists to deny instantiation.
	}
	public static LogFile getLogFile() {			
		if(logFile == null)	logFile = new LogFile();		
		return logFile;		
	}	
	public void writeLogCopyFile(String sourceFile, String destinationFile){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true)))
		{	output.write("Copy File From " + sourceFile + " to " + destinationFile + " at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));			 
		} catch (Exception e) {
			writeLogException(e, "writeLogCopyFile","LogFile");
		}
	}
	public void writeLogReadFile(String fileName){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true)))
		{	output.write("Read File " + fileName + " at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));			 
		} catch (Exception e) {
			writeLogException(e, "writeLogReadFile","LogFile");
		}
	}
	public void writeLogWriteFile(String fileName){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true)))
		{	output.write("Write File " + fileName + " at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));			 
		} catch (Exception e) {
			writeLogException(e, "writeLogWriteFile","LogFile");
		}
	}
	public void writeLogAdd(Article a){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true)))
		{	output.write("Add Item at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));
			output.write("\t" + a.toString());
			output.write(System.getProperty("line.separator"));			 
		} catch (Exception e) {
			writeLogException(e, "writeLogAdd","LogFile");
		}
	}
	public void writeLogEdit(Article a){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true)))
		{	output.write("Edit Item at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));
			output.write("\t" + a.toString());
			output.write(System.getProperty("line.separator"));			 
		} catch (Exception e) {
			writeLogException(e, "writeLogEdit","LogFile");
		}
	}
	public void writeLogDelete(Article a){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true)))
		{	output.write("Delete Item at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));
			output.write("\t" + a.toString());
			output.write(System.getProperty("line.separator"));			 	
		} catch (Exception e) {
			writeLogException(e, "writeLogDelete","LogFile");
		}
	}
	public void writeLogException(Exception ex, String methodName, String className){
		try(BufferedWriter output = new BufferedWriter(new FileWriter("LogFile.log", true))) 
		{	output.write(ex + " in METHOD " + methodName + " CLASS " + className + " at\t" + getCurrentDate());
			output.write(System.getProperty("line.separator"));			
		} catch (Exception e) {
			writeLogException(e, "writeLogException","LogFile");
		}
	}		
	private String getCurrentDate(){ 
		return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
	} 
}
