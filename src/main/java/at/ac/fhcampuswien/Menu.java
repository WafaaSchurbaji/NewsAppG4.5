package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu  {
    private AppController controller;
    private static final String INVALID_USER_INPUT_MESSAGE = "Invalid Input";
    private static final String EXIT_MESSAGE="finished";


    public  void  start(){


    }

    private void handleInput(String input){
    }


    private void getArticleCount(AppController ctrl) {
        this.controller.getArticleCount();
    }

    private void getTopHeadlinesAustria(AppController ctrl) {

    }
    private void getAllNewsBitcoin(AppController ctrl) {

    }

    private static void printExitMessage(){
        System.out.println("Bye Bye");
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage(){
        System.out.println(INVALID_USER_INPUT_MESSAGE);
    }
    private static void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("*     Welcome to NewsApp     *");
        System.out.println("**********************************");
        System.out.println("Enter what you wanna do:");
        System.out.println("a: Get top headlines austria \nb: Get all news about bitcoin" +
                "\ny: Count articles \nq: Quit program");
    }
}




