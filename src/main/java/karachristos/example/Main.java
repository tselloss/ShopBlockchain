package karachristos.example;

import com.google.gson.GsonBuilder;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static List<Block> blockChain = new ArrayList<>();
    public static int prefix = 2;

    public String title;
    public String price;
    public String description;
    public String category;
    public String json;

    public static <Blocks> void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionDB connectionDB=new ConnectionDB();
        Date date= Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

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
                System.out.println("Product List...");
                connectionDB.showProductList();
                break;
            case 2:
                System.out.println("Now you to press some info about product");
                System.out.println("Press the Title of product");
                Scanner scanner1=new Scanner(System.in);
                String title = scanner1.nextLine();
                System.out.println("Press the price of "+title);
                Scanner scanner2=new Scanner(System.in);
                String price= scanner2.nextLine();
                System.out.println("Give me description of the "+title);
                Scanner scanner3=new Scanner(System.in);
                String descr= scanner3.nextLine();
                System.out.println("Give me the category of "+title);
                Scanner scanner4=new Scanner(System.in);
                String category= scanner4.nextLine();
                Products product=new Products("Code"+String.valueOf(new Random().nextInt(10000)),""+title,""+strDate,"$"+price,""+descr,""+category,""+connectionDB.takePreviousRec(""+title));
                Block genesisBlock= new Block(connectionDB.takePreviousHash(),product.toArray(),strDate);
                genesisBlock.mineBlock(prefix);
                blockChain.add(genesisBlock);
                System.out.println(genesisBlock.getHash());
                System.out.println("Is the BlockChain created well? "+isChainValid());
                //BlockChain To Json
                String json= new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
                System.out.println(json);
                connectionDB.insertNewItem(genesisBlock);
                break;
            case 3:
                System.out.println("Add multiple products...");
                System.out.println("How many products do you want to add?");
                int numOfProds= Integer.parseInt(scanner.nextLine());
                while (numOfProds>0)
                {
                    System.out.println("Press the Title of product");
                    Scanner scanner5=new Scanner(System.in);
                    title= scanner5.nextLine();
                    System.out.println("Press the price of "+title);
                    Scanner scanner6=new Scanner(System.in);
                    price= scanner6.nextLine();
                    System.out.println("Give me description of the "+title);
                    Scanner scanner7=new Scanner(System.in);
                    descr= scanner7.nextLine();
                    System.out.println("Give me the category of "+title);
                    Scanner scanner8=new Scanner(System.in);
                    category= scanner8.nextLine();
                    Products products=new Products("Code"+String.valueOf(new Random().nextInt(10000)),""+title,""+strDate,"$"+price,""+descr,""+category,""+connectionDB.takePreviousRec(""+title));
                    Block genesisBlocks= new Block(connectionDB.takePreviousHash(),products.toArray(),strDate);
                    genesisBlocks.mineBlock(prefix);
                    blockChain.add(genesisBlocks);
                    System.out.println(genesisBlocks.getHash());
                    System.out.println("Is the BlockChain created well? "+isChainValid());
                    //BlockChain To Json
                    String jsons= new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
                    System.out.println(jsons);
                    connectionDB.insertNewItem(genesisBlocks);
                }
                break;
            case 4:
                System.out.println("Search a product...");
                System.out.println("Press the title of product");
                Scanner scanner9=new Scanner(System.in);
                String title1=scanner9.nextLine();
                System.out.println("Press 1 to see the first record");
                System.out.println("Press 2 to see the last record");
                System.out.println("Press 3 to see all record");
                System.out.println("Press 4 to exit");
                Scanner scanner10= new Scanner(System.in);
                int personChoise= scanner10.nextInt();
                switch (personChoise)
                {
                    case 1:
                        connectionDB.showFirstProduct(title1);
                        break;
                    case 2:
                        connectionDB.showLastProduct(title1);
                        break;
                    case 3:
                        connectionDB.showProduct(title1);
                        break;
                    case 4:
                        break;
                }
                break;
            case 5:
                System.out.println("Statistics of products...");
                System.out.println("Give the title of product that you want to show the statistics");
                Scanner scanner11= new Scanner(System.in);
                String titleForStatistics= scanner11.nextLine();
                connectionDB.getStatistics(titleForStatistics);
                break;
            case 6:
                //TODO
                System.out.println("GoodBye");
        }
    }

    public static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[prefix]).replace('\0','0');
        for (int i=1;i<blockChain.size();i++){
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
            if (!currentBlock.getHash().equals(currentBlock.calculateBlockHash()))
                return false;
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash()))
                return false;
            if (!currentBlock.getHash().substring(0,prefix).equals(hashTarget))
                return false;
        }
        return true;
    }

}