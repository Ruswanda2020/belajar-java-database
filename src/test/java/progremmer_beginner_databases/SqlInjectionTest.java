package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {
    @Test
    void testSqlIjection() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        Statement statement=connection.createStatement();

        String username="admin'#";
        String password="false";

        String sql= "SELECT * FROM admin WHERE username='"+username+"" +
                "'AND password ='"+password+"'";
        System.out.println(sql);

        ResultSet resultSet=statement.executeQuery(sql);
        if (resultSet.next()){
            System.out.println("sukses login : "+resultSet.getNString("username"));
        }else {
            System.out.println("gagal login");
        }




        statement.close();
        connection.close();
    }
}
