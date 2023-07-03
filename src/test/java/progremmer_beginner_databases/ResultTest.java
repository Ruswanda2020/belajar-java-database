package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultTest {

    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        Statement statement=connection.createStatement();

        String sql= """
                SELECT * FROM customers;
                """;
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            String id=resultSet.getNString("id");
            String nama=resultSet.getNString("nama");
            String email=resultSet.getNString("email");
            System.out.println(
                    String.join(",",id,nama,email)
            );

        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
