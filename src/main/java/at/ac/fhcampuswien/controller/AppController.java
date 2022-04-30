package at.ac.fhcampuswien.controller;

import at.ac.fhcampuswien.api.NewsApi;
import at.ac.fhcampuswien.api.NewsResponse;
import at.ac.fhcampuswien.api.UrlProperties;
import at.ac.fhcampuswien.entity.Article;
import at.ac.fhcampuswien.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AppController {
    private List<Article> articles;
    private List<Article> newsarticle;

    public AppController() {
        NewsResponse article = new NewsResponse();
        newsarticle = article.getArticles();
        articles = newsarticle;
    }

    public NewsResponse getTopHeadLines(Category category, Country country) {
        UrlProperties urlProperties = new UrlProperties(Endpoint.TOP_HEADLINES);
        urlProperties.setCountry(country);
        urlProperties.setCategory(category);
        return NewsApi.getNews(urlProperties);
    }

    public NewsResponse getAllNewsBitcoin(Language language, SortBy sortBy) {
        UrlProperties urlProperties = new UrlProperties(Endpoint.EVERYTHING);
        urlProperties.setQuery("+Bitcoin");
        urlProperties.setLanguage(language);
        urlProperties.setSortBy(sortBy);
        return NewsApi.getNews(urlProperties);
    }

    public NewsResponse getNews(String topic, Language language, SortBy sortBy) {
        UrlProperties urlProperties = new UrlProperties(Endpoint.EVERYTHING);
        urlProperties.setQuery(topic);
        urlProperties.setLanguage(language);
        urlProperties.setSortBy(sortBy);
        return NewsApi.getNews(urlProperties);
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

    protected static List<Article> filterList(String query, List<Article> articles) {
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

}
