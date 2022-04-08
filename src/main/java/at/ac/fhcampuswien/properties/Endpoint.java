package at.ac.fhcampuswien.properties;

public enum Endpoint implements ApiProperties {
    EVERYTHING("everything"),
    TOP_HEADLINES("top-headlines");

    private final String value;

    Endpoint(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
