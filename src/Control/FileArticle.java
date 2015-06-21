package Control;
import Model.Article;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
//import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.io.FileWriter;

/*
 * Main File Class
 * Read and Write Data
 * Store Data as pool
 * */
public class FileArticle {
	private File file;
	private static LogFile logfile;
	private static FileArticle fileArticle;
	private static final String FILE_NAME = "ArticleData.bin";
/**
 * Apply SingleTon pattern
 * */
	public static FileArticle getInstance() {
		logfile = LogFile.getLogFile();
		if (fileArticle == null)
			fileArticle = new FileArticle();
		return fileArticle;
	}
	private FileArticle() {
		try {
			file = new File(FILE_NAME);
		} catch (Exception e) {
			logfile.writeLogException(e, "constructor", "FileArticle");
		}
	}
/**
 * @param: ArrayList<Article>
 * write Object collection to File
 * using writeObject method of ObjectOutputStream
 * */
	public void writeFile(ArrayList<Article> articles) {
		try (ObjectOutputStream objectOutput = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			objectOutput.writeObject(articles);
			logfile.writeLogWriteFile(file.toString());
		} catch (Exception e) {
			logfile.writeLogException(e, "writeFile", "FileArticle");
		}
	}// End of writeFile();
@SuppressWarnings("unchecked")
/**
 * Read Object from File
 * return object as ArrayList
 * */	
	public ArrayList<Article> readFile() {
		ArrayList<Article> list = new ArrayList<Article>();
		if(file.exists()){
		try (ObjectInputStream objectInput = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			list = (ArrayList<Article>) objectInput.readObject();
			logfile.writeLogReadFile(file.toString());
		} catch (Exception e) {
			logfile.writeLogException(e, "readFile", "FileArticle");
		}
		}else{
			System.out.println("File not found!!!");
		}
		return list;
	}// End of readFile();
/*Pisal*/
/**
 *@
 *param readFile(filename)
 *allow users can choose file for reading
 *return as object of ArrayList collection 
 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Article> readFile(File filename) {
		ArrayList<Article> list = new ArrayList<Article>();
		if(file.exists()){
		try (ObjectInputStream objectInput = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(filename)))) {
			list = (ArrayList<Article>) objectInput.readObject();
			logfile.writeLogReadFile(file.toString());
		} catch (Exception e) {
			logfile.writeLogException(e, "readFile(File filename)", "FileArticle");
		}
		}else{
			System.out.println("File not found!!!");
		}
		return list;
	}// End of readFile();
/**
 *@param : File source, File desctination
 *CopyFile function : use for copyFile or backUp File
 * */
	public void copyFile(File source, File destination) throws IOException {
		FileChannel inChannel = null;
		FileChannel outputChannel = null;
		try {
			inChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(destination).getChannel();
			outputChannel.transferFrom(inChannel, 0, inChannel.size());
			System.out.println("Backup Completed");
			logfile.writeLogCopyFile(source.toString(), destination.toString());
		} catch (Exception e) {
			logfile.writeLogException(e, "copyFile", "FileArticle");
		} finally {
			inChannel.close();
			outputChannel.close();
		}
	}// End of copyFile();
	public File sourceFile(){
		return file;
	}
//	/**
//	 * @param: filelog, String[] data
//	 * writeLog function : write information when objects edited, remove 
//	 * */
//		public void writeLog(File fileLog, String[] data) {
//			try (FileWriter fileWriter = new FileWriter(fileLog, true)) {
//				String record = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date())+" ";
//				for (int i = 0; i < data.length; i++)
//					record += data[i]+" ";
//	
//				fileWriter.write(record);
//				fileWriter.write(System.getProperty("line.separator"));
//	
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//System.out.println("Write LogFile Completed");
//		}// End of writeLog();
//		
//			
}// End of class;
