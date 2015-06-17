package Control;
import Model.Article;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import Sort.*;
import View.Display;

public class Management {
	
	ArrayList<Article> articles;
	ArrayList<Integer> indices;
	private Display display;
	private ISort sortBy;
	private ArrayList<Article> subPages;
	public Management() {
		articles = new ArrayList<Article>();
		display = new Display();//
		display.setTableStyle('\u2554', '\u2557', '\u255A', '\u255D', '\u2566', '\u2569', '\u2560', '\u256C', '\u2563', '\u2551', '\u2550');
		//display.setTableStyle('╔', '╗', '╚', '╝', '╦', '╩', '╠', '╬', '╣', '║', '═');
		display.setArticles(articles);
		indices = new ArrayList<Integer>();
		/*for(int  i=0; i<1e6; i++){
			articles.add(new Article("Vichea", "JAVA ", "CBD" , now));
	}*/
		String now = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());;
		articles.add(new Article("Srey LeangHeng", "Web Application Development", "CBD" , now));
		articles.add(new Article("Sun VicheyChetra", "Java Programming Language", "CBD" , now));	
		articles.add(new Article("Dara Po", "Object Oreinted Analysis & Design", "CBD" , now));
		articles.add(new Article("Ke Pisal", "Unix System & Administrator", "CBD" , now));
		articles.add(new Article("Sok Lungdy", "Database Analysis & Design", "CBD" , now));
		articles.add(new Article("Sokngim Sa", "Data Communication", "CBD" , now));
		articles.add(new Article("Polyvan Ouk", "Data Structure & Algorithm", "CBD" , now));
		articles.add(new Article("Nita Best", "Computer Artchitecture", "CBD" , now));
		articles.add(new Article("Mom Kunthy", "C++ Programming", "CBD" , now));
		articles.add(new Article("Sambo Siang", "Visual Basic .Net Prgramming", "CBD" , now));
		articles.add(new Article("Elite Chorn", "Management Information System", "CBD" , now));
		articles.add(new Article("Ros Channa", "Software Engineering", "CBD" , now));
		articles.add(new Article("Hem Sarin", "C Programming", "CBD" , now));
		//sort(new SortById(), false);
		
	}

	/**
	 * Function Add : For Adding Records to Articles with ValidateData. Not
	 * Permit for null value
	 */
	public void add() {
		Scanner input = new Scanner(System.in);
		String author;
		String title;
		String content;
		do{
			content = "";
			System.out.println("Please Enter Author : ");
			author = input.nextLine();
			System.out.println("Please Enter Titile : ");
			title = input.nextLine();
			System.out.println("Please Enter Content: ");
			while (input.hasNext()) {
				content += input.nextLine();
				if (content.endsWith("."))
					break;
			}
			this.validateData(articles, author.trim(), title.trim(),
					content.trim());
		}while(isExitInput());
		input.close();
	}
	/**
	 * Function confirmKey() verified key after each perform (Y/N)
	 */
	private boolean isExitInput() {
		Scanner input = new Scanner(System.in);
		System.out.print("Do you want to continues?(Y/N)");
		String option = input.nextLine();
		switch (option.toLowerCase()) {
		case "y":
			input.close();
			return true;
		case "n":
			input.close();
			return false;
		default:
			System.out.println("Invalid key! Please Input again.");
			input.close();
			isExitInput();
			break;
		}
		input.close();
		return false;
	}
	/**
	 * Function with parameter for Validating Data
	 * 
	 * @param articles
	 *            : ArrayList<Article> articles
	 * @param author
	 *            : Author Name
	 * @param title
	 *            : Title
	 * @param content
	 *            : content
	 */
	private void validateData(ArrayList<Article> articles, String author,
			String title, String content) {
		
		if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
			System.out.println("No value");
		} else {
			articles.add(new Article(author, title, content,"asdf"));
		}
	}
	/**
	 * Function with parameter For Updating Data Case 1: Updating Author Case 2:
	 * Updating Title Case 3: Updating Content Case 4: Updating All Fields
	 * 
	 * @param SearchBy
	 *            : SearchById Only
	 * @param Key
	 *            : Key as ID
	 */
	

	public void update(String key) {
		String author;
		String title;
		String content;
		String modifiedDate = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
		
		Scanner input = new Scanner(System.in);
		byte choose = 0;
		//this.searchBy = new SearchById();
		System.out.println("Update : 1.(Author) 2.(Title) 3.(Content) 4.(All)");
		choose = input.nextByte();
		switch (choose) {
		/* Updating Author by ID */
		case 1:
			System.out.println("Enter Author : ");
			input.nextLine();
			author = input.nextLine();
			//articles.get(this.searchBy.search(articles, key).get(0)).setAuthor(author);
			//articles.get(this.searchBy.search(articles, key).get(0)).setModifiedDate(modifiedDate);
			break;
			/* Updating Title by ID */
		case 2:
			System.out.println("Enter Title : ");
			input.nextLine();
			title = input.nextLine();
			//articles.get(this.searchBy.search(articles, key).get(0)).setTitle(title);
			//articles.get(this.searchBy.search(articles, key).get(0)).setModifiedDate(modifiedDate);
			break;
			/* Updating Content by ID */
		case 3:
			System.out.println("Enter Content : ");
			input.nextLine();
			content = input.nextLine();
			while (input.hasNext()) {
				content += input.nextLine();
				if (content.endsWith("."))
					break;
			}
			//articles.get(this.searchBy.search(articles, key).get(0)).setContent(content);
			//articles.get(this.searchBy.search(articles, key).get(0)).setModifiedDate(modifiedDate);
			break;
			/* Updating All Fields by ID */
		case 4: 
			content = "";
			System.out.println("Please Enter Author : ");
			input.nextLine();
			author = input.nextLine();
			System.out.println("Please Enter Titile : ");
			title = input.nextLine();
			System.out.println("Please Enter Content: ");
			while (input.hasNext()) {
				content += input.nextLine();
				if (content.endsWith("."))
					break;
				}
			if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
				System.out.println("Invalid value!");
				break;
			} else {
				//articles.get(this.searchBy.search(articles, key).get(0)).setData(author, title, content, modifiedDate);
			}
			break;
			/* Not Permit invalid Key */
		default:
			System.err.println("Invalid");
			// update(searchBy, key);
			break;
		}
		input.close();
		System.out.println("Saved");
	}
	/**
	 * Function with parameter For Searching Data By All kind of Searching By ID
	 * By Author By Title By PublishDate By ModifiedDate
	 * 
	 * @param articles
	 *            : arraylist of object to search
	 * @param searchBy
	 *            : option to choose from 0 - 3
	 * @param key
	 *            : keyword to search
	 */
	public static ArrayList<Integer> search(ArrayList<Article> articles, int searchBy, String key) {
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		key = key.toLowerCase(); 
	    switch(searchBy){    
	      case 0:	//search ID
	     		int index = Collections.binarySearch(articles, 
	                                             new Article("", "", "", "").clone(Integer.parseInt(key)),
	                                             new Comparator<Article>() {
	          public int compare(Article art1, Article art2) {
	            return art1.getId() < art2.getId() ? -1 : art1.getId() > art2.getId() ? 1 : 0; 
	          }
	        });
	        resultList.add(index);
	        break;
	      case 1:	//search Authorname
	        for (int startIndex = 0, halfIndex = articles.size() / 2; 
	             startIndex < articles.size() / 2; 
	             startIndex++, halfIndex++) {
	          if (articles.get(startIndex)
	                      .getAuthor()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            resultList.add(startIndex);
	          }
	          if (articles.get(halfIndex)
	                      .getAuthor()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            resultList.add(halfIndex);
	          }
	        }   
	        if ((articles.size() % 2) != 0) {
	          if (articles.get(articles.size() - 1)
	                      .getAuthor()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            resultList.add(articles.size() - 1);            
	          }
	        }
	        break;
	      case 2:	//search PublishDate        	
	        for (int startIndex = 0, halfIndex = articles.size() / 2; 
	             startIndex < articles.size() / 2; 
	             startIndex++, halfIndex++) {
	          if (articles.get(startIndex)
	              .getPublishDate()
	              .toLowerCase()
	              .startsWith(key)) {
	            resultList.add(startIndex);
	          }
	          if (articles.get(halfIndex)
	              .getPublishDate()
	              .toLowerCase()
	              .startsWith(key)) {
	            resultList.add(halfIndex);
	          }
	        }
	        if ((articles.size() % 2) != 0) {
	          if (articles.get(articles.size() - 1)
	              .getPublishDate()
	              .toLowerCase()
	              .startsWith(key)) {
	            resultList.add(articles.size() - 1);
	          }
	        }
	        break;
	      case 3:	//search Title
	        for (int startIndex = 0, halfIndex = articles.size() / 2; 
	             startIndex < articles.size() / 2; 
	             startIndex++, halfIndex++) {
	          if (articles.get(startIndex)
	                      .getTitle()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            resultList.add(startIndex);
	          }
	          if (articles.get(halfIndex)
	                      .getTitle()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            resultList.add(halfIndex);
	          }
	        }
	        if ((articles.size() % 2) != 0) {
	          if (articles.get(articles.size() - 1)
	                      .getTitle()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            resultList.add(articles.size() - 1);
	          }
	        }
	        break;
	      default:				
	        System.out.println("No Option. Please Input Again.");
	        return null;
	    }
		return resultList; 
	}

	/**
	 * Function with parameter For Deleting Data follow by Key
	 * 
	 * @param Key
	 *            : Key as ID
	 */
	public void remove(String key) {
		//search(new SearchById(), key);
		if (indices.get(0) < 0) { // If value < 0 it means search not found;
			System.err.println("Invalid Key");
			return;
		}
		articles.remove((int) indices.get(0)); /*
												 * indices value came from
												 * method search()
												 */
		System.out.println("Remove Completed!");
		//display();
	}
	/**
	 * Function with parameter For Sorting Data By ID By Author By Title By
	 * PublishDate BY ModifiedDate
	 * 
	 * @param SortBy
	 *            : new Objects sort By
	 * @param isAscending
	 *            : true Ascending, false Descending
	 */
	public void sort(ISort sortBy, boolean isAscending) {
		this.sortBy = sortBy;
		this.sortBy.sort(articles, isAscending);
		display();
	}
	/**
	 * Function without parameter for display all of elements
	 */
	public void display() {
		Scanner input = new Scanner(System.in);
		String option;
		String key;
		display.process();
		do{
			System.out.print("Please, Input Your Option-->");
			option = input.next();
			switch(option.toLowerCase()){
			case "a":
				add();
				break;
			case "r":
				System.out.print("Input ID: ");
				key = input.next();
				remove(key);
				break;
			case "u":
				System.out.print("Input ID: ");
				key = input.next();
				update(key);
				break;
			case "s":
//				System.out.print("a) Author, t) Title, pd) Publish Date, md)Modified Date");
//				String search = input.next();
//				ISearch searchBy;
//				switch(search.toLowerCase()){
//				case "a":
//					searchBy = new SearchByAuthor();
//					break;
//				case "t":
//					searchBy = new SearchByTitle();
//					break;
//				case "pd":
//					searchBy = new SearchByPublishDate();
//					break;
//				case "md":
//					searchBy = new SearchByModifiedDate();
//					break;
//				default:
//					searchBy = new SearchById();
//					break;
//				}
//				System.out.print("Please, Input Key: ");
//				key = input.next();
//				//search(searchBy, key);
				break;
			case "ss":
				System.out.print("a(Author, t(Title, pd(Publish Date, md(Modified Date");
				String sort = input.next();
				ISort sortBy;
				switch(sort.toLowerCase()){
				case "a":
					sortBy = new SortByAuthor();
					break;
				case "t":
					sortBy = new SortByTitle();
					break;
				case "pd":
					sortBy = new SortByPublishDate();
					break;
				case "md":
					sortBy = new SortByModifiedDate();
					break;
				default:
					sortBy = new SortById();
					break;
				}
				boolean isAscending;
				System.out.print("ASC or DSC"); 
				String isAsc = input.next();
				if(isAsc.equalsIgnoreCase("asc")){
					isAscending = true;
				}
					
				else {
					isAscending = false;
				}
				sort(sortBy, isAscending);
				break;
			case "g":
				System.out.print("Input Page Number: ");
				int pageNumber = input.nextInt();
				display.gotoPage(pageNumber);
				break;
			case "#":
				System.out.print("Input Page Size: ");
				int pageSize = input.nextInt();
				display.setPageSize(pageSize);
				break;
			case "p":
				display.gotoPreviousPage();
				break;
			case "n":
				display.gotoNextPage();
				break;
			case "f": 
				display.gotoFirstPage();
				break;
			case "l": 
				display.gotoLastPage();
				break;
			case "e":
				input.close();
				return;
			}
			display.process();

		}while(true);
	}
}// End of class;
