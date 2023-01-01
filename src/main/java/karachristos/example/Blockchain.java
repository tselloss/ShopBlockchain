package karachristos.example;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Blockchain {
    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    JSONObject jsonBlockchain = new JSONObject();
    JSONArray array = new JSONArray();
    public String prettyJson;
    public void setBlockchain(ArrayList<Block> blockchain) {
        this.blockchain = blockchain;
    }

    private ArrayList<Block> blockchain = new ArrayList<>();


    public void addNewBlock(Block block) {
        this.blockchain.add(block);
    }

    public void displayBlockchain() {
        for (int i = 0; i < blockchain.size(); i++) {
            try {
                JSONObject record = new JSONObject();
                record.put("Block: ", i + 1);
                record.put("Timestamp: ", blockchain.get(i).getTimeStamp());
                record.put("Hash: ", blockchain.get(i).getHash());
                record.put("Previous Hash: ", blockchain.get(i).getPreviousHash());
                record.put("Data: ", Arrays.toString(blockchain.get(i).getData()));
                array.add(record);
            } catch (JsonIOException e) {
                e.printStackTrace();
            }
        }
        jsonBlockchain.put("Blockchain", array);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(jsonBlockchain.toJSONString());
        prettyJson = gson.toJson(jsonElement);
        System.out.println(prettyJson);
    }


    public void getLastBlock(String title) {

        ArrayList<String[]> products = new ArrayList<>();
        for(Block block: blockchain){
            String[] blocks = block.getData();
            if(Arrays.asList(blocks).contains(title)){
                products.add(blocks);
            }
        }
        if(products.isEmpty())
        {
            System.out.println("We have not any product with the same title");
        } else if (products.size()==1) {
            JSONObject jsonObject1 = new JSONObject();
            JSONArray jsonArray1 = new JSONArray();
            System.out.println("Is only one product with this title: "+title);
            JSONObject record= new JSONObject();
            jsonObject1.put("Product Code: ", products.get(0)[0]);
            jsonObject1.put("Title of product: ", products.get(0)[1]);
            jsonObject1.put("Timestamp: ", products.get(0)[2]);
            jsonObject1.put("Price: ", products.get(0)[3]);
            jsonObject1.put("Description: ", products.get(0)[4]);
            jsonObject1.put("Category: ", products.get(0)[5]);
            jsonObject1.put("Last record: ", products.get(0)[6]);
            jsonArray1.add(jsonObject1);
            Gson json = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = JsonParser.parseString(jsonObject1.toJSONString());
            String prettyJson = json.toJson(jsonElement);
            System.out.println(prettyJson);
        } else if (products.size()>1) {
            System.out.println("We have more than one products with this name");
            System.out.println("You can choose between some options");
            System.out.println("Press 1 -> See the first record of product with title: "+title);
            System.out.println("Press 2 -> See the last record of product with title: "+title);
            System.out.println("Press 3 -> See all the record of product with title: "+title);
            Scanner scanner = new Scanner(System.in);
            Integer titleForStatistics = scanner.nextInt();
            switch (titleForStatistics){
                case 1:
                    JSONArray jsonArray = new JSONArray();
                    System.out.println("Is only one product with this title: "+title);
                    JSONObject record= new JSONObject();
                    record.put("Product Code: ", products.get(0)[0]);
                    record.put("Title of product: ", products.get(0)[1]);
                    record.put("Timestamp: ", products.get(0)[2]);
                    record.put("Price: ", products.get(0)[3]);
                    record.put("Description: ", products.get(0)[4]);
                    record.put("Category: ", products.get(0)[5]);
                    record.put("Last record: ", products.get(0)[6]);
                    jsonArray.add(record);
                    Gson json = new GsonBuilder().setPrettyPrinting().create();
                    JsonElement jsonElement = JsonParser.parseString(record.toJSONString());
                    String prettyJson = json.toJson(jsonElement);
                    System.out.println(prettyJson);
                    break ;
                case 2:
                    JSONArray jsonArray1 = new JSONArray();
                    System.out.println("Is only one product with this title: "+title);
                    JSONObject record1= new JSONObject();
                    record1.put("Product Code: ", products.get(products.size()-1)[0]);
                    record1.put("Title of product: ", products.get(products.size()-1)[1]);
                    record1.put("Timestamp: ", products.get(products.size()-1)[2]);
                    record1.put("Price: ", products.get(products.size()-1)[3]);
                    record1.put("Description: ", products.get(products.size()-1)[4]);
                    record1.put("Category: ", products.get(products.size()-1)[5]);
                    record1.put("Last record: ", products.get(products.size()-1)[6]);
                    jsonArray1.add(record1);
                    Gson json1 = new GsonBuilder().setPrettyPrinting().create();
                    JsonElement jsonElement1 = JsonParser.parseString(jsonArray1.toJSONString());
                    String prettyJson1 = json1.toJson(jsonElement1);
                    System.out.println(prettyJson1);
                    break;
                case 3:
                    //todo is not working please check the Json record
                    JSONObject jsonObject2=new JSONObject();
                    JSONObject record2 = new JSONObject();
                    for (int i=0; i==products.size()-1; i++) {
                        record2.put("Product Code: ", products.get(i)[0]);
                        record2.put("Title of product: ", products.get(i)[1]);
                        record2.put("Timestamp: ", products.get(i)[2]);
                        record2.put("Price: ", products.get(i)[3]);
                        record2.put("Description: ", products.get(i)[4]);
                        record2.put("Category: ", products.get(i)[5]);
                        record2.put("Last record: ", products.get(i)[6]);
                        array.add(record2);
                    }
                    jsonObject2.put("Multiple records of product", array);
                    Gson json2 = new GsonBuilder().setPrettyPrinting().create();
                    JsonElement jsonElement2 = JsonParser.parseString(record2.toJSONString());
                    String prettyJson2 = json2.toJson(jsonElement2);
                    System.out.println(prettyJson2);
                    break;
            }

        }
    }


}
