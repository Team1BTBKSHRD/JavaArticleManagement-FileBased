import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LogArticleFormatter extends Formatter {
   public String format(LogRecord record) {
      StringBuilder buf = new StringBuilder();
      buf.append(record.getSourceMethodName());
      buf.append("\t"); 
      buf.append(formatDateTime(record.getMillis()));
      buf.append("\t"); 
      buf.append(record.getSourceClassName());
      buf.append("\n"); 
      return buf.toString();
   }
 
   private String formatDateTime(long millisecs) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MMM dd, yyyy");
      Date recordDate = new Date(millisecs);
      return dateFormat.format(recordDate);
   }
}