package at.ac.fhcampuswien;

import java.util.List;

public class AppController {
    private List<Article> articles;
    public AppController(){

    }
    public void setArticles(List<Article> articles){

    }
    public int getArticleCount(){
        return 0 ;
    }
    public List<Article> getTopHeadlinesAustria(){
        return articles;
    }
    public List<Article> getAllNewsBitcoin(){
        return articles;
    }
    protected static List<Article> filterList(String query,List<Article> articles){
        return articles;
    }
    private List<Article> generateMockList(){
        return articles;
    }
}
