package karachristos.example;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static List<Block> blockChain = new ArrayList<>();
    public static int prefix = 6;

    public static <Blocks> void main(String[] args) throws SQLException, InterruptedException {
        String[] titleRandom = {"Nike Shoe", "Adidas Shoe", "Vans Shoe"};
        String[] priceRandom = {"80", "90", "100"};
        String[] descrRandom = {"It's black", "It's blue", "It's white"};
        String[] categoryRandom = {"Sneakers", "Outdoor", "Indoor"};
        Blockchain bc = new Blockchain();
        ConnectionDB connectionDB = new ConnectionDB(bc);
        connectionDB.createTable();
        boolean run = true;
        while (run) {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            connectionDB.getInfoFromDB();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("--------------------Welcome to our shop's program--------------------");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Press 1 -> Product List");
            System.out.println("Press 2 -> Add one Product");
            System.out.println("Press 3 -> Add Multiple Products");
            System.out.println("Press 4 -> Search a Product");
            System.out.println("Press 5 -> Blockchain Statistics");
            System.out.println("Press 6 -> Exit");
            Scanner scanner = new Scanner(System.in);
            int personChoice = scanner.nextInt();
            switch (personChoice) {
                case 1:
                    System.out.println("Product List...");
                    bc.displayBlockchain();
                    break;
                case 2:
//                    System.out.println("Now you to press some info about product");
//                    System.out.println("Press the Title of product");
//                    Scanner scanner1 = new Scanner(System.in);
//                    String title = scanner1.nextLine();
//                    System.out.println("Press the price of " + title);
//                    Scanner scanner2 = new Scanner(System.in);
//                    String price = scanner2.nextLine();
//                    System.out.println("Give me description of the " + title);
//                    Scanner scanner3 = new Scanner(System.in);
//                    String descr = scanner3.nextLine();
//                    System.out.println("Give me the category of " + title);
//                    Scanner scanner4 = new Scanner(System.in);
//                    String category = scanner4.nextLine();
                    int titleR = new Random().nextInt(titleRandom.length);
                    String title = titleRandom[titleR];
                    int priceR = new Random().nextInt(priceRandom.length);
                    String price = priceRandom[priceR];
                    int descrR = new Random().nextInt(descrRandom.length);
                    String descr = descrRandom[descrR];
                    int categoryR = new Random().nextInt(categoryRandom.length);
                    String category = categoryRandom[categoryR];
                    Products product = new Products("Code" + String.valueOf(new Random().nextInt(10000)), "" + title, "" + currentDate.toString(), "$" + price, "" + descr, "" + category, "" + connectionDB.takePreviousRec("" + title));
                    Thread blockprod= new Thread(new Block(connectionDB.takePreviousHash(), product.toArray(), currentDate.toString(), connectionDB));
                    blockprod.start();
                    blockprod.join();
                    break;
                case 3:
                    Timestamp currentDate1 = new Timestamp(System.currentTimeMillis());
                    System.out.println("Add multiple products...");
                    System.out.println("How many products do you want to add?");
                    int numOfProds = scanner.nextInt();
                    while (numOfProds>0) {
//                        System.out.println("Press the Title of product");
//                        Scanner scanner5 = new Scanner(System.in);
//                        title = scanner5.nextLine();
//                        System.out.println("Press the price of " + title);
//                        Scanner scanner6 = new Scanner(System.in);
//                        price = scanner6.nextLine();
//                        System.out.println("Give me description of the " + title);
//                        Scanner scanner7 = new Scanner(System.in);
//                        descr = scanner7.nextLine();
//                        System.out.println("Give me the category of " + title);
//                        Scanner scanner8 = new Scanner(System.in);
//                        category = scanner8.nextLine();
                        int titleR1 = new Random().nextInt(titleRandom.length);
                        String title1 = titleRandom[titleR1];
                        int priceR1 = new Random().nextInt(priceRandom.length);
                        String price1 = priceRandom[priceR1];
                        int descrR1 = new Random().nextInt(descrRandom.length);
                        String descr1 = descrRandom[descrR1];
                        int categoryR1 = new Random().nextInt(categoryRandom.length);
                        String category1 = categoryRandom[categoryR1];
                        Products products = new Products("Code" + String.valueOf(new Random().nextInt(10000)), "" + title1, "" + currentDate.toString(), "$" + price1, "" + descr1, "" + category1, "" + connectionDB.takePreviousRec("" + title1));
                        Thread blockprod1= new Thread(new Block(connectionDB.takePreviousHash(), products.toArray(), currentDate1.toString(), connectionDB));
                        blockprod1.start();
                        blockprod1.join();
                        numOfProds--;
                    }
                    break;
                case 4:
                    System.out.println("Search a product...");
                    System.out.println("Press the title of product");
                    Scanner scanner9 = new Scanner(System.in);
                    String title1 = scanner9.nextLine();
                    bc.getProducts(title1);
                    break;
                case 5:
                    System.out.println("Statistics of products...");
                    System.out.println("Give the title of product that you want to show the statistics");
                    Scanner scanner11 = new Scanner(System.in);
                    String titleForStatistics = scanner11.nextLine();
                    bc.getStatistics(titleForStatistics);
                    break;
                case 6:
                    System.out.println("GoodBye");
                    run = false;
            }
        }
    }

        public static boolean isChainValid() {
            Block currentBlock;
            Block previousBlock;
            String hashTarget = new String(new char[prefix]).replace('\0', '0');
            for (int i = 1; i < blockChain.size(); i++) {
                currentBlock = blockChain.get(i);
                previousBlock = blockChain.get(i - 1);
                if (!currentBlock.getHash().equals(currentBlock.calculateBlockHash()))
                    return false;
                if (!previousBlock.getHash().equals(currentBlock.getPreviousHash()))
                    return false;
                if (!currentBlock.getHash().substring(0, prefix).equals(hashTarget))
                    return false;
            }
            return true;
        }
    }
