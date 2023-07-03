package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrancationTest{
    @Test
    void testCommit() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql="INSERT INTO comments(email,comment)VALUES(?,?)";
        for (int i =0; i<100;i++){
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"hallo@gmail.com");
            preparedStatement.setString(2,"hai");
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }



        //connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql="INSERT INTO comments(email,comment)VALUES(?,?)";
        for (int i =0; i<100;i++){
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"hallo@gmail.com");
            preparedStatement.setString(2,"hai");
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }



        connection.rollback();
        connection.close();
    }
}
