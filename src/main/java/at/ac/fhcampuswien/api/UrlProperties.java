package at.ac.fhcampuswien.api;

import at.ac.fhcampuswien.properties.*;

public class UrlProperties {

    private final NameValuePair category = new NameValuePair("category", Category.DEFAULT);
    private final NameValuePair country = new NameValuePair("country", Country.DEFAULT);
    private final NameValuePair language = new NameValuePair("language", Language.DEFAULT);
    private final NameValuePair sortBy = new NameValuePair("sortBy", SortBy.DEFAULT);
    private final NameValuePair query = new NameValuePair("q", "");
    private Endpoint endpoint;

    public UrlProperties(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public NameValuePair getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category.setValue(category);
    }

    public NameValuePair getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country.setValue(country);
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public NameValuePair getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language.setValue(language);
    }

    public NameValuePair getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy.setValue(sortBy);
    }

    public NameValuePair getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query.setStringValue(query);
    }

    @Override
    public String toString() {
        return "UrlProperties{" +
                "category=" + category.getValue() +
                ", country=" + country.getValue() +
                ", language=" + language.getValue() +
                ", sortBy=" + sortBy.getValue() +
                ", query=" + query.getValue() +
                ", endpoint=" + endpoint.getValue() +
                '}';
    }
}
