package Control;
import Model.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import View.Display;

public class Management {
	
	ArrayList<Article> articles;
	ArrayList<Integer> indices;
	private Display display;
	public ArrayList<Article> tempArticles;
	public Management() {
		articles = new ArrayList<Article>();
		display = new Display();//
		display.setTableStyle('\u2554', '\u2557', '\u255A', '\u255D', '\u2566', '\u2569', '\u2560', '\u256C', '\u2563', '\u2551', '\u2550');
		//display.setTableStyle('╔', '╗', '╚', '╝', '╦', '╩', '╠', '╬', '╣', '║', '═');
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

		tempArticles = articles;
		//sort("i", false);
		display.setArticles(tempArticles);
	}

	/**
	 * Function Add : For Adding Records to Articles with ValidateData. Not
	 * Permit for null value
	 */
	public void add(){
		Scanner input = new Scanner(System.in);
		String author;
		String title;
		String content;
		do{
			content = "";
			System.out.print("Please Enter Author : ");
			author = input.next();
			System.out.print("Please Enter Titile : ");
			title = input.next();
			System.out.print("Please Enter Content: ");
			while(input.hasNext()) {
				content += input.next();
				if (content.endsWith(".")) break;
			}
			insertArticle(author, title, content);
			System.out.print("Do you want to continues?(Y/N)");
			String confirm = input.next();
			switch(confirm.toLowerCase()){
			case "y":
				break;
			default:
				return;
			}//End switch;
		}while(true);
		
	}//End of add();
	
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
	private void insertArticle(String author, String title, String content) {
		if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
			System.out.println("Invalid value");
		} else {
			articles.add(new Article(author, title, content, new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date())));
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
	

	public void update() {
		String author;
		String title;
		String content;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Input ID to Update:");
		String id = input.next();
		int index = search(0, id).get(0);
		if(index < 0){
			System.out.println("ID not found!");
			return;
		}
		byte option = 0;
		System.out.print("Update : 1) Author | 2) Title) | 3) Content | 4) All");
		option = input.nextByte();
		switch (option) {
		case 1:/* Updating Author by ID */
			System.out.print("Enter Author: ");
			author = input.next();
			articles.get(index).setAuthor(author);
			break;
		case 2:/* Updating Title by ID */
			System.out.print("Enter Title : ");
			title = input.next();
			articles.get(index).setTitle(title);
			break;
			
		case 3:/* Updating Content by ID */
			System.out.print("Enter Content :");
			content = "";
			while (input.hasNext()) {
				content += input.nextLine();
				if (content.endsWith("."))
					break;
			}
			articles.get(index).setContent(content);
			break;
		case 4: /* Updating All Fields by ID */
			System.out.print("Please Enter Author: ");
			author = input.next();
			System.out.print("Please Enter Titile: ");
			title = input.next();
			System.out.print("Please Enter Content: ");
			content = "";
			while (input.hasNext()) {
				content += input.nextLine();
				if (content.endsWith("."))
					break;
			}//End while;
			if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
				System.out.println("Invalid value!");
				break;
			} else {
				articles.get(index).setData(author, title, content);
			}
			break;
		default:/* Not Permit invalid Key */
			System.err.println("Invalid");
			break;
		}
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
	public ArrayList<Integer> search(int searchBy, String key) {
		ArrayList<Integer> searchList = new ArrayList<Integer>();
		key = key.toLowerCase(); 
	    switch(searchBy){    
	      case 0:	//search ID
				Collections.sort(articles, new Comparator<Article>() {
					@Override
					public int compare(Article art1, Article art2) {
						// TODO Auto-generated method stub

						return art1.getId() > art2.getId() ? 1 : -1;
						/* Sort Object By Ascending */

					}
				});

				int index = Collections.binarySearch(articles, 
	                                             new Article("", "", "", "").clone(Integer.parseInt(key)),
	                                             new Comparator<Article>() {
	          public int compare(Article art1, Article art2) {
	            return art1.getId() < art2.getId() ? -1 : art1.getId() > art2.getId() ? 1 : 0; 
	          }
	        });
	        searchList.add(index);
	        break;
	      case 1:	//search Author name
	        for (int startIndex = 0, halfIndex = articles.size() / 2; 
	             startIndex < articles.size() / 2; 
	             startIndex++, halfIndex++) {
	          if (articles.get(startIndex)
	                      .getAuthor()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            searchList.add(startIndex);
	          }
	          if (articles.get(halfIndex)
	                      .getAuthor()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            searchList.add(halfIndex);
	          }
	        }   
	        if ((articles.size() % 2) != 0) {
	          if (articles.get(articles.size() - 1)
	                      .getAuthor()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            searchList.add(articles.size() - 1);            
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
	            searchList.add(startIndex);
	          }
	          if (articles.get(halfIndex)
	              .getPublishDate()
	              .toLowerCase()
	              .startsWith(key)) {
	            searchList.add(halfIndex);
	          }
	        }
	        if ((articles.size() % 2) != 0) {
	          if (articles.get(articles.size() - 1)
	              .getPublishDate()
	              .toLowerCase()
	              .startsWith(key)) {
	            searchList.add(articles.size() - 1);
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
	            searchList.add(startIndex);
	          }
	          if (articles.get(halfIndex)
	                      .getTitle()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            searchList.add(halfIndex);
	          }
	        }
	        if ((articles.size() % 2) != 0) {
	          if (articles.get(articles.size() - 1)
	                      .getTitle()
	                      .toLowerCase()
	                      .startsWith(key)) {
	            searchList.add(articles.size() - 1);
	          }
	        }
	        break;
	      default:				
	        //System.out.println("No Option. Please Input Again.");
	        return null;
	    }
		return searchList; 
	}

