package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestPrepareStatment {
    @Test
    void testPrepareStatment()throws SQLException {

        Connection connection=ConnetionUtil.getDataSource().getConnection();

        String username="admin";
        String password="admin";
        String sql= " SELECT * FROM admin WHERE username=? and password=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next()){
            System.out.println("sukses login :"+resultSet.getNString("username"));
        }else {
            System.out.println("GAGAL LOGIN");
        }


        preparedStatement.close();
        connection.close();

    }
}
