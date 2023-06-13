package jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {
    public static int executeUpdate(String sql,Object... params) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JdbcUtils.freeConnection();
        }
        return rows;
    }
}
