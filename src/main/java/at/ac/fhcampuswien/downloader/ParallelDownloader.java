package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.exception.NewsApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader{

    private final ExecutorService executorService;

    public ParallelDownloader() {
        this.executorService = Executors.newFixedThreadPool(10);
    }

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) {
        try {
            AtomicInteger count = new AtomicInteger(0);
            List<Callable<Void>> callables = new ArrayList<>();
            for (String url : urls) {
                Callable<Void> downloadUrl = () -> {
                    try {
                        String fileName = saveUrl2File(url);
                        if(fileName != null)
                            count.incrementAndGet();
                    } catch (NewsApiException e){
                        System.err.println(e.getMessage());
                        throw new NewsApiException(e.getMessage());
                    } catch (Exception e){
                        throw new NewsApiException("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
                    }
                    return null;
                };

                callables.add(downloadUrl);
            }
            // wait for all callables to finish -> all articles to be downloaded, before returning count
            executorService.invokeAll(callables);
            return count.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
