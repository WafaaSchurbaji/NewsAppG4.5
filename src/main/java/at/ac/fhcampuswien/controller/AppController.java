package at.ac.fhcampuswien.controller;

import at.ac.fhcampuswien.api.NewsApi;
import at.ac.fhcampuswien.api.NewsResponse;
import at.ac.fhcampuswien.api.UrlProperties;
import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.entity.Article;
import at.ac.fhcampuswien.exception.NewsApiException;
import at.ac.fhcampuswien.properties.*;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class AppController {
    private static AppController INSTANCE;
    private List<Article> articles;


    // Konstruktor privat erstellen - damit andere Klassen kein Objekt instanziieren können
    private AppController() {
        NewsResponse article = new NewsResponse();
        articles = article.getArticles();
    }

    /**
     * Singleton Pattern
     */

    public static AppController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppController();
        }
        return INSTANCE;
    }

    public int downloadURLs(Downloader downloader) throws NewsApiException{
        if(articles==null){
            throw new NewsApiException("Article list is empty");
        }
        List<String> urls = articles.stream().map(Article::getUrl).toList();
        return downloader.process(urls);
    }

    public NewsResponse getTopHeadLines(Category category, Country country) throws NewsApiException {
        UrlProperties urlProperties = new UrlProperties(Endpoint.TOP_HEADLINES);
        urlProperties.setCountry(country);
        urlProperties.setCategory(category);
        NewsResponse newsResponse = NewsApi.getNews(urlProperties);
        setArticles(newsResponse.getArticles());
        return newsResponse;
    }

    public NewsResponse getAllNewsBitcoin(Language language, SortBy sortBy) throws NewsApiException {
        UrlProperties urlProperties = new UrlProperties(Endpoint.EVERYTHING);
        urlProperties.setQuery("+Bitcoin");
        urlProperties.setLanguage(language);
        urlProperties.setSortBy(sortBy);
        NewsResponse newsResponse = NewsApi.getNews(urlProperties);
        setArticles(newsResponse.getArticles());

        return newsResponse;
    }

    public NewsResponse getNews(String topic, Language language, SortBy sortBy) throws NewsApiException {
        UrlProperties urlProperties = new UrlProperties(Endpoint.EVERYTHING);
        urlProperties.setQuery(topic);
        urlProperties.setLanguage(language);
        urlProperties.setSortBy(sortBy);
        NewsResponse newsResponse = NewsApi.getNews(urlProperties);
        setArticles(newsResponse.getArticles());
        return newsResponse;
    }

    public String getMostSource(Collection<Article> articles) throws NewsApiException {
        Optional<Map.Entry<String, Long>> max = articles.stream()
                .map(article -> article.getSource().getName())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        if (max.isPresent())
            return max.get().getKey();
        else
            throw new NewsApiException("No most common source could be determined!");
    }

    public String getAuthorWithLongestName(Collection<Article> articles)throws NewsApiException{
     Optional<Article> authorName =articles.stream()
             .max(Comparator.comparingInt(Article::getAuthorLength));
     if (authorName.isPresent())
     return authorName.get().getAuthor();
     else

             throw new NewsApiException("No author with longest name");
    }

    public long getArticleFromNewYorkTimes(Collection<Article> articles){
          String a = "New York Times";
        return articles.stream()
        .map(article -> article.getSource().getName().equalsIgnoreCase(a)).count();
    }

    public List<Article> getDescriptionByLength(Collection<Article> articles) throws NewsApiException {
        Comparator<Article > sortDescriptionByLengthThenAlphabet = Comparator.comparingInt(Article::getDescriptionLength).thenComparing(Article::getDescription);
        List<Article> sortedByDec = articles.stream()
                .sorted(sortDescriptionByLengthThenAlphabet)
                .collect(Collectors.toList());
    if (sortedByDec.isEmpty()) {
            throw new NewsApiException("No Article contain description");
        }
        else {
            return sortedByDec;
        }
    }
/*
    public List<Article> getArticleWithShortTitle(Collection<Article> articles) throws NewsApiException {
        List<Article> shortTitle = articles.stream()
                .filter(article -> article.getTitle().length() < 15).collect(Collectors.toList());
        if (shortTitle.isEmpty()) {
            throw new NewsApiException("No article with title length less than 15 character");
        }
        else {
            return shortTitle;
        }
    }
*/

    public String getArticleWithShortTitle(Collection<Article> articles)throws NewsApiException{
        Optional<Article> title =articles.stream()
                .min(Comparator.comparingInt(Article::getTitleLength));
        if (title.isPresent())
            return title.get().getTitle();
        else

            throw new NewsApiException("No author with shortest Title");
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public int getArticleCount() {
        return articles != null ? articles.size() : 0;
    }

    @Deprecated
    public static List<Article> filterList(String query, List<Article> articles) {
        if (articles == null)
            return null;
        if (query == null || query.isEmpty())
            return articles;
        List<Article> result = new ArrayList<>();
        for (Article article : articles) {
            if (Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(article.getTitle()).find())
                result.add(article);
        }
        return result;
    }
    @Deprecated
    public static List<Article> generateMockList() {
        List<Article> mock = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            mock.add(new Article(faker.name().fullName(), faker.book().title()));
        }
        mock.add(Article.builder().author("Wafaa").title("Bitcoin Future").build());
        mock.add(Article.builder().author("Max Mustermann").title("The era of BitCoin").build());
        mock.add(Article.builder().author("Tester").title("How to Bitcoin").build());
        return mock;
    }


}
