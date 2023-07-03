package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTimeTest {
    @Test
    void testDateTime() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();

        String sql= """
                INSERT INTO sample_time(sample_date,sample_time,sample_timestamp)
                VALUES(?,?,?)
                """;
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setDate(1,new Date(System.currentTimeMillis()));
        preparedStatement.setTime(2,new Time(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
    @Test
    void testDateQuery() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();

        String sql= """
               SELECT*FROM sample_time;
                """;
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            Time time=resultSet.getTime("sample_time");
            System.out.println("time : " +time);
            Date date=resultSet.getDate("sample_time");
            System.out.println("date : "+date);
            Timestamp timestamp=resultSet.getTimestamp("sample_time");
            System.out.println("timestamp : "+timestamp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}


