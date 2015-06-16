package Control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import Model.Article;
import Search.*;
import Sort.*;

public class Management {
	Scanner put;
	String authors;
	String titles;
	String contents;
	int i = 0;
	boolean b = true;
	ArrayList<Article> articles;
	ArrayList<Integer> indices;
	private ISort sortBy;
	private ISearch searchBy;

	public Management() {
		articles = new ArrayList<Article>();
		indices = new ArrayList<Integer>();
		/*for(int  i=0; i<10e6; i++){
			articles.add(new Article("Vichea", "JAVA ", "CBD", "DFSDFKDSFJSDKFJ"));
		}*/
		articles.add(new Article("aPisal", "A", "CBD"));
		articles.add(new Article("Vichea", "AB", "CBD"));
		articles.add(new Article("Dara", "E", "CBD"));
		articles.add(new Article("Sal", "F", "CBD"));
		articles.add(new Article("Sok", "AG", "CBD"));
		articles.add(new Article("Kit", "E", "CBD"));
		articles.add(new Article("Poly", "G", "CBD"));
		articles.add(new Article("abNita", "U", "CBD"));
		articles.add(new Article("Chetra", "I", "CBD"));
		articles.add(new Article("Sambo", "J", "CBD"));
		articles.add(new Article("aaElit", "P", "CBD"));
		articles.add(new Article("zhanna", "A", "CBD"));
		articles.add(new Article("Ally", "X", "CBD"));

	}

	public void add() {
		put = new Scanner(System.in);

		while (b) {
			contents = "";
			System.out.println("Please Enter Author : ");
			authors = put.nextLine();
			System.out.println("Please Enter Titile : ");
			titles = put.nextLine();
			System.out.println("Please Enter Content: ");
			while (put.hasNext()) {
				contents += put.nextLine();
				if (contents.endsWith("."))
					break;
			}
			this.validateData(articles, authors.trim(), titles.trim(),
					contents.trim());
			confirmKey();
		}
	}

	private void confirmKey() {
		// TODO Auto-generated method stub
		System.out.println("Do you want to continues?(Y/N)");
		String key = new Scanner(System.in).nextLine().toLowerCase();
		switch (key) {
		case "y":
			i=0;
			b = true;
			break;
		case "n":
			i=0;
			b = false;
			/* Call Main Menu Back */
			// display();
			break;
		default:
			System.out.println("invalid key");
			i++;
			if (i == 3) {
				b = false;
				/* Call Main Menu Back */
				return;
			}
			confirmKey();
			break;
		}
	}

	private void validateData(ArrayList<Article> articles, String author,
			String title, String content) {
		// TODO Auto-generated method stub
		String publishdate = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss")
				.format(new Date());
		if (author.isEmpty() || title.isEmpty() || content.isEmpty()) {
			System.out.println("No value");
		} else {
			articles.add(new Article(author, title, content));
		}
	}

	

	public void update(ISearch searchBy, String key) {
		String modifiedDate = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss")
				.format(new Date());
		put = new Scanner(System.in);
		int choose = 0;
		this.searchBy = searchBy;
		// System.out.println(articles.get(this.searchBy.search(articles,
		// key).get(0)).toString());
		System.out.println("Update : 1.(Author) 2.(Title) 3.(Content) 4.(All)");
		choose = put.nextInt();
		switch (choose) {

		case 1:
			System.out.println("Enter Author : ");
			put.nextLine();
			authors = put.nextLine();
			articles.get(this.searchBy.search(articles, key).get(0)).setAuthor(
					authors);
			articles.get(this.searchBy.search(articles, key).get(0))
					.setModifiedDate(modifiedDate);
			System.out.println("Saved");
			break;
		case 2:
			System.out.println("Enter Title : ");
			put.nextLine();
			titles = put.nextLine();
			articles.get(this.searchBy.search(articles, key).get(0)).setTitle(
					titles);
			articles.get(this.searchBy.search(articles, key).get(0))
					.setModifiedDate(modifiedDate);
			System.out.println("Saved");
			break;
		case 3:
			contents = "";
			System.out.println("Enter Content : ");
			put.nextLine();
			contents = put.nextLine();
			while (put.hasNext()) {
				contents += put.nextLine();
				if (contents.endsWith("."))
					break;
			}
			articles.get(this.searchBy.search(articles, key).get(0))
					.setContent(contents);
			articles.get(this.searchBy.search(articles, key).get(0))
					.setModifiedDate(modifiedDate);
			System.out.println("Saved");
			break;
		case 4: // Upated All Elements
			b = true;
			contents = "";
			while (b) {
				System.out.println("Please Enter Author : ");
				put.nextLine();
				authors = put.nextLine();
				System.out.println("Please Enter Titile : ");
				titles = put.nextLine();
				System.out.println("Please Enter Content: ");
				while (put.hasNext()) {
					contents += put.nextLine();
					if (contents.endsWith("."))
						break;
				}
				if (authors.isEmpty() || titles.isEmpty() || contents.isEmpty()) {
					System.out.println("No value");
				} else {

					articles.get(this.searchBy.search(articles, key).get(0))
							.setAuthor(authors);
					articles.get(this.searchBy.search(articles, key).get(0))
							.setTitle(titles);
					articles.get(this.searchBy.search(articles, key).get(0))
							.setContent(contents);
					articles.get(this.searchBy.search(articles, key).get(0))
							.setModifiedDate(modifiedDate);
				}
				confirmKey();
			}
			break;
		default:
			System.err.println("Invalid");
			// update(searchBy, key);
			break;
		}
	}

	public void search(ISearch searchBy, String key) {
		this.searchBy = searchBy;
		//long start = System.currentTimeMillis();
		indices = this.searchBy.search(articles, key);
		//long end = System.currentTimeMillis();
		//System.err.println(end - start);
		/*Iterator<Integer> it = indices.iterator();
		while(it.hasNext()){
			System.out.println(articles.get(it.next()).toString());
		}/*Show all of elements found*/
		
		//System.out.println(articles.get((int)indices.get(0)));/*Search by ID, Show only 1 elements*/
	}
	public void remove(String key) {
		search(new SearchById(), key);
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
	public void sort(ISort sortBy, boolean isAscending) {
		this.sortBy = sortBy;
		this.sortBy.sort(articles, isAscending);
		display();
	}

	public void display() {
		/* System.out.println(articles.size()); */
		//long start = System.currentTimeMillis();
		Iterator<Article>  it = articles.iterator();
		while(it.hasNext()){System.out.println(it.next());}
		for(int index : indices){
			System.out.println(articles.get(index));
		}
		//System.err.println(indices.size());
		//long stop = System.currentTimeMillis();
	}

	public static void main(String[] args) {
		Management management = new Management();
		System.out.println("-------------------");
		//management.search(new SearchByAuthor(), "S");
		//management.display();
		System.out.println("----------");
		//management.update(new SearchById(), "1");
		management.display();
		System.out.println("----------");
		//management.search(new SearchById(), "11");
		//management.display();
		//management.remove("11");
		//management.search(new SearchById(), "11");
		//management.sort(new SortByAuthor(), false);
		System.out.println("----------");
		management.update(new SearchById(), "10");
		management.display();
	}
}// End of class;
