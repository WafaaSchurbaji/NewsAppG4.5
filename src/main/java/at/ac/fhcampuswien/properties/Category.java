package at.ac.fhcampuswien.properties;

public enum Category implements ApiProperties {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
    DEFAULT("");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