	/**
	 * Function with parameter For Deleting Data follow by Key
	 * 
	 * @param Key
	 *            : Key as ID
	 */
	public void remove(String key) {
		int index = search(0, key).get(0);
		if (index < 0) { // If value < 0 it means search not found;
			System.err.println("Invalid Key");
			return;
		}
		articles.remove(index); /*
												 * indices value came from
												 * method search()
												 */
		System.out.println("Remove Completed!");
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
	public void sort(String sortBy,boolean isAscending) {
		switch (sortBy) {
		// sort by id
		case "i":
			Collections.sort(tempArticles, new Comparator<Article>() {
				@Override
				public int compare(Article art1, Article art2) {
					// TODO Auto-generated method stub

					return art1.getId() > art2.getId() ? 1 : -1;
					/* Sort Object By Ascending */

				}
			});
			
			break;
		// sort by author
		case "au":
			System.err.println("Author");
			Collections.sort(tempArticles, new Comparator<Article>() {

				@Override
				public int compare(Article art1, Article art2) {
					// TODO Auto-generated method stub
					return art1.getAuthor().compareTo(art2.getAuthor());
					/* Sort Object By Ascending */
				}
			});
			break;
			
		// Sort by title
		case "t":
			// TODO Auto-generated method stub
			Collections.sort(tempArticles, new Comparator<Article>() {

				@Override
				public int compare(Article art1, Article art2) {
					// TODO Auto-generated method stub

					return art1.getTitle().compareTo(art2.getTitle());
					/* Sort Object By Ascending */
				}
			});
			break;
		// sort by Publish Date
		case "p":
			Collections.sort(tempArticles, new Comparator<Article>() {

				@Override
				public int compare(Article art1, Article art2) {
					// TODO Auto-generated method stub

					return art1.getPublishDate().compareTo(art2.getPublishDate());
					/* Sort Object By Ascending */
				}
			});			
			break;		
		} //end switch
		if(!isAscending)
			Collections.reverse(tempArticles); /* Sort Object by Descending */

		
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
				update();
				break;
			case "s":
				System.out.print("0 (ID, 1(Author, 2(Publish Date, 3)Title");
				int searchBy = input.nextInt();
				System.out.print("Input Key:");
				key = input.next();
				ArrayList<Integer>ss = search(searchBy, key);
				tempArticles  = new ArrayList<Article>();
				for(Integer s : ss){
					//System.out.println(articles.get(s));
					tempArticles.add(articles.get(s));
				}
				display.setArticles(tempArticles);
				break;
			case "ss":
				System.out.print("Sort By: I)D, Au)thor, T)itle, P)ublish Date --> ");
				String sortBy = input.next();
				
				System.out.print("Order By: ASC or DSC --> ");
				String isAsc = input.next();
				if (isAsc.equalsIgnoreCase("asc")) {
					sort(sortBy.toLowerCase(), true);
				}

				else if(isAsc.equalsIgnoreCase("dsc")) {
					sort(sortBy.toLowerCase(), false);
				}
				else{
					System.err.println("Invalid Input!!!");
				}
				
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
			case "h":
				tempArticles = articles;
				display.setArticles(tempArticles);
				display.gotoFirstPage();
				break;
			case "v":
				System.out.print("Input ID:");
				String id = input.next();
				Article art = articles.get(search(0, id).get(0));
				display.viewDetail(art);
				input.next();
				break;
			case "e":
				return;
			}//End of switch;
			display.process();

		}while(true);
	}//End of function display;
}// End of class;
