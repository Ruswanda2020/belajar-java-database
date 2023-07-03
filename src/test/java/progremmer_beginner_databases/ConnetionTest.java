package progremmer_beginner_databases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnetionTest {
    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver= new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        }catch (SQLException exception){
            Assertions.fail(exception);
        }
    }

    @Test
    void connectionTest() {

        String jdbcUrl="jdbc:mysql://localhost:3306/belajar_java_database";
        String username="root";
        String password="wanda@20101999";

        try {
            Connection connection=DriverManager.getConnection(jdbcUrl,username,password);
            System.out.println("connection succsess");
        }catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void connectionCloseTest() {

        String jdbcUrl="jdbc:mysql://localhost:3306/belajar_java_database";
        String username="root";
        String password="wanda@20101999";

        try(Connection connection=DriverManager.getConnection(jdbcUrl,username,password)) {
            System.out.println("connection succsess");
            //connection.close();
        }catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }
}
