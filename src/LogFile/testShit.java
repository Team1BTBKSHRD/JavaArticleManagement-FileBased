import java.io.IOException;
import java.util.logging.*;
 
public class testShit {
   private static final Logger logger = Logger.getLogger(testShit.class.getName());
 
   public static void main(String[] args) throws IOException {
      // Construct a default FileHandler.
      // "%t" denotes the system temp directory, kept in environment variable "tmp"
      Handler fh = new FileHandler("test.log", true);  // append is true
      
//    fh.setFormatter(new SimpleFormatter());  // Set the log format
      // Add the FileHandler to the logger.
      logger.addHandler(fh);
      // Set the logger level to produce logs at this level and above.
      logger.setLevel(Level.FINE);
 
      try {
         // Simulating Exceptions
         throw new Exception("Simulating an exception");
      } catch (Exception ex){
         logger.log(Level.SEVERE, ex.getMessage(), ex);
      }
      logger.info("This is a info-level message");
      logger.config("This is a config-level message");
      logger.fine("This is a fine-level message");
      logger.finer("This is a finer-level message");
      logger.finest("This is a finest-level message");  // below the logger's level
 
      fh.flush();
      fh.close();
   }
}