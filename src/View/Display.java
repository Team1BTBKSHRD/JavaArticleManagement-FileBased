package View;

import java.util.ArrayList;
import java.util.List;
import Model.Article;

public class Display {
	
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

	private static final String[] headers = { "ID", "AUTHOR ", "TITLE ", "PUBLISH DATE"};

	ArrayList<Article> articles;
	List<Article> subPages; /* Use for store a set of articles for view */
	
	private int currentPage; /* Current page position */
	private int pageSize; /* Number of records for view */
	private int totalPage; /* Store All Total Pages */
	
	/*Method setPageSize()
	 * Use for set number of record for view
	 * pageSize parameter is a number of record that we want to view */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		repaginate(); /* After set a new value we need to repaginate */
	}

	/*Method setArticles()
	 * Use for set all of articles */
	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	/*Method gotoPage
	 * Use for set number of page that the client want to visit
	 * pageNumbe parameter is a number of page*/
	public void gotoPage(int pageNumber) {
	    currentPage = pageNumber - 1; /* currentPage value started from 0*/
	    if(currentPage < 0){ /*Goto first page if current page < 0*/
	    	gotoFirstPage();
	    }
	    else if(currentPage > totalPage){ /*Goto last page if current page > totalPage */
	    	gotoLastPage();
	    }
	    repaginate(); /* repaginate after set value */
	}
	
	/*Default constructor
	 * default currentPage = 0
	 * default page size = 5
	 * set default table style
	 * and regainate page again*/
	public Display() {
		currentPage = 0;
		pageSize = 5;
		articles =  new ArrayList<Article>();
		topLeft = topRight = buttomLeft = buttomRight = middleTop = middleButtom = middleLeft = middleCenter = middleRight = '+';
		horizontalLine = '-';
		verticalLine = '|';
		repaginate();
	}

	/*Constructor with parameters 
	 * Use for set default table style
	 * set current page = 0
	 * page size = 5
	 * and then start to repaginate*/
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

	/*Method gotoNextPage()
	 * Use for increase the current page*/
	public void gotoNextPage(){
		if(currentPage < totalPage){ /* if current page is not the last page increase current page*/
			currentPage++;
		} else{ /* else set current page into the first page */
			gotoFirstPage();
		}
		repaginate(); /* After set repaginate page again*/
	}
	
	/*Method gotoPreviousPage()
	 * Use for decrease current page*/
	public void gotoPreviousPage(){
		if(currentPage >= 0){ /*if current page is not the start page increase current page*/
			currentPage--;
		}
		else{ /* else set current page into the last page */
			gotoLastPage();
		}
		repaginate(); /* After set repaginate page again*/
	}
	
	/*Method gotoFirstPage
	 * Use for set current page into the first page*/
	public void gotoFirstPage(){
		currentPage = 0;
	}
	
	/*Method gototLastPage()
	 * Use for set current page into the last page*/
	public void gotoLastPage(){
		currentPage = totalPage - 1;
	}
	
	/*Method repaginate()
	 * Use for get objects for view by page size and current page (add into subPages)*/
	private void repaginate() {
		if (!articles.isEmpty()) {
			totalPage = (int) Math.ceil(this.articles.size() / (float)pageSize); /*Calculate the total page*/
			int start = currentPage * pageSize; /* Start index */
			int end = start + pageSize - 1; /* End index */
			if (end >= articles.size()) { /* if end value is out of bound of ArrayList */
				end = articles.size() - 1; /* set end index = the last index of array */
			}
			if (start >= articles.size()) { /* if start index >= total size of ArrayList) */
				gotoFirstPage(); /* set current page into the first page */
				repaginate(); /* repaginate page again */
			} else if (start < 0) { /* if start index < 0 */
				gotoLastPage(); /* set current page into the last page */
				if (articles.size() % pageSize == 0) { /* if total size is even number */
					currentPage--; /* decrease current page */
				}
				repaginate(); /* repaginate page again */
			} else { /* else start < total records */
				subPages = articles.subList(start, end + 1); /* Separate them into subPages for display */
			}
		}
	}

	/*Method process()
	 * Use for out put the records of subPages
	 * */
	public void process() {
		repaginate();
		drawTable(subPages);
	}
	
	/*Method setTableStyle()
	 * Use for set table style by its parameter */
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

	/* Method header()
	 * Use for output the organization's name and the project's name*/
	public void header(int []maxColumns, int totalLenght) {
		final String ORGANIZATION_NAME = "KOREAN SOFTWARE HRD CENTER";
		final String PROJECT_NAME = "ARTICLES MANAGEMENT SYSTEM";
		String head = "";
		//head = addHorizontalLine(head, maxColumns, topLeft, horizontalLine, topRight);
		//head = addLetter(head, verticalLine, 1);
		head = addLetter(head, '_', (totalLenght - ORGANIZATION_NAME.length()) / 2);
		head += ORGANIZATION_NAME;
		head = addLetter(head, '_', (totalLenght - ORGANIZATION_NAME.length()) /2);
		//head = addLetter(head, verticalLine, 1) + "\n";
		head += "\n";
		//head = addLetter(head, verticalLine, 1);
		head = addLetter(head, '*', (totalLenght - PROJECT_NAME.length()) / 2);
		head += PROJECT_NAME;
		head = addLetter(head, '*', (totalLenght - PROJECT_NAME.length()) / 2);
		//head = addLetter(head, verticalLine, 1) + "\n";
		
		//head += addHorizontalLine(head, maxColumns, buttomLeft, horizontalLine, buttomRight);
		System.out.println(head);
	}

	public void menu(int[] maxColumns, int totalLenght) {
		final String MENU1 = "F) First | P) Previous | N) Next | L) Last";
		final String MENU2 = "A) Add | R) Remove | S)Search |  U) Update | SS) Sort";
		final String MENU3 = "H) Home | V) View Detail | G) Goto | #) Set Row | E) Exit";
		String strMenu = "";
		strMenu = addHorizontalLine(strMenu, maxColumns, topLeft, horizontalLine, topRight);
		//strMenu = addLetter(strMenu, verticalLine, 1);
		strMenu = addLetter(strMenu, ' ', (totalLenght - MENU1.length()) / 2);
		strMenu += MENU1;
		strMenu = addLetter(strMenu, ' ', totalLenght - ((totalLenght - MENU1.length())));
		//strMenu = addLetter(strMenu, verticalLine, 1) + "\n";
		strMenu += "\n";
		
		//strMenu = addLetter(strMenu, verticalLine, 1);
		strMenu = addLetter(strMenu, ' ', (totalLenght - MENU2.length()) / 2);
		strMenu += MENU2;
		strMenu = addLetter(strMenu, ' ', totalLenght - ((totalLenght - MENU2.length())));
		//strMenu = addLetter(strMenu, verticalLine, 1) + "\n";
		strMenu += "\n";
		
		//strMenu = addLetter(strMenu, verticalLine, 1);
		strMenu = addLetter(strMenu, ' ', (totalLenght - MENU3.length()) / 2);
		strMenu += MENU3;
		strMenu = addLetter(strMenu, ' ', totalLenght - ((totalLenght - MENU3.length())));
		//strMenu = addLetter(strMenu, verticalLine, 1) + "\n";
		strMenu += "\n";
		
		strMenu += addHorizontalLine(strMenu, maxColumns, buttomLeft, horizontalLine, buttomRight);
		System.out.println(strMenu);
	}

	/*
	 * Method maxColumnsLength() Use for fine maximum length of every columns
	 */
	private int[] maxColumnsLength(List<Article> articles) {
		int[] maxLengths = new int[] { headers[0].length(),
				headers[1].length(), headers[2].length(), headers[3].length()};

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
				if(article.getAuthor().length() > 10)
					maxLengths[1] = 13;
				else
					maxLengths[1] = article.getAuthor().length();

			if (article.getTitle().length() > maxLengths[2]) /*
															 * Find maximum
															 * length of column
															 * Title
															 */
				if(article.getTitle().length() > 23)
					maxLengths[2] = 23;
				else
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

		}
		return maxLengths; /* return maximum length of every columns */
	}

	/*
	 * Method addLetter() Use for add letter into string string parameter is
	 * original string, letter is the character we need to add, letterNumber parameter is the number of latter are going
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
	
	/*Method sumColumnsLenght
	 * Use for sum each columns length */
	private int sumColumnsLength(int[] maxColumns){
		int sum = 0;
		for(int columnLength : maxColumns){
			sum += columnLength + 1;
		}
		return sum;
	}
	
	/*Method drawTable()
	 * Use for draw table, header, and menu bar
	 * articles parameter is the articles we want to put into the table*/
	public void drawTable(List<Article> articles) {

		int[] maxColumns = maxColumnsLength(articles); /* Find maximum length for every columns*/
		int totalLenght = sumColumnsLength(maxColumns);/* calculate total length */
		String table = "";
		/* Header Block */
		header(maxColumns, totalLenght); /* Output header */
		// First line of table;
		table += addHorizontalLine(table, maxColumns, topLeft, middleTop,
				topRight);
		// Add Header Title;
		int index = 0;
		for (String header : headers) {
			table += Character.toString(verticalLine); // "?";
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

			table += Character.toString(verticalLine) + article.getId(); //Add ID
			table = addLetter(table, ' ',
					maxColumns[0] - Integer.toString(article.getId()).length());

			if(article.getAuthor().length() > 13)
				table += Character.toString(verticalLine) + article.getAuthor().substring(0, 10) + "..."; //Add Author
			else
				table += Character.toString(verticalLine) + article.getAuthor();
			table = addLetter(table, ' ', maxColumns[1]
					- article.getAuthor().length());

			if(article.getTitle().length() > 23)
				table += Character.toString(verticalLine) + article.getTitle().substring(0, 20) + "..."; //Add Title
			else
				table += Character.toString(verticalLine) + article.getTitle();
			table = addLetter(table, ' ', maxColumns[2]
					- article.getTitle().length());

			table += Character.toString(verticalLine) //Add Publish Date
					+ article.getPublishDate();
			table = addLetter(table, ' ', maxColumns[3]
					- article.getPublishDate().length());

			table += Character.toString(verticalLine);
			table += "\n";
		}
		// End of Body

		// Footer;
		table += addHorizontalLine(table, maxColumns, buttomLeft, middleButtom,
				buttomRight);
		// End of Footer;
		System.out.println(table);
		System.out.println("Total Records:" + this.articles.size()); /*Output total records */
		System.out.println("Pages: " + (this.currentPage + 1) + "/" + totalPage); /* Output current page and total pages*/
		System.out.println();
		//Menu bar;
		menu(maxColumns, totalLenght); /* Output Menu Bar */
	}
	public void viewDetail(Article article){
		System.out.println("ID: " + article.getId());
		System.out.println("Author: " + article.getAuthor());
		System.out.println("Title: " + article.getTitle());
		System.out.println("Publish Date: " + article.getPublishDate());
		System.out.println("Content: " + article.getContent());
	}
}
