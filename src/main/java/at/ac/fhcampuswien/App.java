package at.ac.fhcampuswien;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Article> list = AppController.generateMockList();
        for (Article article : list){
            System.out.println(article.getAuthor() + "\t\t" + article.getTitle());
            System.out.println("#######################################################################################");
        }


        Menu menu = new Menu();
        menu.start();
    }
}
