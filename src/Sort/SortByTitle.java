package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Model.Article;

public class SortByTitle implements ISort {

	@Override
	public void sort(ArrayList<Article> articles, boolean isAscending) {
		// TODO Auto-generated method stub
		Collections.sort(articles, new Comparator<Article>() {

			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub

				return art1.getTitle().compareTo(art2.getTitle()); 
				/* Sort Object By Ascending */
			}
		});
		if (!isAscending) { 
			Collections.reverse(articles); /* Sort Object By Descending */
		}
	}

}
