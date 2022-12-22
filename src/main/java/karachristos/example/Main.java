package karachristos.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionDB connectionDB=new ConnectionDB();


        System.out.println("---------------------------------------------------------------------");
        System.out.println("--------------------Welcome to our shop's program--------------------");
        System.out.println("---------------------------------------------------------------------");

        System.out.println("Press 1 for Product List");
        System.out.println("Press 2 to add one Product");
        System.out.println("Press 3 to add Multiple Products");
        System.out.println("Press 4 to search a Product");
        System.out.println("Press 5 for DB Statistics");
        System.out.println("Press 6 to Exit");

        Scanner scanner=new Scanner(System.in);
        int personChoice=scanner.nextInt();

        switch(personChoice){
            case 1:
                //TODO
                System.out.println("Product List...");
                connectionDB.connect();
                connectionDB.displayDatabase();
                break;
            case 2:
                //TODO
                System.out.println("Add a product...");
                break;
            case 3:
                //TODO
                System.out.println("Add multiple products...");
                break;
            case 4:
                //TODO
                System.out.println("Search a product...");
                break;
            case 5:
                //TODO
                System.out.println("Statistics of products...");
                break;
            case 6:
                //TODO
                System.out.println("GoodBye");
        }
    }
}