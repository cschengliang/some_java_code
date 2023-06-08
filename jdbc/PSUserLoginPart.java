package jdbc;

import java.sql.*;
import java.util.Scanner;

public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account: ");
        String account = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        //2.ps的数据库流程
        //2.1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");


        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1", "cheng", "pass");

        String sql = "select * from t_user where account = ? and password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, account);
        preparedStatement.setObject(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Login success");
        } else {
            System.out.println("Login failed");
        }
        //8.关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
