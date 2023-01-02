package karachristos.example;
import java.sql.*;
import java.util.ArrayList;


public class ConnectionDB {
    Blockchain bc;
    public ConnectionDB (Blockchain blockchain){
        bc=blockchain;
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:ShopData.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void showProductList() throws SQLException {

        String query="SELECT rowid,* FROM ProductList;";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any product");
            }else
            {
                System.out.println("Product List: ");
                do{
                    System.out.println(
                            "PRODUCT: " +
                                    res.getInt("rowid") + "\n" +
                                    "Product Code: " + res.getString("codeOfProduct") + "\n" +
                                    "Title: " + res.getString("titleOfProduct") + "\n" +
                                    "Time of Creation: " + res.getString("timestamps") + "\n" +
                                    "Price: " + res.getString("price") + "\n" +
                                    "Description: " + res.getString("description") + "\n" +
                                    "Category: " +res.getString("category") + "\n" +
                                    "Previous Record: " + res.getString("previousRecord") + "\n"
                    );
                }while ((res.next()));

            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }


    public void insertNewItem(Block block) throws SQLException {
        String[] data= block.toArray();
        //Integer id, String productCode,String titleOfProduct,String timestamps,String price,String description,String category,String previousHash
        String query="INSERT INTO ProductList(hashCode,previousHash,timestamps,nonce,codeOfProduct,titleOfProduct,price,description,category,previousRecord) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection=this.connect(); PreparedStatement pstmt=connection.prepareStatement(query)){
            pstmt.setString(1,data[0]);
            pstmt.setString(2, data[1]);
            pstmt.setString(3,data[2]);
            pstmt.setString(4,data[3]);
            pstmt.setString(5,data[4]);
            pstmt.setString(6,data[5]);
            pstmt.setString(7,data[6]);
            pstmt.setString(8,data[7]);
            pstmt.setString(9,data[8]);
            pstmt.setString(10,data[9]);
            pstmt.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("A new Item with title: "+data[5]+" is inserted at time: "+data[2]);
    }


    public String takePreviousHash() throws SQLException {
        String query="SELECT hashCode From ProductList ORDER BY rowid DESC LIMIT 1";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            while (res.next()) {
                return res.getString("hashCode");
            }
        }
        return "";
    }

    public String takePreviousRec(String titleOfProduct) throws SQLException {
        String query="SELECT codeOfProduct From ProductList WHERE titleOfProduct='"+titleOfProduct+"' ORDER BY rowid DESC LIMIT 1";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            while (res.next()) {
                return res.getString("codeOfProduct");
            }
        }
        return "";
    }
    public void showFirstProduct(String title) throws SQLException {
        String query="SELECT rowid,* FROM ProductList where titleOfProduct='"+title+"' Order By rowid ASC LIMIT 1;";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any product");
            }else
            {
                System.out.println("Product List: ");
                do{
                    System.out.println(
                            "PRODUCT: " +
                                    res.getInt("rowid") + "\n" +
                                    "Product Code: " + res.getString("codeOfProduct") + "\n" +
                                    "Title: " + res.getString("titleOfProduct") + "\n" +
                                    "Time of Creation: " + res.getString("timestamps") + "\n" +
                                    "Price: " + res.getString("price") + "\n" +
                                    "Description: " + res.getString("description") + "\n" +
                                    "Category: " +res.getString("category") + "\n" +
                                    "Previous Record: " + res.getString("previousRecord") + "\n"
                    );
                }while ((res.next()));

            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void showLastProduct(String title) throws SQLException {
        String query="SELECT rowid,* FROM ProductList where titleOfProduct='"+title+"' Order By rowid DESC LIMIT 1;";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any product");
            }else
            {
                System.out.println("Product List: ");
                do{
                    System.out.println(
                            "PRODUCT: " +
                                    res.getInt("rowid") + "\n" +
                                    "Product Code: " + res.getString("codeOfProduct") + "\n" +
                                    "Title: " + res.getString("titleOfProduct") + "\n" +
                                    "Time of Creation: " + res.getString("timestamps") + "\n" +
                                    "Price: " + res.getString("price") + "\n" +
                                    "Description: " + res.getString("description") + "\n" +
                                    "Category: " +res.getString("category") + "\n" +
                                    "Previous Record: " + res.getString("previousRecord") + "\n"
                    );
                }while ((res.next()));

            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void showProduct(String title) throws SQLException {
        String query="SELECT rowid,* FROM ProductList where titleOfProduct='"+title+"'";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any product");
            }else
            {
                System.out.println("Product List: ");
                do{
                    System.out.println(
                            "PRODUCT: " +
                                    res.getInt("rowid") + "\n" +
                                    "Product Code: " + res.getString("codeOfProduct") + "\n" +
                                    "Title: " + res.getString("titleOfProduct") + "\n" +
                                    "Time of Creation: " + res.getString("timestamps") + "\n" +
                                    "Price: " + res.getString("price") + "\n" +
                                    "Description: " + res.getString("description") + "\n" +
                                    "Category: " +res.getString("category") + "\n" +
                                    "Previous Record: " + res.getString("previousRecord") + "\n"
                    );
                }while ((res.next()));

            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    int i=0;
    public void getStatistics(String title)  {
        ArrayList<String> priceOfProducts= new ArrayList<>();
        ArrayList<String> timestampOfProduct= new ArrayList<>();
        String[] price={};
        String query="SELECT * FROM ProductList where titleOfProduct='"+title+"'";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any product with this title: "+title);
            }else
            {
                do{
                    priceOfProducts.add(res.getString("price"));
                    timestampOfProduct.add(res.getString("timestamps"));
                }while ((res.next()));
            }
            if (priceOfProducts.size()>2) {
                System.out.println(priceOfProducts);
                System.out.println(timestampOfProduct);
            }else
            {
                System.out.println("We have not enough records to show the statistics for this product!!!");
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void getInfoFromDB()
    {
        ArrayList<Block> dbSavedBlocks= new ArrayList<>();
        String query="SELECT * From ProductList";

        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any product");
            }else
            {
                do{
                    Products products= new Products(res.getString("codeOfProduct"),
                            res.getString("titleOfProduct"),
                            res.getString("timestamps"),
                            res.getString("price"),
                            res.getString("description"),
                            res.getString("category"),
                            res.getString("previousRecord"));
                    Block block= new Block(res.getString("previousHash"),res.getString("hashCode"), products.toArray(),res.getString("timestamps"), Integer.parseInt(res.getString("nonce")));
                    dbSavedBlocks.add(block);
                }while ((res.next()));
            }
            if (bc.getBlockchain().isEmpty()) {
                bc.setBlockchain((ArrayList<Block>) dbSavedBlocks.clone());
            }
            else if (dbSavedBlocks.size()==bc.getBlockchain().size())
            {

            }
            else if(dbSavedBlocks.size()>bc.getBlockchain().size())
            {
             for (int i=bc.getBlockchain().size(); i<=dbSavedBlocks.size()-1; i++)
             {
                 bc.addNewBlock(dbSavedBlocks.get(i));
             }
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


