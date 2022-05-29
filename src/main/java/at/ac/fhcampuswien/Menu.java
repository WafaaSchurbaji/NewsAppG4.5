package at.ac.fhcampuswien;

import at.ac.fhcampuswien.api.NewsResponse;
import at.ac.fhcampuswien.controller.AppController;
import at.ac.fhcampuswien.entity.Article;
import at.ac.fhcampuswien.exception.NewsApiException;
import at.ac.fhcampuswien.properties.Category;
import at.ac.fhcampuswien.properties.Country;
import at.ac.fhcampuswien.properties.Language;
import at.ac.fhcampuswien.properties.SortBy;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Menu {
    private final AppController controller = new AppController();

    private static final String INVALID_USER_INPUT_MESSAGE = "\nInvalid Input\n";
    private static final String EXIT_MESSAGE = "Bye Bye";
    private Scanner scanner = new Scanner(System.in);

    //getTopHeadlinesAustria
    //getAllNewsBitcoin
    //getNews
    public void start() {
        printMenu();
        String input = scanner.next().trim().toLowerCase();
        handleInput(input);
    }

    private void handleInput(String input) {
        if ("a".equals(input)) {
            getTopHeadlines();
        } else if ("b".equals(input)) {
            getAllNewsBitcoin();
        } else if ("c".equals(input)) {
            getNewsByTopic();
        } else if ("q".equals(input)) {
            printExitMessage();
        } else {
            printInvalidInputMessage();
        }
        start();
    }

    //code from Nisad
    private void getTopHeadlines() {
        try {
            printCategoryOptions();
            scanner = new Scanner(System.in);
            Category category = Category.valueOf(scanner.next().toUpperCase());
            Country country = getCountryPreference();
            NewsResponse response = controller.getTopHeadLines(category, country);
            printResponse(response);
        } catch (NewsApiException exception) {
            System.out.println("Something went wrong ... " + exception.getMessage());
        } catch (Exception exception) {
            printInvalidInputMessage();
            getTopHeadlines();
        }
    }

    //code from Nisad
    private void printCategoryOptions() {
        System.out.println("\nChoose from the following categories");
        System.out.println("******************************************");
        for (Category category : Category.values()) {
            System.out.println(category.name().toLowerCase());
        }
        System.out.println("******************************************\n");
    }

    private Country getCountryPreference() {
        System.out.println("\nChoose from the following Countries to get its headlines");
        System.out.println("*****************************************************");
        try {
            for (Country country : Country.values()) {
                System.out.println(country.getPrettyName());
            }
            scanner = new Scanner(System.in);
            String country = scanner.next().trim();
            if ("default".equalsIgnoreCase(country))
                return Country.DEFAULT;
            return Country.getCountryByPrettyName(country);
        } catch (Exception exception) {
            printInvalidInputMessage();
            getCountryPreference();
        }
        return Country.DEFAULT;
    }


    private void printResponse(NewsResponse response) throws NewsApiException{
        System.out.println("\nResponse Status: " + response.getStatus());
        System.out.println("Articles count: " + response.getTotalResults());
        System.out.println("Most Sources: " + response.getMostSource(response.getArticles()));
        System.out.println("Author with longest name:" + response.getAuthorWithLongestName(response.getArticles()));
        System.out.println("NYT: " +response.getArticleFromNewYorkTimes(response.getArticles()));
        System.out.println("Under 15 characters headlines: \n" + response.printHeadlinesUnder15(response.getArticles()).toString());

        System.out.println("***********************************************************************************************************************\n\n");
        for (Article article : response.getDescriptionByLength(response.getArticles())) {
            System.out.println(article.toString());
            System.out.println("***********************************************************************************************************************\n\n");
        }

    }


    //
    private void getAllNewsBitcoin() {
        try {
            Language language = getLanguagePreference();
            SortBy sortBy = getSortingPreference();
            NewsResponse response = controller.getAllNewsBitcoin(language, sortBy);
            printResponse(response);
        } catch (NewsApiException exception) {
            System.out.println("Something went wrong ... " + exception.getMessage());
        }
    }




    private SortBy getSortingPreference() {
        System.out.println("\nChoose from the following to sort by it:");
        System.out.println("******************************************");
        for (SortBy sortBy : SortBy.values()) {
            System.out.println(sortBy.name().toLowerCase());
        }
        System.out.println("******************************************\n");
        try {
            scanner = new Scanner(System.in);
            return SortBy.valueOf(scanner.next().trim().toUpperCase());
        } catch (Exception exception) {
            printInvalidInputMessage();
            getSortingPreference();
        }

        return SortBy.DEFAULT;
    }

    /**
     * enum elements in a stream array
     */
    //Code from Glory
    private Language getLanguagePreference() {
        String languages = Arrays.stream(Language.values())
                .map(Language::getValue)
                .filter(language -> !StringUtils.isBlank(language))
                .collect(Collectors.joining(", "));
        System.out.println("\nEnter your language preference ( " + languages + " )");
        System.out.println("Type default to use the default value");

        try {
            for (Language language : Language.values()) {
                System.out.println(language.getPrettyName());
            }
            System.out.println("*****************************************************");
            scanner = new Scanner(System.in);
            String language = scanner.next().trim().toUpperCase();
            if ("default".equalsIgnoreCase(language))
                return Language.DEFAULT;
            return Language.getLanguageByPrettyName(language);
        } catch (Exception exception) {
            printInvalidInputMessage();
            getLanguagePreference();
        }
        return Language.DEFAULT;
    }

    private void getNewsByTopic() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("\nEnter your desired topic:");
            System.out.println("******************************************");
            String topic = scanner.next().trim();
            Language language = getLanguagePreference();
            SortBy sortBy = getSortingPreference();
            NewsResponse response = controller.getNews(topic, language, sortBy);
            printResponse(response);
        } catch (NewsApiException e) {
            System.out.println("Something went wrong ... " + e.getMessage());
        }
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_USER_INPUT_MESSAGE);
    }

    private static void printMenu() {
        System.out.println("""
                **********************************
                *       Welcome to NewsApp       *
                **********************************
                Enter what you wanna do:
                a: Get top headlines by country\s
                b: Get all news about bitcoin
                c: Get news by topic\s
                q: Quit program""");
    }
}




