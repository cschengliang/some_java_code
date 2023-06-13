package jdbc.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> t1 = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");

        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = t1.get();
        if(connection == null){
            connection = dataSource.getConnection();
            t1.set(connection);
        }
        return connection;
    }
    public static void freeConnection() throws SQLException {
        Connection connection = t1.get();
        if(connection != null){
            t1.remove();
            connection.setAutoCommit(true);
        }
        connection.close();
    }
}
