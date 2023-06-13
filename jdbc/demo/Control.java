package jdbc.demo;


import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static jdbc.demo.BaseDao.executeUpdate;

public class Control {

    public void addUser() throws SQLException {
        String sql = "insert into t_score(name, student_id, score) VALUES (?,?,?);";
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String student_id = scanner.nextLine();
        int score = scanner.nextInt();

        int i = executeUpdate(sql, name, student_id, score);
        System.out.println("i= " + i);
    }

    @Test
    public void show() {


    }

    /**
     * @param <T> 实体类集合的模版对象
     * @param sql 查询语句，要求列名或者列的别名与实体类的属性名一致
     * @param params 占位符的值，和对应的?赋值
     * @param T 声明结果的类型

     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        Connection connection = JdbcUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (params == null && params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        //定义一个list负责传出值
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        //结果的数目
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            //获胜实例化对象
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                //解释：获得结果集中的值
                Object value = resultSet.getObject(i);
                //解释：获得结果集中的列名
                String propertyName = metaData.getColumnLabel(i);
                //解释：获得属性的名字，然后通过反射获得属性
                Field field = clazz.getDeclaredField(propertyName);
                //属性可以设置，打破private的限制
                field.setAccessible(true);
                //1.要赋值的对象，静态可赋值为null
                //2.具体的属性值
                field.set(t, value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JdbcUtils.freeConnection();
        }
        return list;

    }

}
