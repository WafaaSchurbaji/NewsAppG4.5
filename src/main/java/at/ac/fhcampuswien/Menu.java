package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu  {
    private AppController controller;
    private static final String INVALID_USER_INPUT_MESSAGE = "Invalid Input";
    private static final String EXIT_MESSAGE="Bye Bye";


    public  void  start(){
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();

        handleInput(input);
            }


    private void handleInput(String input){
      /* if (!(input == "a" || input == "b" || input == "y" || input == "q"))
       {
          // printInvalidInputMessage();
        }*/
        if ("a".equals(input)) {
            getTopHeadlinesAustria(controller);
        } else if ("b".equals(input)) {
            getAllNewsBitcoin(controller);
        } else if ("y".equals(input)) {
            getArticleCount(controller);
        } else if ("q".equals(input)) {
            printExitMessage();
        } else {
            printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl) {

        System.out.println(this.controller.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        controller = new AppController();
        System.out.println( controller.getTopHeadlinesAustria());


    }
    private void getAllNewsBitcoin(AppController ctrl) {
        controller= new AppController();
        System.out.println(controller.getAllNewsBitcoin());

    }

    private static void printExitMessage(){
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    private static void printInvalidInputMessage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(INVALID_USER_INPUT_MESSAGE);
        System.out.println("Please enter what you wanna do:");
       scanner.nextLine().trim().toLowerCase();
    }
    private static void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("*     Welcome to NewsApp     *");
        System.out.println("**********************************");
        System.out.println("Enter what you wanna do:");
        System.out.println("a: Get top headlines austria \nb: Get all news about bitcoin" +
                "\ny: Count articles \nq: Quit program");
        System.out.println();
    }
}




