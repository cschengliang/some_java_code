package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class PSCURDPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        /** t_user 插入一条数据
         *  account
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1", "cheng", "pass");
        String sql = "insert into t_user(account,password,nickname) values(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "小二");

        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1", "cheng", "pass");
        String sql = "update t_user set account = ?,password = ?,nickname = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "陈");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "test");
        preparedStatement.setObject(4, 1);
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }

        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1", "cheng", "pass");
        String sql = "delete from t_user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 1);
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("数据删除成功");
        } else {
            System.out.println("数据删除失败");
        }

        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        /**
         * 查询所有用户数据，并且封装到一个List<Map> list集合中
         * 解释：
         * 行 id account password nickname
         * 行 id account password nickname
         * 行 id account password nickname
         *数据库 一行一个map,key = id ,value = List,List里面存放一行的值
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1", "cheng", "pass");
        String sql = "select * from t_user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        //装载列的信息
        ResultSetMetaData MetaData = preparedStatement.getMetaData();
        int columnCount = MetaData.getColumnCount();

        List<Map> list = new ArrayList<>();
        while (resultSet.next()){
            HashMap map = new HashMap();

            for (int i = 1; i <= columnCount; i++) {
                //获取指定列下角标的值！resultSet
                Object value = resultSet.getObject(i);
                //优先获取列的别名
                String columnLabel =  MetaData.getColumnLabel(i);
                map.put(columnLabel,value);
            }
            list.add(map);
        }
        System.out.println(list);
    }
    public static void main(String[] args) {

    }
}
