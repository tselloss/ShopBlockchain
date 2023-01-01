package karachristos.example;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

public class Blockchain {
    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(ArrayList<Block> blockchain) {
        this.blockchain = blockchain;
    }

    private ArrayList<Block> blockchain=new ArrayList<>();



    public void addNewBlock(Block block)
    {
        this.blockchain.add(block);
    }

    public void displayBlockchain()
    {

        JSONObject jsonBlockchain= new JSONObject();
        JSONArray array= new JSONArray();
        for(int i=0; i<blockchain.size(); i++) {
            try {
                JSONObject record= new JSONObject();
                record.put("Block: ", i+1);
                record.put("Timestamp: ", blockchain.get(i).getTimeStamp());
                record.put("Hash: ", blockchain.get(i).getHash());
                record.put("Previous Hash: ", blockchain.get(i).getPreviousHash());
                record.put("Data: ", Arrays.toString(blockchain.get(i).getData()));
                array.add(record);
             }catch (JsonIOException e)
            {
                e.printStackTrace();
            }
        }
        jsonBlockchain.put("Blockchain",array);
        Gson gson= new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement= JsonParser.parseString(jsonBlockchain.toJSONString());
        String prettyJson=gson.toJson(jsonElement);
        System.out.println(prettyJson);
    }


    public void jsonParser(JsonObject jsonObject)
    {
//        JsonParser parser= new JsonParser();
//        try{
//
//        }
    }
    public Block getLastBlock()
    {
        return this.blockchain.get(blockchain.size()-1);
    }



}
