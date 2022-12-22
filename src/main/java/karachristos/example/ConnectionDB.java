package karachristos.example;
import java.sql.*;

public class ConnectionDB {
    Connection connection=null;
    public void connect() throws SQLException {
        
        String url="jdbc:sqlite:C:\\sqlite\\mydatabase.db";
        try {
            connection= DriverManager.getConnection(url);
            System.out.println("Connection with SQLite is ready");
        } catch (SQLException e) {
            throw new RuntimeException(e);
//        }finally {
//            if (connection!=null)
//                connection.close();
        }
    }
    public void displayDatabase() throws SQLException {
        String select="Select * from SHOP_Table";
        //String tableName = "SHOP_Table";
        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery(select);
        System.out.println(resultSet);
        while(resultSet.next())
        {
            //TODO sout everything
        }
    }

}
