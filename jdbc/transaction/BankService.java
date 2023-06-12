package jdbc.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankService {
    @Test
    public void start() throws SQLException, ClassNotFoundException {
        transfer("li", "zhang", 900);
    }

    //    public void transfer(String addAccount,String subAccount,int money) throws SQLException, ClassNotFoundException {
//        BankDao bankDao = new BankDao();
//        //一个事务的最基本要求，必须是同一个连接对象
//        //一个转账方法，属于一个事务，只使用同一个连接即可。
//        bankDao.add(addAccount,money);
//        System.out.println("---------------");
//        bankDao.sub(subAccount,money);
//    }
    public void transfer(String addAccount, String subAccount, int money) throws ClassNotFoundException, SQLException {
        BankDao banDao = new BankDao();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.10.10.32/atguigu1", "cheng", "pass");

        try {
            //开启事务
            connection.setAutoCommit(false);

            //执行数据库动作
            banDao.add(addAccount,money,connection);
            System.out.println("----");
            banDao.sub(subAccount,money,connection);
            connection.commit();
            //事务提交
        }catch (Exception e){
            connection.rollback();
            throw e;
        }finally {
            connection.close();
        }



    }
}
