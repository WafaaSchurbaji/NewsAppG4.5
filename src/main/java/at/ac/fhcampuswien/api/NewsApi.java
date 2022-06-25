package at.ac.fhcampuswien.api;


import at.ac.fhcampuswien.exception.NewsApiException;
import at.ac.fhcampuswien.properties.Endpoint;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class NewsApi {

    private static final NameValuePair API_KEY = new NameValuePair("apiKey", "ca6e67324c204d80bb4146f96880a632");
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final Gson gson = new Gson();

    public static NewsResponse getNews(UrlProperties urlProperties) throws NewsApiException {
        OkHttpClient client = new OkHttpClient();
        Request request = new ApiRequestBuilder().apiKey(API_KEY).baseUrl(BASE_URL).urlProperties(urlProperties).build();
        NewsResponse newsResponse = null;
        try (Response response = client.newCall(request).execute()) {
            String json = Objects.requireNonNull(response.body()).string();
            newsResponse = gson.fromJson(json, NewsResponse.class);
        } catch (IOException e) {
            throw new NewsApiException("Could not parse the response!\n" + e.getMessage(), e);
        }
        return newsResponse;
    }

}