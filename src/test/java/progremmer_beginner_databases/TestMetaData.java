package progremmer_beginner_databases;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestMetaData {
    @Test
    void testMetadata() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData=connection.getMetaData();


        ResultSet resultSet = databaseMetaData.getTables("belajar_java_databses",null,null,null);
        while (resultSet.next()){
            String tabelName=resultSet.getNString("TABEL_NAME");
            System.out.println("tabel : "+tabelName);
        }

        resultSet.close();
        connection.close();
    }
    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        String sql="INSERT INTO comments(email,comment)VALUES(?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData=preparedStatement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());
        //System.out.println(parameterMetaData.getParameterType(1));




        preparedStatement.close();
        connection.close();
    }
    @Test
    void testResultMetaData() throws SQLException {
        Connection connection=ConnetionUtil.getDataSource().getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM sample_time ");
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
            System.out.println("nama colum : "+resultSetMetaData.getColumnName(i));
            System.out.println("colum type : "+resultSetMetaData.getColumnType(i));
            System.out.println("type name : "+resultSetMetaData.getColumnTypeName(i));
        }


        statement.close();
        resultSet.close();
        connection.close();
    }
}
