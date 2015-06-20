import java.io.IOException;
import java.util.logging.*;

public class testShit {
    static LogFile lg;
   public testShit(){
    lg = LogFile.getInstance();     
   }
 
   public static void main(String[] args) throws IOException {
    System.out.println("asdf");
    lg.setupLogger();
   }
}