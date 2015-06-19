package Control;
import Model.Article;
import java.util.List;

public class Demo {
	public static void main(String[] args) {
		Management management = new Management();
		display1(management.articles);
		display1(management.tempArticles);
		management.sort("2",false);
		display1(management.articles);
		display1(management.tempArticles);
		System.out.print(management.search(0,"1"));
		display1(management.articles);
		display1(management.tempArticles);
		
		
	}
	public static void display1(List<Article> a){
		for(Article t: a){
			System.out.println(t);
		}
		System.out.println();
	}
}
