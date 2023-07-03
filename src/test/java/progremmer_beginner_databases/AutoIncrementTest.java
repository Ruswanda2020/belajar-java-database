package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {
        @Test
        void testAutoIncrementTest() throws SQLException {
                Connection connection=ConnetionUtil.getDataSource().getConnection();
                String sql= """
                INSERT INTO comments(email,comment) VALUES (?,?);
                """;
                PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                for (int i=0;i<=100;i++){
                        preparedStatement.clearParameters();
                        preparedStatement.setString(1,"wanda@gmail.com");
                        preparedStatement.setString(2,"hai");
                        preparedStatement.addBatch();
                }
                preparedStatement.executeUpdate();

                ResultSet resultSet=preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                        System.out.println("id comment : "+resultSet.getInt(1));
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
        }
}

