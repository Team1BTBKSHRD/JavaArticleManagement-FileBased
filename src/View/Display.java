package View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Model.Article;

public class Display {
	public static final String ORGANIZATION_NAME = "KOREAN SOFTWARE HRD CENTER";
	public static final String PROJECT_NAME = "ARTICLES MANAGEMENT SYSTEM";
	public static final String MENU1 = "F) Fist | P) Pervious | N) Next | L) Last";
	public static final String MENU2 = "A) Add | R) Remove | S)Search | U) Update | D) Display | G) Goto #) Set Row | E) Exit";
	private char topLeft;
	private char topRight;

	private char buttomLeft;
	private char buttomRight;

	private char middleTop;
	private char middleButtom;
	private char middleCenter;
	private char middleLeft;
	private char middleRight;

	private char verticalLine;
	private char horizontalLine;

	private String[] headers = { "ID", "AUTHOR ", "TITLE ", "PUBLISH DATE",
			"MODIFIED DATE" };

	ArrayList<Article> articles;
	List<Article> subPages;
	
	private int currentPage;
	private int pageSize;
	private int totalPage;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		repaginate();
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public void gotoPage(int pageNumber) {
	    currentPage = pageNumber - 1;
	    if(currentPage < 0){
	    	gotoFristPage();
	    }
	    else if(currentPage > totalPage){
	    	gotoLastPage();
	    }
	    repaginate();
	}
	
	public Display() {
		currentPage = 0;
		pageSize = 5;
		articles =  new ArrayList<Article>();
		topLeft = topRight = buttomLeft = buttomRight = middleTop = middleButtom = middleLeft = middleCenter = middleRight = '+';
		horizontalLine = '-';
		verticalLine = '|';
		repaginate();
	}

	public Display(char topLeft, char topRight, char buttomLeft,
			char buttomRight, char middleTop, char middleButton,
			char middleLeft, char middleCenter, char middleRight,
			char verticalLine, char horizontalLine) {

		currentPage = 0;
		pageSize = 5;
		articles =  new ArrayList<Article>();
		this.topLeft = topLeft;
		this.topRight = topRight;

		this.buttomLeft = buttomLeft;
		this.buttomRight = buttomRight;

		this.middleTop = middleTop;
		this.middleButtom = middleButton;
		this.middleLeft = middleLeft;
		this.middleCenter = middleCenter;
		this.middleRight = middleRight;

		this.verticalLine = verticalLine;
		this.horizontalLine = horizontalLine;
		repaginate();
	}

	
	public void gotoNextPage(){
		if(currentPage < totalPage){
			currentPage++;
		} else{
			//currentPage = 0;
			gotoFristPage();
		}
		repaginate();
	}
	public void gotoPreviousPage(){
		if(currentPage >= 0){
			currentPage--;
		}
		else{
			//currentPage = totalPage;
			gotoLastPage();
		}
		repaginate();
	}
	
	public void gotoFristPage(){
		currentPage = 0;
	}
	public void gotoLastPage(){
		currentPage = totalPage - 1;
	}
	
	private void repaginate() {
		if (!articles.isEmpty()) {
			totalPage = (int) Math.ceil(this.articles.size() / (float)pageSize);
			int start = currentPage * pageSize;
			int end = start + pageSize - 1;
			if (end >= articles.size()) {
				end = articles.size() - 1;
			}
			if (start >= articles.size()) {
				//currentPage = 0;
				gotoFristPage();
				repaginate();
			} else if (start < 0) {
				//currentPage = articles.size() / pageSize;
				gotoLastPage();
				if (articles.size() % pageSize == 0) {
					currentPage--;
				}
				repaginate();
			} else {
				subPages = articles.subList(start, end + 1);
			}
		}
	}

	public void process() {
		repaginate();
		drawTable(subPages);
	}

	public void setTableStyle(char topLeft, char topRight, char buttomLeft,
			char buttomRight, char middleTop, char middleButton,
			char middleLeft, char middleCenter, char middleRight,
			char verticalLine, char horizontalLine) {

		this.topLeft = topLeft;
		this.topRight = topRight;

		this.buttomLeft = buttomLeft;
		this.buttomRight = buttomRight;

		this.middleTop = middleTop;
		this.middleButtom = middleButton;
		this.middleLeft = middleLeft;
		this.middleCenter = middleCenter;
		this.middleRight = middleRight;

		this.verticalLine = verticalLine;
		this.horizontalLine = horizontalLine;
	}

	public void header(int []maxColumns, int totalLenght) {
		String head = "";
		head = addHorizontalLine(head, maxColumns, topLeft, horizontalLine, topRight);
		head = addLetter(head, verticalLine, 1);
		head = addLetter(head, '_', (totalLenght - ORGANIZATION_NAME.length()) / 2);
		head += ORGANIZATION_NAME;
		head = addLetter(head, '_', totalLenght - ((totalLenght - ORGANIZATION_NAME.length())));
		head = addLetter(head, verticalLine, 1) + "\n";
		
		head = addLetter(head, verticalLine, 1);
		head = addLetter(head, '*', (totalLenght - PROJECT_NAME.length()) / 2);
		head += PROJECT_NAME;
		head = addLetter(head, '*', totalLenght - ((totalLenght - PROJECT_NAME.length())));
		head = addLetter(head, verticalLine, 1) + "\n";
		
		head += addHorizontalLine(head, maxColumns, buttomLeft, horizontalLine, buttomRight);
		System.out.println(head);
	}

