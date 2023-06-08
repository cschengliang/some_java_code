package jdbc;

import com.mysql.cj.jdbc.Driver;

import javax.xml.transform.Result;
import java.sql.*;

public class StatementQueryPart  {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        /**
         * TODO:
         *  1.注册驱动的第一种方式    DriverManager.registerDriver(new Driver());
         *  依赖：驱动版本 8: com.mysql.cj.jdbc.Driver
         *  依赖：驱动版本 5: com.mysql.jdbc.Driver
         */
        DriverManager.registerDriver(new Driver());
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32:3306/atguigu1","cheng","pass");
        //3.创建statement
        Statement statement = connection.createStatement();
        //4.执行sql
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);
        //5.结果集处理
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println("id:"+id+" account:"+account+" password:"+password+" nickname:"+nickname);
        }

        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();


    }
}
