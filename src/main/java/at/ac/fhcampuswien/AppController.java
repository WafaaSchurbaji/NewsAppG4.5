package at.ac.fhcampuswien;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AppController {
    private List<Article> articles;
    public AppController(){
    }

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }

    public List<Article> getArticles(){
        return this.articles;
    }
    public int getArticleCount(){
        return articles != null ? articles.size() : 0;
    }
    public List<Article> getTopHeadlinesAustria(){
        return articles != null ? articles : new ArrayList<>();
    }
    public List<Article> getAllNewsBitcoin(){
        return filterList("bitcoin", this.articles);
    }
    protected static List<Article> filterList(String query,List<Article> articles){
        if (articles == null)
            return null;
        if (query == null || query.isEmpty())
            return articles;
        List<Article> result = new ArrayList<>();
        for (Article article : articles){
            if (Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(article.getTitle()).find())
                result.add(article);
        }
        return result;
    }
    public static List<Article> generateMockList(){
        List<Article> mock = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++){
            mock.add(new Article(faker.name().fullName(), faker.book().title()));
        }
        mock.add(new Article("Wafaa", "Bitcoin Future"));
        mock.add(new Article("Max Mustermann", "The era of BitCoin"));
        mock.add(new Article("Tester", "How to Bitcoin"));
        return mock;
    }
}
