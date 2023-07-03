package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestStatment {
    @Test
    void testCreatStatment() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        Statement statement=connection.createStatement();


        statement.close();
        connection.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        Statement statement=connection.createStatement();

        String sql= """
                INSERT INTO customers(id,nama,email)
                VALUES("1002","eko","eko@gmail.com")
                """;
        int result=statement.executeUpdate(sql);
        System.out.println(result);

        statement.close();
        connection.close();
    }
}
