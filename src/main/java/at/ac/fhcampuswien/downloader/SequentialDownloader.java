package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.exception.NewsApiException;
import java.util.List;

//Class is needed for exercise 4 - ignore for exercise 3 solution
public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) throws NewsApiException {
        int count = 0;
        for (String url : urls) {
            try {
                String fileName = saveUrl2File(url);
                if (fileName != null) {
                    count++;
                }
            } catch (Exception e) {
                throw new NewsApiException("Different problem occured in " + this.getClass().getName() + ". Message " + e.getMessage());
            }

        }
        return count;
    }
}