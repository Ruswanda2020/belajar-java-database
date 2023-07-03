package progremmer_beginner_databases;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnetionPoolTest {

    @Test
    void hikariCpTest() throws SQLException {
        HikariConfig config =new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
        config.setUsername("root");
        config.setPassword("wanda@20101999");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10*60_000);

        try {

            HikariDataSource hikariDataSource=new HikariDataSource(config);
            Connection connection=hikariDataSource.getConnection();
            connection.close();
            hikariDataSource.close();


        }catch (SQLException e){
            Assertions.fail(e);
        }

    }
    @Test
    void tesUtil() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
    }

}
