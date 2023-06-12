package jdbc.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSCURDPart extends BaseDao {
    @Test
    public void testInsert() throws SQLException {
        String sql = "";
        int i = executeUpdate("", "", "");
        System.out.println("i = " + i);
    }


//非DQL语句封装方法
//DQL语句封装  ->返回值类型
/*
    1.确定泛型User.class T = User
    2.要使用反射技术属性赋值
    public <T> List<T> executeQuery(String sql,Object .. params);
 */
    public <T> List<T> executeQuery(Class<T> clazz,String sql,Object ... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if(params == null && params.length !=0){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i,params[i-1]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> list = new ArrayList<>();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()){
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                Object value = resultSet.getObject(i);
                String propertyName = metaData.getColumnLabel(i);

                Field field = clazz.getDeclaredField(propertyName);
                field.setAccessible(true);

                field.set(t,value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JdbcUtilsV2.freeConnection();
        }
        return list;

    }

}