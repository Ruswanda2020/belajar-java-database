package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BacthTest {
    @Test
    void testBatchStatment() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        Statement statement=connection.createStatement();

        String sql= """
                INSERT INTO comments(email,comment) VALUES ('wanda@gmail.com','hai');
                """;
        for (int i=0;i<=100;i++){
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testBatchPreparestatment() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        String sql= """
                INSERT INTO comments(email,comment) VALUES (?,?);
                """;
        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        for (int i=0;i<=100;i++){
            preparedStatement.clearParameters();
            preparedStatement.setString(1,"wanda@gmail.com");
            preparedStatement.setString(2,"hai");
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();


        preparedStatement.close();
        connection.close();
    }
}
