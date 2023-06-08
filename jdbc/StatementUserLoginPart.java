package jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Collection;
import java.util.Scanner;

/**
 * @Author: 程亮
 * Description: 模拟用户登陆
 * TODO:
 *      1.明确jdbc的使用流程和步骤
 */

public class StatementUserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.获取用户输入信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号：");
        String account = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        //2.注册驱动
        /**
         * 方案1:
         *     DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
         *     8+ com.mysql.cj.jdbc.Driver
         *     5+ com.mysql.jdbc.Driver
         * 问题：注册两次驱动，浪费资源
         * 1.DriverManager.registerDriver()
         * 2.Driver.static{DriverManager.registerDriver()}
         * 触发类加载：
         *  1.类中有静态代码块
         *  2.类中有静态成员变量
         *  3.类中有静态方法
         *  4.类中有实例方法
         *  5.类中有构造方法
         *  6.类中有main方法
         *  7.反射
         */
//        DriverManager.registerDriver(new Driver());
        //字符串的方式
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接
        //三个参数：
        // String URL:  jdbc:mysql://ip:port/databaseName
        //              jdbc:mysql://ip:port/databaseName?useSSL=false&serverTimezone=UTC
        //?后面可以添加参数，以键值对的形式。
        //两个参数：
        // String URL: jdbc:mysql://ip:port/databaseName?useSSL=false&serverTimezone=UTC
        // String info: user=xxx&password=xxx
        //        jdbc:mysql://ip:port/databaseName
        //一个参数：
        // String URL: jdbc:mysql://ip:port/databaseName?user=xxx&password=xxx&useSSL=false&serverTimezone=UTC
        // String user:
        // String password:
        //如果是8.0.25版本，需要添加一下信息：url?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false
        //可选信息：serverTimezone=UTC 时区 useSSL=false  不使用SSL加密 allowPublicKeyRetrieval=true 允许公钥检索 allowMultiQueries=true 允许多条查询  useUnicode=true&characterEncoding=utf-8  字符集 rewriteBatchedStatements=true  批量更新
//        DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1","cheng","pass");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1?user=cheng&password=pass");
        /**
         * SQL分类：DDL: create alter drop truncate
         *         DML: insert update delete
         *         DQL: select
         *         DCL: grant revoke
         *
         */
        //3.获取执行sql语句的对象
        Statement statement = connection.createStatement();
        //4.执行sql语句
        String sql = "select * from t_user where account='"+account+"' and password='"+password+"' ";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account1 = resultSet.getString("account");
            String password1 = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println("id:"+id+" account:"+account1+" password:"+password1+" nickname:"+nickname);
        }
        //5.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
