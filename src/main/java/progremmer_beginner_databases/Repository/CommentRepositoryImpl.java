package progremmer_beginner_databases.Repository;

import progremmer_beginner_databases.ConnetionUtil;
import progremmer_beginner_databases.entity.Comments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository{
    @Override
    public void insert(Comments comments) {
        try(Connection connection= ConnetionUtil.getDataSource().getConnection()) {
            String sql="INSERT INTO comments(email,comment)VALUES(?,?)";
            try(PreparedStatement statement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1,comments.getEmail());
                statement.setString(2,comments.getComment());
                statement.executeUpdate();

                try (ResultSet resultSet=statement.getGeneratedKeys()){
                    if (resultSet.next()){
                        comments.setId(resultSet.getInt(1));
                    }
                }
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
    @Override
    public Comments findByid(Integer id) {
        try(Connection connection= ConnetionUtil.getDataSource().getConnection()) {
            String sql="SELECT * FROM comments WHERE id=?";
            try(PreparedStatement statement=connection.prepareStatement(sql)) {
                statement.setInt(1,id);
                try(ResultSet resultSet=statement.executeQuery()){
                    if (resultSet.next()){
                        return new Comments(
                                resultSet.getInt("id"),
                                resultSet.getNString("email"),
                                resultSet.getNString("comment"));
                    }else {
                        return null;
                    }
                }
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
    @Override
    public List<Comments> findAll() {
        try(Connection connection= ConnetionUtil.getDataSource().getConnection()) {
            String sql="SELECT * FROM comments";
            try(PreparedStatement statement=connection.prepareStatement(sql)) {
                try (ResultSet resultSet=statement.executeQuery()){
                    List<Comments> comments=new ArrayList<>();
                    while (resultSet.next()){
                        comments.add(new Comments(
                                resultSet.getInt("id"),
                                resultSet.getNString("email"),
                                resultSet.getNString("comment")));
                    }
                    return comments;
                }
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
    @Override
    public List<Comments> findAllByEmail(String email) {
        try (Connection connection = ConnetionUtil.getDataSource().getConnection()) {
                String sql="SELECT * FROM comments WHERE email=?";
                try(PreparedStatement statement=connection.prepareStatement(sql)) {
                    statement.setString(1,email);
                    try(ResultSet resultSet=statement.executeQuery()) {
                        List<Comments> comments=new ArrayList<>();
                        while (resultSet.next()){
                            comments.add(new Comments(
                                   resultSet.getInt("id"),
                                    resultSet.getNString("email"),
                                    resultSet.getNString("comment")
                            ));
                        }
                        return comments;
                    }
                }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }
}
