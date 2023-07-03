package progremmer_beginner_databases.Repository;

import progremmer_beginner_databases.entity.Comments;

import java.util.List;

public interface CommentRepository {

    void insert(Comments comments);
    Comments findByid(Integer id);
    List<Comments> findAll();
    List<Comments> findAllByEmail(String email);


}
