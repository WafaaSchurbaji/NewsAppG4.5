package at.ac.fhcampuswien;

import at.ac.fhcampuswien.controller.AppController;
import at.ac.fhcampuswien.entity.Article;
import at.ac.fhcampuswien.entity.Source;
import at.ac.fhcampuswien.exception.NewsApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class AppControllerTest {
    private static AppController appController;

    @BeforeEach
    void setup() {
        appController = AppController.getInstance();

    }

    /**
     * Tests for Set Method
     */

    @Test
    @DisplayName("List can be set")
    public void listShouldBeSet() {
        List<Article> list = new ArrayList<>();
        Article article = new Article("New List", "New List");
        list.add(article);
        appController.setArticles(list);//list wird gesetzt durch die appcontroller
        List<Article> actual = appController.getArticles();//um zu wissen welche list ist jetzt in appcontroller
        assertEquals(article, actual.get(0), "Lists sizes should be the same");//vergleicht ob  die artikeln die in der list sind die selbe die ich hier hab

    }

    /**
     * Tests for getArticleCount Method
     */

    @Test
    @DisplayName("List count when not empty")
    public void listCountWhenNotEmpty() {
        List<Article> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Article("New List", "New List"));
        }
        appController.setArticles(list);
        assertEquals(5, appController.getArticleCount());
    }

    @Test
    @DisplayName("List count when empty")
    public void listCountWhenEmpty() {
        List<Article> list = new ArrayList<>();
        appController.setArticles(list);
        assertEquals(0, appController.getArticleCount());
    }

    @Test
    @DisplayName("List count when null")
    public void listCountWhenNull() {
        appController.setArticles(null);
        assertEquals(0, appController.getArticleCount());
        //or.....
        // assertNull(null,"listCount should be null");
    }

    /**
     * Tests for FilterList Method
     */

    @Test
    @DisplayName("List is filtered")
    public void listShouldBeFiltered() {
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("mohamad", "the Sea"));
        list.add(new Article("Diana", "Big Tree"));
        list.add(new Article("Max", "The Laptop"));
        list.add(new Article("Max", "Old laptop"));
        list.add(new Article("Alice", "Car in the City"));
        //Act
        List<Article> actual = AppController.filterList("laptop", list);
        //Assert
        assertEquals(2, actual.size());
        assertNotNull(actual, "the List should not be null");
        //hier wird geschaut nach dem ich meine liste gefiltert habe ob meine zwei artikeln die ich hier hab auch da sind
        if (!actual.get(0).getTitle().equalsIgnoreCase("The Laptop") && !actual.get(0).getTitle().equalsIgnoreCase("Old laptop"))
            fail("Filtered articles are not as expected!");
        if (!actual.get(1).getTitle().equalsIgnoreCase("The Laptop") && !actual.get(1).getTitle().equalsIgnoreCase("Old laptop"))
            fail("Filtered articles are not as expected!");
    }

    @Test
    @DisplayName("Query is empty")
    public void listShouldBeFilteredQueryEmpty() {
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("mohamad", "the Sea"));
        list.add(new Article("Diana", "Big Tree"));
        list.add(new Article("Max", "The Laptop"));
        list.add(new Article("Max", "Old laptop"));
        list.add(new Article("Alice", "Car in the City"));
        //Act
        List<Article> actual = AppController.filterList("", list);
        //Assert
        assertEquals(list, actual);

    }

    @Test
    @DisplayName("List is null")
    public void listShouldBeFilteredListNull() {
        assertNull(null, "The list should be Null ");
    }

    @Test
    @DisplayName("List is empty")
    public void listShouldBeFilteredListEmpty() {
        assertEquals(new ArrayList<>(), AppController.filterList("query", new ArrayList<>()));

    }

    @Test
    @DisplayName("Query is not found")
    public void listShouldBeFilteredQueryNotFound() {
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("mohamad", "the Sea"));
        list.add(new Article("Diana", "Big Tree"));
        list.add(new Article("Max", "The Laptop"));
        list.add(new Article("Max", "Old laptop"));
        list.add(new Article("Alice", "Car in the City"));
        //Act
        List<Article> actual = AppController.filterList("test", list);
        //Assert
        assertEquals(0, actual.size());
    }


    /**
     * Tests for getTopHeadlinesAustria Method
     */

    /*
    @Test
    @DisplayName ("getTopHeadlinesAustria with empty list of articles")
    public void testGetTopHeadlinesAustriaWhereArticlesIsNull(){
        assertNotEquals(appController.getTopHeadlinesAustria(), null);
    }

     */

    /*
    @Test
    @DisplayName("getTopHeadlinesAustria with filled list of articles")
    public void testGetTopHeadlinesAustriaWithFilledArticleList(){
        List<Article> testArticles = AppController.generateMockList();
        appController.setArticles(testArticles);
        //Currently getTopHeadlinesAustria method extracts all articles
        assertEquals(appController.getTopHeadlinesAustria(), testArticles);
    }

     */

    /**
     * Tests for getAllNewsBitcoin Method
     */

    /*
    @Test
    @DisplayName("getAllNewsBitcoin with list of articles containing Bitcoin")
    public void testGetAllNewsBitcoin(){
        List<Article> testArticles = AppController.generateMockList();
        Article toFilter = new Article("Testautor", "Bitcoin Network");
        testArticles.add(toFilter);
        appController.setArticles(testArticles);
        assertEquals(appController.getAllNewsBitcoin().contains(toFilter), true);
    }

     */

    /*
    @Test
    @DisplayName("getAllNewsBitcoin with list of articles filtered not containing Bitcoin")
    public void testGetAllNewsBitcoinFilteringArticle(){
        List<Article> testArticles = AppController.generateMockList();
        Article toFilter = new Article("Testautor", "Etherium Network");
        testArticles.add(toFilter);
        appController.setArticles(testArticles);
        assertEquals(appController.getAllNewsBitcoin().contains(toFilter), false);
    }

     */
    @Test
    @DisplayName("List contains Bitcoin")
    public void listContainsBitcoin() {
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("James", "Bitcoin Future"));
        list.add(new Article("Silvana", "Yellow Brick Road"));
        list.add(new Article("Agnes", "How to Bitcoin"));
        list.add(new Article("Ashley", "The Way of Life"));
        list.add(new Article("Frank", "The Brand New Car"));
        //Act
        List<Article> actual = AppController.filterList("bitcoin", list);
        //Assert
        assertEquals(2, actual.size());
        if (!actual.get(0).getTitle().equalsIgnoreCase("How to Bitcoin") && !actual.get(0).getTitle().equalsIgnoreCase("Bitcoin Future"))
            fail("Filtered articles are not as expected!");
        if (!actual.get(1).getTitle().equalsIgnoreCase("How to Bitcoin") && !actual.get(1).getTitle().equalsIgnoreCase("Bitcoin Future"))
            fail("Filtered articles are not as expected!");

    }


    @Test
    @DisplayName("Query is empty")
    public void filteredListWithQueryEmpty() {
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("Stefan", "How to be successful with Bitcoin"));
        list.add(new Article("Lisa", "Digital Currency: Bitcoin "));
        list.add(new Article("Pascal", "The Yellow Car"));
        list.add(new Article("Pierre", "The Greatest Show"));
        list.add(new Article("Elodie", "How to succeed without really trying"));
        //Act
        List<Article> actual = AppController.filterList("", list);
        //Assert
        assertEquals(list, actual);
    }

    @Test
    @DisplayName("List is null")
    public void BitcoinlistShouldBeFilteredListNull() {
        assertEquals(null, AppController.filterList("bitcoin", null));
    }

    @Test
    @DisplayName("The List is EMPTY")
    public void BitcoinListShouldBeFilteredListIsEmpty() {
        assertEquals(new ArrayList<>(), AppController.filterList("bitcoin", new ArrayList<>()));
    }

    @Test
    public void mostCommonSourceTest() throws NewsApiException {
        List<Article> articles = new ArrayList<>();
        Source source1 = new Source("source1", "source1");
        Source source2 = new Source("source2", "source2");
        Source source3 = new Source("source3", "source3");
        Article article1 = new Article("article1", "article1");
        article1.setSource(source1);
        Article article2 = new Article("article2", "article2");
        article2.setSource(source1);
        Article article3 = new Article("article3", "article3");
        article3.setSource(source1);
        Article article4 = new Article("article4", "article4");
        article4.setSource(source1);
        Article article5 = new Article("article5", "article5");
        article5.setSource(source1);
        Article article6 = new Article("article6", "article6");
        article6.setSource(source2);
        Article article7 = new Article("article7", "article7");
        article7.setSource(source3);

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);
        articles.add(article6);
        articles.add(article7);
        String expected = source1.getName();
        String actual = appController.getMostSource(articles);
        assertEquals(expected.toLowerCase(), actual.toLowerCase());
    }
    @Test
    @DisplayName("The Article should be sorted by the longest author name")
    public void getAuthorWithLongestNameTest1() throws NewsApiException {
        List<Article> list = new ArrayList<>();
        Article article1 = new Article("kurier.at", "Lamborghini: Mit Vollgas durch alle Krisen | kurier.at - KURIER");
        list.add(article1);
        Article article2 = new Article("finanzen.net Redaktion", "Bitcoin, Ether & Co: So schlagen sich die Kryptokurse am Nachmittag ");
        list.add(article2);
        list.add(new Article("Andreas Anzenberger", " Neues AMA-Gütesiegel für Tierwohl soll Exporte sichern "));
        list.add(new Article("Curtis", "Title: Globaler Kohlen aushöhlen Markt Wachsende Nachfrage nach großen Wettbewerbern und Strategieanalysen bis 2031 "));
        list.add(new Article("Motorradmagazin", " Der elektrische Tourer Neue Energica Experia"));
        String expected = article2.getAuthor();
        String actual = appController.getAuthorWithLongestName(list);
        assertEquals(expected, actual);
        assertNotEquals(article1.getAuthor(),actual);
    }


    @Test
    @DisplayName("sort the article in ascending order by length of the Description")
    public void getDescriptionByLengthTest1() throws NewsApiException{
        List<Article> list = new ArrayList<>();
        Article article1 = new Article("Motorradmagazin", " Der elektrische Tourer Neue Energica Experia","3.290 Fertighäuser sind 2021 in Österreich errichtet worden");
        list.add(article1);
        Article article2 = new Article("finanzen.net Redaktion", "Bitcoin, Ether & Co: So schlagen sich die Kryptokurse am Nachmittag ","Bitcoin, Ether & Co: So schlagen sich die Kryptowährungen am Sonntagvormittag");
        list.add(article2);
        list.add(new Article("Andreas Anzenberger", " Neues AMA-Gütesiegel für Tierwohl soll Exporte sichern ","Der jüngste Bier-Guide listet erneut die besten Lokale für Süffiges in Österreich auf."));
        Article article3 = new Article("Curtis", "Title: Globaler Kohlen aushöhlen Markt Wachsende Nachfrage nach großen Wettbewerbern und Strategieanalysen bis 2031 "," Die Großpetersdorfer Westernstadt Lucky Town startete mit praktisch vollem Haus in die mittlerweile 26.");
        list.add(article3);
        Article article4 =new Article("kurier.at", "Lamborghini"," Der Ukraine-Krieg verändert die Kärntner Landwirtschaft. Agrarprodukte sind zum einen gefragt wie noch nie");
        list.add(article4);
        List<Article> actual = appController.getDescriptionByLength(list);
        assertEquals(list.stream().toList(), actual);
    }
    @Test
    @DisplayName("The Article should be sorted by the shortest Title")
    public void getArticleWithShortTitleTest1()throws NewsApiException{
        List<Article> list = new ArrayList<>();
        Article article1 = new Article("Motorradmagazin", " Der elektrische Tourer Neue Energica Experia","3.290 Fertighäuser sind 2021 in Österreich errichtet worden");
        list.add(article1);
        Article article2 = new Article("finanzen.net Redaktion", "Bitcoin, Ether & Co: So schlagen sich die Kryptokurse am Nachmittag ","Bitcoin, Ether & Co: So schlagen sich die Kryptowährungen am Sonntagvormittag");
        list.add(article2);
        list.add(new Article("Andreas Anzenberger", " Neues AMA-Gütesiegel für Tierwohl soll Exporte sichern ","Der jüngste Bier-Guide listet erneut die besten Lokale für Süffiges in Österreich auf."));
        Article article3 =new Article("kurier.at", "Lamborghini"," Der Ukraine-Krieg verändert die Kärntner Landwirtschaft. Agrarprodukte sind zum einen gefragt wie noch nie");
        list.add(article3);
        Article article4 = new Article("Curtis", "Title: Globaler Kohlen aushöhlen Markt Wachsende Nachfrage nach großen Wettbewerbern und Strategieanalysen bis 2031 "," Die Großpetersdorfer Westernstadt Lucky Town startete mit praktisch vollem Haus in die mittlerweile 26.");
        list.add(article4);
        String expected = article3.getTitle();
        String actual = appController.getArticleWithShortTitle(list);
        assertEquals(expected, actual);
        assertNotEquals(article1.getAuthor(),actual);
    }

    @Test
    @DisplayName("Article should be from Source New York Times")
    public void getArticleFromNewYorkTimesTest1()throws NewsApiException{
        Source source1 = new Source("New York Times", "New York Times");
        Source source2 = new Source("BBC", "BBC");
        Source source3 = new Source("source3", "source3");
        List<Article> list = new ArrayList<>();
        Article article1 = new Article("Chelsia Rose Marcius", " Adams Defends Police for Handcuffing Vendor Who Sold Mangoes in Subway");
        list.add(article1);
        article1.setSource(source1);
        Article article2 = new Article("Jan Ransom, Jonah E. Bromwich and Anne Barnard", "Rikers Island Faces Pressure to End Death and Disorder Crisis");
        list.add(article1);
        article2.setSource(source1);
        Article article3 =new Article("kurier.at", "Lamborghini");
        list.add(article3);
        article3.setSource(source2);
        Article article4 = new Article("Curtis", "Title: Globaler Kohlen aushöhlen Markt Wachsende Nachfrage nach großen Wettbewerbern und Strategieanalysen bis 2031 ");
        list.add(article4);
        article4.setSource(source2);
        Article article5=new Article("Andreas Anzenberger", " Neues AMA-Gütesiegel für Tierwohl soll Exporte sichern ");
        list.add(article5);
        article5.setSource(source3);
        assertNotEquals(3,appController.getArticleFromNewYorkTimes(list));

    }





}
