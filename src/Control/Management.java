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
	private ArrayList<Article> articles;
	private Display display;
	private ArrayList<Article> tempArticles;
	private LogFile logfile;
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
		try {
			System.gc();		
			logfile = LogFile.getLogFile();
			articles = new ArrayList<Article>();		
			
			String choose = getStringKeyboard("Test creating objects (o)\nRead Object From File ArticleData.bin (Any Key)");
			if(choose.equalsIgnoreCase("o")){
				int listSize = getNumberKeyboard("Number of object to create: ");
				for(int  i=0; i<listSize; i++){
					articles.add(new Article("HengWondering", "Web Application Development", "CBD" , currentDate()));	
				}
			}else{ //Any Key
				ArrayList<Article> tmpList = FileArticle.getInstance().readFile();
				if(tmpList.size()!=0){
					articles = tmpList;
				}else{
					System.out.print("No Data");				
				}
				Article.MAX_ID = articles.get(0).getId() + 1;
			}
			
			display = new Display();
			display.setTableStyle('╔', '╗', '╚', '╝', '╦', '╩', '╠', '╬', '╣', '║', '═');
			display.setArticles(articles);
		} catch (Exception e) {
			logfile.writeLogException(e, "constructor", "Management");
		}
	}
	/**
	 * Function currentDate with no parameter and String as return type
	 * @return string of current date and time
	 */
	private String currentDate(){
		return  new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
	}
	/**
	 * Function inputContent  
	 * @return content as multiple line of String from Keyboard
	 * Stop Scanner when user input period
	 */
	private String inputContent(){
		StringBuilder contents = new StringBuilder();
		try {
			Scanner put = new Scanner(System.in);			
			while (put.hasNext()) {
				contents.append(put.next());				
				if (contents.toString().endsWith("...")) {
					contents.setLength(contents.length() - 3);
					break;
				}
				contents.append("\n");
			}//End while;				
		} catch (Exception e) {
			logfile.writeLogException(e, "inputContent", "Management");
		}
		return contents.toString();
	}//End of inputContent();
	/**
	 * get number from input keyboard 
	 * if error/mismatch input again
	 * @param message : message to show on console screen
	 * @return : number from keyboard
	 */
	public int getNumberKeyboard(String message){
	    Scanner put = new Scanner(System.in);
	    while (true) {
	      System.out.print(message);
	      try	{	return put.nextInt();	}
	      catch (java.util.InputMismatchException e) {
	        System.out.println("Input Mismatch. Please Input Number Again.");
	        logfile.writeLogException(e, "getNumberKeyboard", "Management");
	        put.nextLine();
	      }
	    }
	}
	/**
	 * get String from input keyboard
	 * @param message : message to show on console screen
	 * @return : string from keyboard
	 */
	public static String getStringKeyboard(String message){
 		Scanner put = new Scanner(System.in);
	    String str = "";
	    while (str.equals("")) {      
	    	System.out.print(message);
	    	str = put.nextLine();	         
	    }
	    return str;
	}
	/**
	 * Function add new article object with no parameter and no return type
	 * inputing multiple object using do while loop
	 */
	public void add(){
		try {
			Scanner input = new Scanner(System.in);
			String author;
			String title;
			String content;
			do{
				author = getStringKeyboard("Please Enter Author : ");
				title = getStringKeyboard("Please Enter Title : ");
				System.out.println("Type 3 periods (...) to stop");
				System.out.print("Please Enter Content: ");
				content = inputContent();
				//insertArticle(author, title, content);
				Article newArticle = new Article(author, title, content, currentDate());
				articles.add(newArticle);
				logfile.writeLogAdd(newArticle);
				
				System.out.print("Do you want to continues?(Y/N)");
				String confirm = input.next();
				switch(confirm.toLowerCase()){
				case "y":
					break;
				default:
					return;
				}//End switch;
			}while(true);
		} catch (Exception e) {
			logfile.writeLogException(e, "add", "Management");
		}		
	}//End of add();
	/**
	 * Function update one article object with no parameter and no return type  
	 * Case 1: Updating Author 
	 * Case 2: Updating Title 
	 * Case 3: Updating Content 
	 * Case 4: Updating All Fields
	 */
	public void update() {
		int index = -1;
		try{
			String author="";
			String title = "";
			String content="";
			String id = Integer.toString(getNumberKeyboard("Input ID to Update:"));			
			String option = "";
			sort("i", true);
			index = search("i", id).get(0);
			if(index < 0){
				System.out.println("ID not found!");
				waiting();
				return;
			}
			option = getStringKeyboard("Update : Au) Author | T) Title) | C) Content | Al) All: ");
			switch (option.toLowerCase()) {
				case "au":/* Updating Author by ID */
					author = getStringKeyboard("Please Enter Author : ");
					articles.get(index).setAuthor(author);
					break;
				case "t":/* Updating Title by ID */
					title = getStringKeyboard("Please Enter Title : ");
					articles.get(index).setTitle(title);
					break;					
				case "c":/* Updating Content by ID */
					System.out.print("Enter Content :");
					content = inputContent();
					articles.get(index).setContent(content);
					break;
				case "al": /* Updating All Fields by ID */
					author = getStringKeyboard("Please Enter Author : ");
					title = getStringKeyboard("Please Enter Title : ");
					System.out.print("Please Enter Content: ");
					content = inputContent();					
					articles.get(index).setData(author, title, content);
					break;
				default:/* Not Permit invalid Key */
					System.err.println("Invalid");
					waiting();
					return;					
			}
			System.out.println("Update Completed!");
			waiting();
		} catch (Exception e) {
			logfile.writeLogException(e, "update", "Management");
		}
		logfile.writeLogEdit(articles.get(index));
	}
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
		try {
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
		} catch (Exception e) {
			logfile.writeLogException(e, "search", "Management");
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
		try {
			sort("i", true);
			int index = search("i", key).get(0);
			if (index < 0) { // If value < 0 it means search not found;
				System.out.println("Invalid Key");
				waiting();
				return;
			}
			logfile.writeLogDelete(articles.get(index));
			articles.remove(index); 
			System.out.println("Remove Completed!");
		} catch (Exception e) {
			logfile.writeLogException(e, "remove", "Management");
		}		
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
		try {
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
		} catch (Exception e) {
			logfile.writeLogException(e, "sort", "Management");
		}
		//display();
	}//End of sort();
	/**
	 * Function display without parameter and no return type
	 * for manipulating articles and display all of elements
	 */
	public void display(){		
		try{
			Scanner input = new Scanner(System.in);
			String option;
			String key;
			String sortBy = "i";
			boolean isAscending = false;
			sort(sortBy, isAscending);
			display.process();	
			do{
				System.out.print("Please, Input Your Option-->");
				option = input.next();
				switch(option.toLowerCase()){
				case "a":
					add();
					break;
				case "r":					
					key = Integer.toString(getNumberKeyboard("Input ID to remove: "));
					remove(key);
					break;
				case "u":
					update();
					break;
				case "s": //Search
					System.out.print("I) ID | Au)Author | T)Title | P)Publish Date--> ");
					String searchBy = input.next();
					key = Integer.toString(getNumberKeyboard("Input ID to search: "));
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
//					if(searchList == null){
//						System.err.println("Key not found!");
//						waiting();
//						tempArticles = articles;
//						break;
//					}else{
//						for(Integer index : searchList){
//							tempArticles.add(articles.get(index));
//						}
//					}
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
					int pageNumber = getNumberKeyboard("Input Page Number: ");
					display.gotoPage(pageNumber);
					break;
				case "#":
					int pageSize = getNumberKeyboard("Input Page Size: ");
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
					String id = Integer.toString(getNumberKeyboard("Input ID to view: "));
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
					String choice = input.next();
					if(choice.equalsIgnoreCase("y")){
						System.out.print("Please input file name : ");
						String filename=input.next();
						File fileArgs = new File(filename);
						if(fileArgs.exists()){
							articles=FileArticle.getInstance().readFile(fileArgs);
							display.setArticles(articles);
						}
					}else{
						articles=FileArticle.getInstance().readFile();
					}		
					break;
				case "b":// Back Up File
					String now = new SimpleDateFormat("ddMMYYYYHHmmss").format(new Date());			
					FileArticle.getInstance().copyFile(FileArticle.getInstance().sourceFile(), 
														new File("backup" + now + ".bin"));				
					break;
				/*End Menu File*/
				case "e" :
					System.out.print("Are you sure (Y/N)? : ");
					choice = input.next();
					if(choice.equalsIgnoreCase("y")){
						FileArticle.getInstance().writeFile(articles);
						return;
					}					
				default:
					break;
				}//End of switch;
				sort(sortBy, isAscending);
				display.process();
			}while(true);
		} catch (Exception e) {
			logfile.writeLogException(e, "display", "Management");
		}
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
	//##################deprecated function######################
	//there's no need to validate when we use input.next() instead of input.nextLine()
//	/**
//	 * Function insertArticle with 3 parameter with no return type
//	 * it's used for inserting data to collection and validating Data
//	 * @param articles
//	 *            : ArrayList<Article> articles
//	 * @param author
//	 *            : Author Name
//	 * @param title
//	 *            : Title
//	 * @param content
//	 *            : content
//	 */
//	private void insertArticle(String author, String title, String content) {
//		try {
//			if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
//				System.out.println("Invalid value");
//				waiting();
//			} else {
//				Article newArticle = new Article(author, title, content, new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date()));
//				logfile.writeLogAdd(newArticle);
//				articles.add(newArticle);
//			}
//		} catch (Exception e) {
//			String methodName1 = Thread.currentThread().getStackTrace()[1].getMethodName();
//			logfile.writeLogException(e, methodName1,this.getClass().getSimpleName());
//		}
//	}
}// End of class;
