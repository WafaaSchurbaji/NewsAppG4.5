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



}
