package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Model.Article;

public class ProcessControl {
	//ArrayList<Article> articles;
	public void Sort(ArrayList<Article> articles,int sortBy, boolean isAscending) {
		switch (sortBy) {
		// sortby id
		case 1:
			Collections.sort(articles, new Comparator<Article>() {

				@Override
				public int compare(Article art1, Article art2) {
					// TODO Auto-generated method stub

					return art1.getId() > art2.getId() ? 1 : -1;
					/* Sort Object By Ascending */

				}

			});
			if (!isAscending) {
				Collections.reverse(articles); /* Sort Object By Descending */
			}
			break;
//			sort by author
		case 2:
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
			break;
//			sort by title
		case 3:
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

			break;
			//sort by Publish Date
		case 4:
			Collections.sort(articles, new Comparator<Article>() {

				@Override
				public int compare(Article art1, Article art2) {
					// TODO Auto-generated method stub

					return art1.getPublishDate().compareTo(art2.getPublishDate()); 
					/* Sort Object By Ascending */
				}
			});
			if (!isAscending) { 
				Collections.reverse(articles); /* Sort Object By Descending */
			}

		default:
			break;
		}
	}

}
