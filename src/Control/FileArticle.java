package Control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Article;

/*
 * Main File Class
 * Read and Write Data
 * Store Data as pool
 * */
public class FileArticle {
	private File file;
	private static FileArticle fileArticle;
	private static final String FILE_NAME = "ArticleManagement.bin";

	public static FileArticle getInstance() {
		if (fileArticle == null)
			fileArticle = new FileArticle();
		return fileArticle;
	}

	private FileArticle() {
		try {
			file = new File(FILE_NAME);
		} catch (Exception e) {
		}
	}

	public void writeFile(ArrayList<Article> articles) {
		try (ObjectOutputStream objectOutput = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			objectOutput.writeObject(articles);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Write Completed!!!");
	}// End of writeFile();

	@SuppressWarnings("unchecked")
	public ArrayList<Article> readFile() {
		ArrayList<Article> list = new ArrayList<Article>();
		try (ObjectInputStream objectInput = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			list = (ArrayList<Article>) objectInput.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}// End of readFile();

	/*
	 * public String getDateTime() { return new
	 * SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date()); }
	 */

	public void writeLog(File fileLog, String[] data) {
		try (FileWriter fileWriter = new FileWriter(fileLog, true)) {
			String record = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date())+" ";
			for (int i = 0; i < data.length; i++)
				record += data[i]+" ";

			fileWriter.write(record);
			fileWriter.write(System.getProperty("line.separator"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Write LogFile Completed");
	}// End of writeLog();

	public void copyFile(File source, File destination) throws IOException {
		FileChannel inChannel = null;
		FileChannel outputChannel = null;
		try {
			inChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(destination).getChannel();
			outputChannel.transferFrom(inChannel, 0, inChannel.size());
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inChannel.close();
			outputChannel.close();
		}
	}// End of copyFile();
	public static void main(String[] args) {
		//System.out.println(FileArticle.getInstance().readFile().size());
		ArrayList<Article> at=FileArticle.getInstance().readFile();
		for(Article a : at)
			System.out.println(a);
		
	}
}// End of class;
