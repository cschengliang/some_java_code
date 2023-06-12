package jdbc;

import org.junit.Test;

import java.sql.*;

public class PSOtherPart {


    //主键获取
    @Test
    public void returnPrimaryKey() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://10.10.10.32/atguigu1?user=cheng&password=pass");

        //便携SQL语句
        String sql = "insert into t_user(account,password,nickname) values (?,?,?);";
        //创建statement
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //占位符赋值
        statement.setObject(1, "test2");
        statement.setObject(2, "12322456");
        statement.setObject(3, "伊利丹");
        //发送SQL语句，并获取结果
        int i = statement.executeUpdate();
        //解析结果
        if (i > 0) {
            System.out.println("数据插入成功！");
            //可以获取回显的主键
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();//移动下光标
            int id = resultSet.getInt(1);
            System.out.println("id = " + id);
        } else {
            System.out.println("数据插入失败！");
        }
        //关闭资源
        statement.close();
        connection.close();


    }

    @Test
    public void testInsert() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://10.10.10.32/atguigu1?user=cheng&password=pass");

        //便携SQL语句
        String sql = "insert into t_user(account,password,nickname) values (?,?,?);";
        //创建statement
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //占位符赋值
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            statement.setObject(1, "tes2t2" + i);
            statement.setObject(2, "1221322456" + i);
            statement.setObject(3, "伊2利丹" + i);
            statement.executeUpdate();
        }


        statement.close();
        connection.close();

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) / 1000);

    }
    @Test

    public void testInsert1() throws Exception {
        /** 1. 路径添加rewriteBatchedStatements = true
         *  2. insert into values [] 语句后面不能添加;
         *  3.不是执行语句，而是批量添加addBatch()
         *  4.遍历添加完毕，统一批量执行 executeBatch
         *
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://10.10.10.32/atguigu1?rewriteBatchedStatements=True", "cheng", "pass");

        //便携SQL语句
        String sql = "insert into t_user(account,password,nickname) values (?,?,?)";
        //创建statement
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //占位符赋值
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            statement.setObject(1, "qq21w2t2" + i);
            statement.setObject(2, "121wq2ewq21322456" + i);
            statement.setObject(3, "伊2wq1qw2利丹" + i);
            statement.addBatch();//添加，不执行
        }
        statement.executeBatch();

        statement.close();
        connection.close();
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) / 1000);

    }

    public static void main(String[] args) {

    }
}
