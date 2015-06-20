package Control;
import Model.Article;
import View.Display;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Management {	
	ArrayList<Article> articles;
	private Display display;
	private ArrayList<Article> tempArticles;
	private File logfile;
	public Management() {
		//display.setTableStyle('\u2554', '\u2557', '\u255A', '\u255D', '\u2566', '\u2569', '\u2560', '\u256C', '\u2563', '\u2551', '\u2550');		
		/*for(int  i=0; i<100_000_000; i++){
			//articles.add(new Article("Vichea", "JAVA ", "CBD" , now));
			articles.add(new Article("Srey LeangHeng", "Web Application Development", "CBD" , now));
			//articles.add(new Article("Sun VicheyChetra", "Java Programming Language", "CBD" , now));	
		}*/
		/*String now = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
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
		articles.add(new Article("Hem Sarin", "C Programming", "CBD" , now));*/
		//long st=System.currentTimeMillis();
		//FileArticle.getInstance().writeFile(articles);
		//long ed=System.currentTimeMillis();
		//System.out.println((ed-st)/1000);
		//long ed1=System.currentTimeMillis();
		//System.out.println((ed1-ed)/1000);
		//tempArticles = articles;
		//sort("i", false);
		System.gc();
		String now = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
		logfile=new File("log.txt");
		articles = new ArrayList<Article>();
		display = new Display();
		display.setTableStyle('╔', '╗', '╚', '╝', '╦', '╩', '╠', '╬', '╣', '║', '═');
		
		System.out.print("Testing 1 000 000 objects (o)");
		System.out.print("\nTesting Read objects inside file (r)");
		String choose = new Scanner(System.in).next();
		if(choose.equalsIgnoreCase("o")){
			for(int  i=0; i<100_000; i++){
				articles.add(new Article("Srey LeangHeng", "Web Application Development", "CBD" , now));	
			}
		}else if(choose.equalsIgnoreCase("r")){
			if(FileArticle.getInstance().readFile().size()!=0){
				articles=FileArticle.getInstance().readFile();
			}else{
				System.out.print("No Data");				
			}
		}	
		display.setArticles(articles);
	}
	/**
	 * Function add new article object with no parameter and no return type
	 * inputing multiple object using do while loop
	 */
	public void add(){
		Scanner input = new Scanner(System.in);
		String author;
		String title;
		String content;
		do{
			System.out.print("Please Enter Author : ");
			author = input.nextLine();
			System.out.print("Please Enter Titile : ");
			title = input.nextLine();
			System.out.print("Please Enter Content: ");
			content = inputContent();
			insertArticle(author, title, content);
			System.out.print("Do you want to continues?(Y/N)");
			String confirm = input.nextLine();
			switch(confirm.toLowerCase()){
			case "y":
				break;
			default:
				return;
			}//End switch;
		}while(true);
		
	}//End of add();
	/**
	 * Function insertArticle with 3 parameter with no return type
	 * it's used for Validating Data
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
			waiting();
		} else {
			articles.add(new Article(author, title, content, new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date())));
		}
	}
	/**
	 * Function update one article object with no parameter and no return type  
	 * Case 1: Updating Author 
	 * Case 2: Updating Title 
	 * Case 3: Updating Content 
	 * Case 4: Updating All Fields
	 */
	public void update() {
		String author="";
		String title = "";
		String content="";
		
		Scanner input = new Scanner(System.in);
		System.out.print("Input ID to Update:");
		String id = input.next();
		sort("i", true);
		int index = search("i", id).get(0);
		if(index < 0){
			System.out.println("ID not found!");
			waiting();
			return;
		}
		String option = "";
		System.out.print("Update : Au) Author | T) Title) | C) Content | Al) All: ");
		option = input.nextLine();
		option = input.nextLine();
		switch (option.toLowerCase()) {
		case "au":/* Updating Author by ID */
			System.out.print("Enter Author: ");
			author = input.nextLine();
			articles.get(index).setAuthor(author);
			break;
		case "t":/* Updating Title by ID */
			System.out.print("Enter Title : ");
			title = input.nextLine();
			articles.get(index).setTitle(title);
			break;
			
		case "c":/* Updating Content by ID */
			System.out.print("Enter Content :");
			content = inputContent();
			articles.get(index).setContent(content);
			break;
		case "al": /* Updating All Fields by ID */
			System.out.print("Please Enter Author: ");
			author = input.nextLine();
			System.out.print("Please Enter Titile: ");
			title = input.nextLine();
			System.out.print("Please Enter Content: ");
			content = inputContent();
			
			if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
				System.out.println("Invalid value!");
				waiting();
				break;
			} else {
				articles.get(index).setData(author, title, content);
			}
			break;
		default:/* Not Permit invalid Key */
			System.err.println("Invalid");
			waiting();
			break;
		}
		System.out.println("Update Completed!");
/*Pisal*/FileArticle.getInstance().writeLog(logfile, new String[]{"Update-->",""+articles.get(index).getId(),author,title,content});
		waiting();
	}
	/**
	 * Function inputContent  
	 * @return content as multiple line of String from Keyboard
	 * Stop Scanner when user input period
	 */
	private String inputContent(){
		String content = "";
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			content += input.nextLine();
			if (content.endsWith("."))
				break;
		}//End while;
		return content;
	}//End of inputContent();
	/**
	 * Function search with two parameter with 4 options 
	 * By ID, By Author, By Title, and By PublishDate 
	 * each options split 2 condition in the linear loop 
	 * @param searchBy
	 *            : option to choose such as i, au, p, t
	 * @param key
	 *            : keyword to search
	 * @return as arraylist of object ID
	 * 		   if error it returns null
	 */
	public ArrayList<Integer> search(String searchBy, String key) {
		ArrayList<Integer> searchList = new ArrayList<Integer>();
		key = key.toLowerCase(); 
		sort("i", true);
	    switch(searchBy.toLowerCase()){    
	      case "i":	//search ID
	     	int index = Collections.binarySearch(articles, 
	                                             new Article("", "", "", "").clone(Integer.parseInt(key)),
	                                             new Comparator<Article>() {
	     		public int compare(Article article1, Article article2) {
	     			return article1.getId() < article2.getId() ? -1 : article1.getId() > article2.getId() ? 1 : 0;
	     		}
	     	});
	        searchList.add(index);
	        break;
	      case "au":	//search Author name
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
	      case "p":	//search PublishDate        	
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
	      case "t":	//search Title
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
	        System.out.println("No Option. Please Input Again.");
	        waiting();
	        return null;
	    }
		return searchList; 
	}
	/**
	 * Function delete with one parameter 
	 * For Deleting Article Object follow with the key given 
	 * @param Key
	 *            : Key as object ID to delete
	 */
	public void remove(String key) {
		sort("i", true);
		int index = search("i", key).get(0);
		if (index < 0) { // If value < 0 it means search not found;
			System.out.println("Invalid Key");
			waiting();
			return;
		}
		/*Pisal*/
		FileArticle.getInstance().writeLog(logfile, new String[]{"Remove-->",articles.get(index).toString()});
		articles.remove(index); /*				 */
		System.out.println("Remove Completed!");
		waiting();
	}
	/**
	 * Function sort with 2 parameter 
	 * For Sorting Data By ID, By Author, By Title, and By PublishDate	 
	 * @param SortBy
	 *            : key option to sort
	 * @param isAscending
	 *            : true Ascending, false Descending
	 */
	public void sort(String sortBy,boolean isAscending) {
		switch (sortBy.toLowerCase()) {
			case "i":// sort by id
				Collections.sort(articles, new Comparator<Article>() {
					@Override
					public int compare(Article art1, Article art2) {
						return art1.getId() > art2.getId() ? 1 : -1; /* Sort Object By Ascending */
					}
				});	
				break;
			
			case "au":// sort by author
				Collections.sort(articles, new Comparator<Article>() {
					@Override
					public int compare(Article art1, Article art2) {
						return art1.getAuthor().compareTo(art2.getAuthor());/* Sort Object By Ascending */
					}
				});
				break;	
			
			case "t":// Sort by title
				Collections.sort(articles, new Comparator<Article>() {
					@Override
					public int compare(Article art1, Article art2) {
						return art1.getTitle().compareTo(art2.getTitle());/* Sort Object By Ascending */
					}
				});
				break;
			
			case "p":// sort by Publish Date
				Collections.sort(articles, new Comparator<Article>() {
					@Override
					public int compare(Article art1, Article art2) {
						return art1.getPublishDate().compareTo(art2.getPublishDate());/* Sort Object By Ascending */
					}
				});			
				break;	
			default:
				System.out.println("Invalid Type!");
				waiting();
		}//End of switch
		if(!isAscending)
			Collections.reverse(articles); /* Sort Object by Descending */
		//display();
	}//End of sort();
	/**
	 * Function display without parameter and no return type
	 * for manipulating articles and display all of elements
	 */
	public void display(){
		Scanner input = new Scanner(System.in);
		String option;
		String key;
		String sortBy = "i";
		boolean isAscending = false;
		sort(sortBy, isAscending);
		display.process();	
		do{
			System.out.print("Please, Input Your Option-->");
			option = input.nextLine();
			switch(option.toLowerCase()){
			case "a":
				add();
				break;
			case "r":
				System.out.print("Input ID to remove: ");
				key = input.next();
				remove(key);
				break;
			case "u":
				update();
				break;
			case "s": //Search
				System.out.print("I) ID | Au)Author | T)Title | P)Publish Date--> ");
				String searchBy = input.nextLine();
				System.out.print("Input Key to search: ");
				key = input.nextLine();
				ArrayList<Integer>searchList = search(searchBy, key);
				tempArticles = new ArrayList<Article>();
				for(Integer index : searchList){
					if(index < 0){
						System.err.println("Key not found!");
						waiting();
						tempArticles = articles;
						break;
					}
					tempArticles.add(articles.get(index));
				}
				display.setArticles(tempArticles);
				break;
			case "ss":
				System.out.print("Sort By: I) ID | Au) Author | T) Title | P) Publish Date --> ");
				sortBy = input.next();
				System.out.print("Order By: ASC or DSC --> ");
				String orderBy = input.next();
				
				if (orderBy.equalsIgnoreCase("asc")) 
					isAscending = true;
				else
					isAscending = false;
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
				//tempArticles = articles;
				display.setArticles(articles);
				display.gotoFirstPage();
				break;
			case "v":
				System.out.print("Input ID to view: ");
				String id = input.next();
				sort("i", true);
				int index = search("i", id).get(0);
				if(index < 0){
					System.out.println("ID not found!");
					waiting();
					break;
				}
				Article art = articles.get(index);
				display.viewDetail(art);
				waiting(); 
				break;
				/*Pisal*/	/*Menu File*/
			case "w":// Write To File
				FileArticle.getInstance().writeFile(articles);
				break;
			case "re":// Read From File
				System.out.print("Read from Backup File (Y/N)? : ");
				String choice=input.next();
				if(choice.equalsIgnoreCase("y")){
					System.out.print("Please input file name : ");
					String filename=input.next();
					articles=FileArticle.getInstance().readFile(new File(filename));
				}else{
					articles=FileArticle.getInstance().readFile();
				}				
				display.setArticles(articles);
				break;
			case "b":// Back Up File
				System.out.print("Please input destination file : ");
				String destination = input.next();
				try {
					FileArticle.getInstance().copyFile(FileArticle.getInstance().sourceFile(), new File(destination));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			/*End Menu File*/
			case "e" :
				return;
			default:
				break;
			}//End of switch;
			sort(sortBy, isAscending);
			display.process();
		}while(true);
	}//End of function display;
	/**no idea what this do
	 * 
	 */
	private void waiting(){
		System.out.print("Press Enter to continue...");
		try
        {
            System.in.read();
        }  
        catch(Exception e){}
	}
}// End of class;
