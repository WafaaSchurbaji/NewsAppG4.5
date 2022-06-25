package at.ac.fhcampuswien.api;

import at.ac.fhcampuswien.properties.Endpoint;
import okhttp3.Request;

public class ApiRequestBuilder {
    private UrlProperties urlProperties;
    private String baseUrl;
    private NameValuePair apiKey;

    public ApiRequestBuilder urlProperties(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
        return this;
    }

    public ApiRequestBuilder apiKey(NameValuePair apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ApiRequestBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public Request build() {
        return new Request.Builder().url(getUrl(urlProperties)).build();
    }

    private String getUrl(UrlProperties urlProperties) {
        if (urlProperties == null) {
            System.out.println("URL Properties object is null .. default values will be used");
            urlProperties = new UrlProperties(Endpoint.EVERYTHING);
            System.out.println(urlProperties);
        }
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append(urlProperties.getEndpoint().getValue())
                .append("?")
                .append(urlProperties.getQuery().getName())
                .append("=")
                .append(urlProperties.getQuery().getValue());
        switch (urlProperties.getEndpoint()) {
            case EVERYTHING -> {
                stringBuilder
                        .append("&")
                        .append(urlProperties.getLanguage().getName())
                        .append("=")
                        .append(urlProperties.getLanguage().getValue());
                stringBuilder
                        .append("&")
                        .append(urlProperties.getSortBy().getName())
                        .append("=")
                        .append(urlProperties.getSortBy().getValue());
            }
            case TOP_HEADLINES -> {
                stringBuilder
                        .append("&")
                        .append(urlProperties.getCountry().getName())
                        .append("=")
                        .append(urlProperties.getCountry().getValue());
                stringBuilder
                        .append("&")
                        .append(urlProperties.getCategory().getName())
                        .append("=")
                        .append(urlProperties.getCategory().getValue());
            }
        }
        stringBuilder
                .append("&")
                .append(apiKey.getName())
                .append("=")
                .append(apiKey.getValue());
        return stringBuilder.toString();
    }
}
