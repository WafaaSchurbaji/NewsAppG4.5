package at.ac.fhcampuswien;
import java.util.Scanner;

public class Menu  {
    private AppController controller = new AppController();
    private static final String INVALID_USER_INPUT_MESSAGE = "Invalid Input";
    private static final String EXIT_MESSAGE="Bye Bye";


    Scanner scanner = new Scanner(System.in);


    public  void  start(){
        printMenu();
        String input = scanner.next().trim().toLowerCase();
        handleInput(input);

            }


    private void handleInput(String input){

        if ("a".equals(input)) {
            getTopHeadlinesAustria(controller);
           start();
        } else if ("b".equals(input)) {
            getAllNewsBitcoin(controller);
           start();
        } else if ("y".equals(input)) {
            getArticleCount(controller);
            start();
        } else if ("q".equals(input)) {
            printExitMessage();

        } else {
            printInvalidInputMessage();
            start();

        }
    }

    private void getArticleCount(AppController ctrl) {
        System.out.println(ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        System.out.println( ctrl.getTopHeadlinesAustria());


    }
    private void getAllNewsBitcoin(AppController ctrl) {
        System.out.println(ctrl.getAllNewsBitcoin());

    }

    private static void printExitMessage(){
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    private static void printInvalidInputMessage(){
        System.out.println(INVALID_USER_INPUT_MESSAGE);

    }
    private static void printMenu() {

        System.out.println("**********************************\n"+
        "*     Welcome to NewsApp     *\n" +
        "**********************************\n" +
       "Enter what you wanna do:\n"+
        "a: Get top headlines austria \n" +
                "b: Get all news about bitcoin\n" +
                "y: Count articles \n" +
                "q: Quit program");
    }



}




