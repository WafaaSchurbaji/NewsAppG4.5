package at.ac.fhcampuswien.entity;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Article {

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    // Constructor
    public Article(String author, String title){
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!StringUtils.isBlank(getAuthor())) {
            stringBuilder.append("Author: ").append(getAuthor()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getPublishedAt())) {
            stringBuilder.
                    append("Published at: ").append(getPublishedAt()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getTitle())) {
            stringBuilder.append("Title: ").append(getTitle()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getDescription())) {
            stringBuilder.append("Description: ").append(getDescription()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getContent())) {
            stringBuilder.append("Content: ").append(getContent()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getUrl())) {
            stringBuilder.append("URL: ").append(getUrl()).append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(getAuthor(), article.getAuthor()) && Objects.equals(getTitle(), article.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getTitle());
    }

}
