package at.ac.fhcampuswien.api;

import at.ac.fhcampuswien.entity.Article;
import at.ac.fhcampuswien.exception.NewsApiException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    /*public String getAuthorWithLongestName(Collection<Article> articles) throws NewsApiException {
        Optional<String> longName = Optional.ofNullable(articles.stream()
                .max(Comparator.comparing(article -> article.getAuthor().length()))
                .get().getAuthor());
        if (longName.isPresent())
            return longName.get();
        else {
            throw new NewsApiException("There is no author with longest name");
        }
    }*/
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



}
