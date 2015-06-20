package LogFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import java.lang.reflect.*;
import java.lang.Thread;

import Model.Article;
public class test {  
   public static void main(String[] args) throws IOException {
	   //this example of how to use LogFile class
      LogFile logFile = LogFile.getLogFile();
		try{
		      String now = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
		      Article a = new Article("Srey LeangHeng", "Web Application Development", "CBD" , now);
		      logFile.writeLogOpenFile("asfd.txt");
		      logFile.writeLogCloseFile("asfd.txt");
		      logFile.writeLogAdd(a);
		      logFile.writeLogEdit(a);
		      logFile.writeLogDelete(a);
		      logFile.writeLogEdit(a);
		      throw new IOException();
		}catch(Exception ex){
			logFile.writeLogException(ex);
		} 
   }
      // System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());   
       // StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    // StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
    // String methodName = e.getMethodName();
    // System.out.println(methodName);
    //String className = this.getClass().getSimpleName();
}