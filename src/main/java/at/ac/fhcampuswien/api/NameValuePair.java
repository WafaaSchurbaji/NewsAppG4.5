package at.ac.fhcampuswien.api;

import at.ac.fhcampuswien.properties.ApiProperties;

public class NameValuePair {
    private String name;
    private ApiProperties value;
    private String stringValue;

    public NameValuePair(String name, ApiProperties value) {
        this.name = name;
        this.value = value;
        this.stringValue = value.getValue();
    }

    public NameValuePair(String name, String stringValue) {
        this.name = name;
        this.stringValue = stringValue;
        this.value = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value != null ? value.getValue() : stringValue;
    }

    public void setValue(ApiProperties value) {
        this.value = value;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
