package at.ac.fhcampuswien.properties;

public enum SortBy implements ApiProperties {
    RELEVANCY("relevancy"),// articles more closely related to q come first
    POPULARITY("popularity"),//articles from popular sources and publishers come first.
    PUBLISHED_AT("publishedAt"),//newest articles come first.
    DEFAULT("");//publishedAt

    private final String value;

    SortBy(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
