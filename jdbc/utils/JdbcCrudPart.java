package jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcCrudPart {
    public void testInsert()throws SQLException{
        Connection connection =JdbcUtilsV2.getConnection();
        JdbcUtilsV2.freeConnection();
    }
    @Test
    public void test() throws SQLException {
        testInsert();
    }
}
