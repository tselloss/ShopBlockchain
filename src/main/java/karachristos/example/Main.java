package karachristos.example;

import com.google.gson.GsonBuilder;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static List<Block> blockChain = new ArrayList<>();
    public static int prefix = 2;
    public static <Blocks> void main(String[] args) throws SQLException, ClassNotFoundException {
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
                System.out.println("Product List...");
                connectionDB.showProductList();
                break;
            case 2:
                System.out.println("Add a product...");
                Date date= Calendar.getInstance().getTime();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                Products product=new Products("w222","Nike Shoes",""+strDate,"$5","Is black","Basketball Shoe","");
                Block genesisBlock= new Block("0",product.toArray(),new Date().getTime());
                genesisBlock.mineBlock(prefix);
                blockChain.add(genesisBlock);
                System.out.println(genesisBlock.getHash());
                System.out.println("Node "+(blockChain.size()));
                System.out.println("Is the BlockChain created well? "+isChainValid());
                //BlockChain To Json
                String json= new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
                System.out.println(json);
                //connectionDB.insertNewItem(genesisBlock);
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