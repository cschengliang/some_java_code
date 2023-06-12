package jdbc.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//硬编码的方式
public class DruidUsePart {
    //硬编码的方式
    public void testHard() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl("jdbc:mysql://10.10.10.32/atguigu1");
        dataSource.setUsername("cheng");
        dataSource.setPassword("pass");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);

        DruidPooledConnection connection = dataSource.getConnection();
        //数据库crud

        connection.close();//连接池提供的连接，close,就是回收连接
    }
    //通过读取外部连接配置文件的方式
    @Test
    public void testSoft() throws Exception {
        Properties properties = new Properties();

        InputStream ips = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");

        properties.load(ips);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        connection.close();
    }
}
