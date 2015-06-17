package Search;

import java.util.ArrayList;

import Model.Article;

public class SearchByModifiedDate implements ISearch {

	@Override
	public ArrayList<Integer> search(ArrayList<Article> articles, String key) {
		// TODO Auto-generated method stub
		ArrayList<Integer> indices = new ArrayList<Integer>();
		/*
		 * Search Algorithm Separate ArrayList into 2 Search with 0 and the
		 * beginning of half elements (totalSize / 2) startIndex values (0 - the
		 * half of total elements) halfIndex values (the half of total elements
		 * - total elements)
		 */
		key = key.toLowerCase(); /* Convert to lower case for compare */
		for (int startIndex = 0, halfIndex = articles.size() / 2; startIndex < articles
				.size() / 2; startIndex++, halfIndex++) {
			if (articles.get(startIndex).getModifiedDate().toLowerCase()
					.startsWith(key)) {
				indices.add(startIndex);
			}
			if (articles.get(halfIndex).getModifiedDate().toLowerCase()
					.startsWith(key)) {
				indices.add(halfIndex);
			}
		}

		/* If number of records is odd check the last record */
		if ((articles.size() % 2) != 0) {
			if (articles.get(articles.size() - 1).getModifiedDate()
					.toLowerCase().startsWith(key)) {
				indices.add(articles.size() - 1);
			}
		}
		return indices; /* indices stored only values of ArrayList index */

	}

}
