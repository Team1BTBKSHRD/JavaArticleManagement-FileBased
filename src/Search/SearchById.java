package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Model.Article;

public class SearchById implements ISearch {

	@Override
	public ArrayList<Integer> search(ArrayList<Article> articles, String key) {
		// TODO Auto-generated method stub
		ArrayList<Integer> indices  = new ArrayList<Integer>();
		// Index received value from Collections.binarySearch() as an index of Articles  
		int index = Collections.binarySearch(articles, new Article("", "", "").clone(Integer.parseInt(key))/*Key for search*/, new Comparator<Article>() {
			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getId() < art2.getId() ? -1 : art1.getId() > art2.getId() ? 1 : 0; 
			}
		});
		indices.add(index); // Add only one index (one element only)
		return indices;
	}

}