	public void menu(int[] maxColumns, int totalLenght) {
		String strMenu = "";
		strMenu = addHorizontalLine(strMenu, maxColumns, topLeft, horizontalLine, topRight);
		strMenu = addLetter(strMenu, verticalLine, 1);
		strMenu = addLetter(strMenu, '_', (totalLenght - MENU1.length()) / 2);
		strMenu += MENU1;
		strMenu = addLetter(strMenu, '_', totalLenght - ((totalLenght - MENU1.length())));
		strMenu = addLetter(strMenu, verticalLine, 1) + "\n";
		
		strMenu = addLetter(strMenu, verticalLine, 1);
		strMenu = addLetter(strMenu, '*', (totalLenght - MENU2.length()) / 2);
		strMenu += MENU2;
		strMenu = addLetter(strMenu, '*', totalLenght - ((totalLenght - MENU2.length())));
		strMenu = addLetter(strMenu, verticalLine, 1) + "\n";
		
		strMenu += addHorizontalLine(strMenu, maxColumns, buttomLeft, horizontalLine, buttomRight);
		System.out.println(strMenu);
	}

	/*
	 * Method maxColumnsLength() Use for fine maximum length of every columns
	 */
	private int[] maxColumnsLength(List<Article> articles) {
		int[] maxLengths = new int[] { headers[0].length(),
				headers[1].length(), headers[2].length(), headers[3].length(),
				headers[4].length() };

		for (Article article : articles) {
			if (Integer.toString(article.getId()).length() > maxLengths[0]) /*
																			 * Find
																			 * maximum
																			 * length
																			 * of
																			 * column
																			 * ID
																			 */
				maxLengths[0] = Integer.toString(article.getId()).length();

			if (article.getAuthor().length() > maxLengths[1]) /*
															 * Find maximum
															 * length of column
															 * Author
															 */
				maxLengths[1] = article.getAuthor().length();

			if (article.getTitle().length() > maxLengths[2]) /*
															 * Find maximum
															 * length of column
															 * Title
															 */
				maxLengths[2] = article.getTitle().length();

			if (article.getPublishDate().length() > maxLengths[3]) /*
																	 * Find
																	 * maximum
																	 * length of
																	 * column
																	 * Publish
																	 * Date
																	 */
				maxLengths[3] = article.getPublishDate().length();

			if (article.getModifiedDate().length() > maxLengths[4]) /*
																	 * Find
																	 * maximum
																	 * length of
																	 * column
																	 * Modified
																	 * Date
																	 */
				maxLengths[4] = article.getModifiedDate().length();
		}
		return maxLengths; /* return maximum length of every columns */
	}

	/*
	 * Method addSpace() Use for add spaces into string string parameter is
	 * original string spaceNumber parameter is the number of spaces are going
	 * add to string
	 */
	private String addLetter(String string, char letter, int letterNumber) {
		for (int i = 0; i < letterNumber; i++)
			string += Character.toString(letter);
		return string;
	}

	/*
	 * Method replaceLastIndex() Use for replace the last character of string
	 * string parameter is original string key is a character for replace
	 */
	private String replaceLastIndex(String string, char key) {
		string = string.substring(0, string.length() - 1); /*
															 * Cut the last
															 * character of
															 * string
															 */
		string += Character.toString(key); /* Add key into string */
		return string;
	}

