package at.ac.fhcampuswien;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {
    private static AppController appController;

    @BeforeEach
    void setup(){
        appController = new AppController();
    }

    @Test
    @DisplayName("List should be set")
    public void listShouldBeSet(){
        List<Article> list = new ArrayList<>();
        Article article = new Article("New Author", "New Title");
        list.add(article);
        appController.setArticles(list);
        List<Article> actual = appController.getArticles();
        assertEquals(1,actual.size(),"List sizes should be the same");
        assertEquals(actual.get(0).getAuthor(),article.getAuthor(),"Authors is not the same");
        assertEquals(actual.get(0).getTitle(),article.getTitle(),"Title is not the same");

    }


    @Test
    @DisplayName("List count when not empty")
    public void listCountWhenNotEmpty(){
        List<Article> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(new Article("New List", "New List"));
        }
        appController.setArticles(list);
        assertEquals(5, appController.getArticleCount());
    }

    @Test
    @DisplayName("List count when empty")
    public void listCountWhenEmpty(){
        List<Article> list = new ArrayList<>();
        appController.setArticles(list);
        assertEquals(0, appController.getArticleCount());
    }

    @Test
    @DisplayName("List count when null")
    public void listCountWhenNull(){
        appController.setArticles(null);
        assertEquals(0, appController.getArticleCount());
    }
    @Test
    @DisplayName("List is filtered")
    public void listShouldBeFiltered(){
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("mohamad","the Sea"));
        list.add(new Article("Diana","Big Tree"));
        list.add(new Article("Max","The Laptop"));
        list.add(new Article("Max","Old laptop"));
        list.add(new Article("Alice","Car in the City"));
        //Act
        List<Article> actual = AppController.filterList("laptop",list);
        //Assert
        assertEquals(2, actual.size());
        if (!actual.get(0).getTitle().equalsIgnoreCase("The Laptop") && !actual.get(0).getTitle().equalsIgnoreCase("Old laptop") )
            fail("Filtered articles are not as expected!");
        if (!actual.get(1).getTitle().equalsIgnoreCase("The Laptop") && !actual.get(1).getTitle().equalsIgnoreCase("Old laptop") )
            fail("Filtered articles are not as expected!");
    }
    @Test
    @DisplayName("Query is empty")
    public void listShouldBeFilteredQueryEmpty(){
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("mohamad","the Sea"));
        list.add(new Article("Diana","Big Tree"));
        list.add(new Article("Max","The Laptop"));
        list.add(new Article("Max","Old laptop"));
        list.add(new Article("Alice","Car in the City"));
        //Act
        List<Article> actual = AppController.filterList("",list);
        //Assert
        assertEquals(list,actual);

    }

    @Test
    @DisplayName("List is null")
    public void listShouldBeFilteredListNull(){
        assertEquals(null,AppController.filterList("query",null));

    }

    @Test
    @DisplayName("List is empty")
    public void listShouldBeFilteredListEmpty(){
        assertEquals(new ArrayList<>(),AppController.filterList("query",new ArrayList<>()));

    }

    @Test
    @DisplayName("Query is not found")
    public void listShouldBeFilteredQueryNotFound(){
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("mohamad","the Sea"));
        list.add(new Article("Diana","Big Tree"));
        list.add(new Article("Max","The Laptop"));
        list.add(new Article("Max","Old laptop"));
        list.add(new Article("Alice","Car in the City"));
        //Act
        List<Article> actual = AppController.filterList("test",list);
        //Assert
        assertEquals(0, actual.size());
    }

    @Test
    @DisplayName ("List contains Bitcoin")
    public void listContainsBitcoin() {
        //Arrange
        List<Article> list = new ArrayList<>();
        list.add(new Article("James", "Bitcoin Future"));
        list.add(new Article("Silvana", "Yellow Brick Road"));
        list.add(new Article("Agnes", "How to Bitcoin"));
        list.add(new Article("Ashley", "The Way of Life"));
        list.add(new Article("Frank", "The Brand New Car"));
        //Act
        List<Article> actual = AppController.filterList("bitcoin",list);
        //Assert
        assertEquals(2, actual.size());
        if(!actual.get(0).getTitle().equalsIgnoreCase("How to Bitcoin") && !actual.get(0).getTitle().equalsIgnoreCase("Bitcoin Future"))
        fail("Filtered articles are not as expected!");
        if(!actual.get(1).getTitle().equalsIgnoreCase("How to Bitcoin") && !actual.get(1).getTitle().equalsIgnoreCase("Bitcoin Future"))
        fail("Filtered articles are not as expected!");

    }

    @Test
    @DisplayName ("getTopHeadlinesAustria with empty list of articles")
    public void testGetTopHeadlinesAustriaWhereArticlesIsNull(){
        assertEquals(appController.getArticles(), null);
        appController.getTopHeadlinesAustria();
        assertNotEquals(appController.getTopHeadlinesAustria(), null);
    }

    @Test
    @DisplayName("getTopHeadlinesAustria with filled list of articles")
    public void testGetTopHeadlinesAustriaWithFilledArticleList(){
        List<Article> testArticles = AppController.generateMockList();
        appController.setArticles(testArticles);
        //Currently getTopHeadlinesAustria method extracts all articles
        assertEquals(appController.getTopHeadlinesAustria(), testArticles);
    }

    @Test
    @DisplayName("getAllNewsBitcoin with list of articles containing Bitcoin")
    public void testGetAllNewsBitcoin(){
        List<Article> testArticles = AppController.generateMockList();
        Article toFilter = new Article("Testautor", "Bitcoin Network");
        testArticles.add(toFilter);
        appController.setArticles(testArticles);
        assertEquals(appController.getAllNewsBitcoin().contains(toFilter), true);
    }

    @Test
    @DisplayName("getAllNewsBitcoin with list of articles filtered not containing Bitcoin")
    public void testGetAllNewsBitcoinFilteringArticle(){
        List<Article> testArticles = AppController.generateMockList();
        Article toFilter = new Article("Testautor", "Etherium Network");
        testArticles.add(toFilter);
        appController.setArticles(testArticles);
        assertEquals(appController.getAllNewsBitcoin().contains(toFilter), false);
    }

    @Test
    @DisplayName("Query is empty")
    public void filteredListWithQueryEmpty(){
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
        assertEquals(list,actual);
    }

    @Test
    @DisplayName("List is null")
    public void BitcoinlistShouldBeFilteredListNull(){
        assertEquals(null,AppController.filterList("bitcoin",null));
    }

    @Test
    @DisplayName("The List is EMPTY")
    public void BitcoinListShouldBeFilteredListIsEmpty(){
        assertEquals(new ArrayList<>(),AppController.filterList("bitcoin", new ArrayList<>()));
    }

}
