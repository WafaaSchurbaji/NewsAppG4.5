package at.ac.fhcampuswien.api;


import at.ac.fhcampuswien.entity.Article;
import at.ac.fhcampuswien.properties.Endpoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsApi {

    private static final NameValuePair API_KEY = new NameValuePair("apiKey", "ca6e67324c204d80bb4146f96880a632");
    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static NewsResponse getNews(UrlProperties urlProperties) {
        String url = getUrl(urlProperties);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        NewsResponse newsResponse = new NewsResponse();
        try (Response response = client.newCall(request).execute()) {
            String json = Objects.requireNonNull(response.body()).string();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Article>>() {
            }.getType();
            List<Article> articles = gson.fromJson(json, listType);
            newsResponse.setArticles(articles);
            newsResponse.setStatus(response.code());
            newsResponse.setTotalResults(articles.size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return newsResponse;
    }

    private static String getUrl(UrlProperties urlProperties) {
        if (urlProperties == null) {
            System.out.println("URL Properties object is null .. default values will be used");
            urlProperties = new UrlProperties(Endpoint.EVERYTHING);
            System.out.println(urlProperties);
        }
        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
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
                .append(API_KEY.getName())
                .append("=")
                .append(API_KEY.getValue());
        return stringBuilder.toString();
    }
}