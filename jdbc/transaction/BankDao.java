package jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankDao {
    /**
     * @param
     */

    public void add(String account,int money,Connection connection) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1","cheng","pass");
        String sql = "update t_bank set money = money + ? where account = ?;";

        PreparedStatement statement  = connection.prepareStatement(sql);
        statement.setInt(1,money);
        statement.setString(2,account);

        statement.executeUpdate();

        statement.close();
        System.out.println("加钱成功");


    }
    public void sub(String account,int money,Connection connection) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1","cheng","pass");
        String sql = "update t_bank set money = money - ? where account = ?;";

        PreparedStatement statement  = connection.prepareStatement(sql);
        statement.setInt(1,money);
        statement.setString(2,account);

        statement.executeUpdate();

        statement.close();

    }

}
