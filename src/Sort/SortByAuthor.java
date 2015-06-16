package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Model.Article;

public class SortByAuthor implements ISort {

	@Override
	public void sort(ArrayList<Article> articles, boolean isAscending) {
		// TODO Auto-generated method stub
		Collections.sort(articles, new Comparator<Article>() {

			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub

				return art1.getAuthor().compareTo(art2.getAuthor()); 
				/* Sort Object By Ascending */
			}
		});
		if (!isAscending) { 
			Collections.reverse(articles); /* Sort Object By Descending */
		}
	}

}