	/*
	 * Method addHorizontalLine() Use for add horizontal line into string string
	 * parameter is the original string maxColumns parameter is maximum length
	 * of every columns start parameter is a character of the beginning of the
	 * line middle parameter is a character of the middle in the line end
	 * parameter is a character of the end of the line
	 */
	private String addHorizontalLine(String string, int[] maxColumns,
			char start, char middle, char end) {
		string = Character.toString(start); /* Beginning Character of the line */
		for (int columnLength : maxColumns) {
			for (byte line = 0; line < columnLength; line++) {
				string += Character.toString(horizontalLine); /*
															 * Length of cell =
															 * maximum of column
															 * length
															 */
			}
			string += Character.toString(middle); /*
												 * Add the middle character of
												 * horizontal line
												 */
		}
		string = replaceLastIndex(string, end); /*
												 * Add the last character of
												 * horizontal line
												 */
		string += "\n"; /* Add a new line */
		return string; /* Value of string is horizontal line */
	}
	private int sumColumnsLenght(int[] maxColumns){
		int sum = 0;
		for(int columnLength : maxColumns){
			sum += columnLength + 1;
		}
		return sum;
	}
	public void drawTable(List<Article> articles) {

		int[] maxColumns = maxColumnsLength(articles);
		int totalLenght = sumColumnsLenght(maxColumns);
		String table = "";
		/* Header Block */
		header(maxColumns, totalLenght);
		// First line of table;
		table += addHorizontalLine(table, maxColumns, topLeft, middleTop,
				topRight);
		// Add Header Title;
		int index = 0;
		for (String header : headers) {
			table += Character.toString(verticalLine); // "¦";

			// Add spaces;
			table = addLetter(table, ' ',
					(maxColumns[index] - header.length()) / 2);
			// Add header name;
			table += header;
			// Add spaces;
			int totalLength = maxColumns[index]
					- (maxColumns[index] - header.length()) / 2
					- header.length();
			table = addLetter(table, ' ', totalLength);
			// (maxColumns[index] - header.length()) / 2);

			index++;
		}
		table += Character.toString(verticalLine) + "\n"; /* Add vertical line */

		/* End of Header Block */

		// Body
		for (Article article : articles) {
			// Horizontal Line of every records;
			table += addHorizontalLine(table, maxColumns, middleLeft,
					middleCenter, middleRight);

			table += Character.toString(verticalLine) + article.getId();
			table = addLetter(table, ' ',
					maxColumns[0] - Integer.toString(article.getId()).length());

			table += Character.toString(verticalLine) + article.getAuthor();
			table = addLetter(table, ' ', maxColumns[1]
					- article.getAuthor().length());

			table += Character.toString(verticalLine) + article.getTitle();
			table = addLetter(table, ' ', maxColumns[2]
					- article.getTitle().length());

			table += Character.toString(verticalLine)
					+ article.getPublishDate();
			table = addLetter(table, ' ', maxColumns[3]
					- article.getPublishDate().length());

			table += Character.toString(verticalLine)
					+ article.getModifiedDate();
			table = addLetter(table, ' ', maxColumns[4]
					- article.getModifiedDate().length());

			table += Character.toString(verticalLine);
			table += "\n";
		}
		// End of Body

		// Footer;
		table += addHorizontalLine(table, maxColumns, buttomLeft, middleButtom,
				buttomRight);
		// End of Footer;
		System.out.println(table);
		menu(maxColumns, totalLenght);
		System.err.println("Total Records:" + this.articles.size());
		System.err.println("Pages: " + (this.currentPage + 1) + "/" + totalPage);
		System.out.println();
	}

	public static void main(String[] args) {
		Display dis = new Display();
		//dis.setTableStyle('+', '+', '+', '+', '-', '-', '¦', '+', '¦', '¦', '-');
		dis.setTableStyle('\u2554', '\u2557', '\u255A', '\u255D', '\u2566', '\u2569', '\u2560', '\u256C', '\u2563', '\u2551', '\u2550');
		ArrayList<Article> articles = new ArrayList<Article>();

		articles.add(new Article("Siang Sambo", "HRD Center",
				"The best IT Center In Cambodia"));
		articles.add(new Article("Vichea Lay", "RUPP", "CBD"));
		articles.add(new Article("Dara", "Edfdfd", "CBD"));
		articles.add(new Article("Sal", "Fdfdfd", "CBD"));
		articles.add(new Article("Sok", "AGdfdfd", "CBD"));
		articles.add(new Article("Kit", "Edfdf", "CBD"));
		articles.add(new Article("Poly", "Gdfdf", "CBD"));
		articles.add(new Article("Nita", "Udfdfd", "CBD"));
		articles.add(new Article("Chetra", "Idfdfd", "CBD"));
		articles.add(new Article("Sambo", "Jdfdfd", "CBD"));
		articles.add(new Article("aaElit", "Pdfdfd", "CBD"));
		articles.add(new Article("zhanna", "Adfdfd", "CBD"));
		articles.add(new Article("Ally", "Xdfdfd", "CBD"));

		articles.add(new Article("Siang Sambo", "HRD Center",
				"The best IT Center In Cambodia"));
		articles.add(new Article("Vichea Lay", "RUPP", "CBD"));
		articles.add(new Article("Dara", "Edfdfd", "CBD"));
		articles.add(new Article("Sal", "Fdfdfd", "CBD"));
		articles.add(new Article("Sok", "AGdfdfd", "CBD"));
		articles.add(new Article("Kit", "Edfdf", "CBD"));
		articles.add(new Article("Poly", "Gdfdf", "CBD"));
		articles.add(new Article("Nita", "Udfdfd", "CBD"));
		articles.add(new Article("Chetra", "Idfdfd", "CBD"));
		articles.add(new Article("Sambo", "Jdfdfd", "CBD"));
		articles.add(new Article("aaElit", "Pdfdfd", "CBD"));
		articles.add(new Article("zhanna", "Adfdfd", "CBD"));
		articles.add(new Article("Ally", "Xdfdfd", "CBD"));

		dis.setArticles(articles);
		//dis.process();
		dis.setPageSize(5);
		//dis.gotoPage();
		//dis.gotoLastPage();
		//dis.gotoFristPage();
		dis.process();
		
	}
}
