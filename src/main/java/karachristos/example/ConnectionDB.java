package karachristos.example;
import java.sql.*;
public class ConnectionDB {
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
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void takePreviousRec(String codeOfProduct)
    {
        String query="SELECT previousRecord From ProductList WHERE titleOfProduct='"+codeOfProduct+"';";
        try(Connection conn=this.connect(); Statement stmnt=conn.createStatement(); ResultSet res=stmnt.executeQuery(query)){
            if(res.next()==false){
                System.out.println("We have not any same product");
            }else
            {
                System.out.println("Previous Record ");
                do{
                    System.out.println("Previous Record: " + res.getString("previousRecord") + "\n");
                }while ((res.next()));
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}

