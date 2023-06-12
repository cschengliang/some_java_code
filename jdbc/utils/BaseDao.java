package jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDao {
    public int executeUpdate(String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i, params[i]);
        }
        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();

        if (connection.getAutoCommit()) {
            JdbcUtilsV2.freeConnection();
        }

        return rows;
    }
}
