package karachristos.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blockchain {
    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(ArrayList<Block> blockchain) {
        this.blockchain = blockchain;
    }

    private ArrayList<Block> blockchain=new ArrayList<>();


    public Blockchain()
    {
        blockchain= new ArrayList<Block>();
        blockchain.add(genesisBlock());
    }
    private Block genesisBlock()
    {
        Block genesis = new Block("", new String[]{""},"");
        genesis.setPreviousHash(null);
        genesis.calculateBlockHash();
        return genesis;
    }


    public void addNewBlock(Block block)
    {
        Block newBlock= block;
        newBlock.setPreviousHash(blockchain.get(blockchain.size()-1).getHash());
        newBlock.calculateBlockHash();
        this.blockchain.add(newBlock);
    }

    public void displayBlockchain()
    {
        for(int i=1; i<blockchain.size(); i++)
        {
            System.out.println("Block: "+i);
            System.out.println("Timestamp: "+blockchain.get(i).getTimeStamp());
            System.out.println("Hash: "+blockchain.get(i).getHash());
            System.out.println("Previous Hash: "+blockchain.get(i).getPreviousHash());
            System.out.println("Data: "+ Arrays.toString(blockchain.get(i).getData()));
        }
    }
    public Block getLastBlock()
    {
        return this.blockchain.get(blockchain.size()-1);
    }



}
