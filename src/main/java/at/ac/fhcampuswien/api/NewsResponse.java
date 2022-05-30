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


    public long getArticleFromNewYorkTimes(Collection<Article> articles)  {
        String a = "New York Times";
        return articles.stream()
                .map(article -> article.getSource().getName().equalsIgnoreCase(a)).count();
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

public List<Article> getDescriptionByLength(Collection<Article> articles)  {
        Comparator<Article > sortDescriptionByLengthThenAlphabet = Comparator.comparingInt(Article::getDescriptionLength).thenComparing(Article::getDescription);
    return articles.stream()
            .sorted(sortDescriptionByLengthThenAlphabet)
            .collect(Collectors.toList());
    }


    public String getArticleWithShortTitle(Collection<Article> articles) throws NewsApiException {
        Optional<Article> titleName =articles.stream()
                .min(Comparator.comparingInt(Article::getTitleLength));
        if (titleName.isPresent())
            return titleName.get().getTitle();
        else

            throw new NewsApiException("No author with shortest Title");

    }

}




