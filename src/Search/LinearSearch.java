package Search;

import java.util.ArrayList;

import Model.Article;

public class LinearSearch implements ISearch {

	@Override
	public ArrayList<Integer> search(ArrayList<Article> articles, String key) {
		// TODO Auto-generated method stub
		ArrayList<Integer> indices = new ArrayList<Integer>();
		key = key.toLowerCase(); /*Convert to lower case for compare*/
		for (int startIndex = 0; startIndex < articles.size(); startIndex++) {
			if (articles.get(startIndex).getAuthor().toLowerCase().startsWith(key)) {
				indices.add(startIndex);
			}
		}

		return indices;
	}

}
