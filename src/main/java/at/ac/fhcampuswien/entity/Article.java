package at.ac.fhcampuswien.entity;


import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Article {

    private final String author;
    private final String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private Source source;

    // Constructor
    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Article(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }

    //Second Constructor
    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt, String content, Source source) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }
    public int getAuthorLength(){
        if(author!=null)
          return author.length();

        return 0;
    }
    public String getTitle() {
        return title;
    }
    public int getTitleLength(){
        if(title!=null)
            return title.length();
        return 0;
    }

    public String getDescription() {
        return description;
    }

   public int getDescriptionLength(){
      if(description != null)
            return description.length();

        return 0;
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

    public void setSource(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return this.source;
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (source != null && !StringUtils.isBlank(source.getName())) {
            stringBuilder.append("Source: ").append(getSource().getName()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getAuthor())) {
            stringBuilder.append("Author: ").append(getAuthor()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getPublishedAt())) {
            stringBuilder.
                    append("Published at: ").append(getPublishedAt()).append(System.getProperty("line.separator"));
        } if (!StringUtils.isBlank(getTitle())) {
            stringBuilder.append("Title: ").append(getTitle()).append(System.getProperty("line.separator"));
        }
        if (!StringUtils.isBlank(getDescription())) {
            stringBuilder.append("Description (Length): ").append(getDescriptionLength()).append(System.getProperty("line.separator"));
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
        if (!StringUtils.isBlank(getUrlToImage())) {
            stringBuilder.append("Image: ").append(getUrlToImage()).append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return Objects.equals(getAuthor(), article.getAuthor()) && Objects.equals(getTitle(), article.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getTitle());
    }




    public static ArticleBuilder builder() {
        return new ArticleBuilder();
    }


    public static class ArticleBuilder {
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
        private Source source;

        public ArticleBuilder() {

        }

        public ArticleBuilder author(String author) {
            this.author = author;
            return this;
        }

        public ArticleBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ArticleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ArticleBuilder url(String url) {
            this.url = url;
            return this;
        }

        public ArticleBuilder urlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
            return this;
        }

        public ArticleBuilder publishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        public ArticleBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ArticleBuilder source(Source source) {
            this.source = source;
            return this;
        }

        public Article build() {
            return new Article(author, title, description, url, urlToImage, publishedAt, content, source);
        }


}


}
