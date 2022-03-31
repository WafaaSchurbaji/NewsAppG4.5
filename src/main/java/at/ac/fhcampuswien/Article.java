package at.ac.fhcampuswien;

import java.util.Objects;

public class Article {
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

    private String author;
    private String title;

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

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author ;}
}
