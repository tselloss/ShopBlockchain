package karachristos.example;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String hash;
    private String previousHash;

    public String[] getData() {
        return data;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    private String[] data;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    private String timeStamp;
    private int nonce;

    public Block(String previousHash, String[] data, String timeStamp) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }
    public String calculateBlockHash(){
        String dataToHash = previousHash + timeStamp.toString()
                +data+Integer.toString(nonce);
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }

    public String mineBlock(int prefix){
        String prefixString =
                new String(new char[prefix]).replace('\0','5');
        while (!hash.substring(0,prefix).equals(prefixString)){
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }
    public String getHash() {
        return hash;
    }
    public String getPreviousHash() {
        return previousHash;
    }
    public String[] toArray()
    {
        return new String[]{this.hash,this.previousHash, this.timeStamp, String.valueOf(this.nonce),data[0], data[1], data[3], data[4], data[5], data[6]};
    }

}

