package Search;
import java.util.ArrayList;

import Model.Article;
public interface ISearch {
	ArrayList<Integer> search(ArrayList<Article> articles, String key);
}
