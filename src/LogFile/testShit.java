package LogFile;

import java.io.IOException;
import java.util.logging.*;
import java.lang.reflect.*;
import java.lang.Thread;
public class testShit {  


   public static void main(String[] args) throws IOException {
      LogFile logFile = LogFile.getLogFile();
      logFile.writeLogClosingFile();

   }
      // System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());   
       // StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    // StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
    // String methodName = e.getMethodName();
    // System.out.println(methodName);
    //String className = this.getClass().getSimpleName();
}