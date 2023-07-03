package progremmer_beginner_databases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import progremmer_beginner_databases.Repository.CommentRepository;
import progremmer_beginner_databases.Repository.CommentRepositoryImpl;
import progremmer_beginner_databases.entity.Comments;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;
    @BeforeEach
    void setUp() {
        commentRepository=new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comments comments=new Comments("wanda@gmail.com","haiii");
        commentRepository.insert(comments);

        Assertions.assertNotNull(comments.getId());
        System.out.println(comments.getId());
    }

    @Test
    void testFindById() {
        Comments comments=commentRepository.findByid(610);
        Assertions.assertNotNull(comments);
        System.out.println(comments.getId());
        System.out.println(comments.getEmail());
        System.out.println(comments.getComment());

        Comments notFound=commentRepository.findByid(10000);
        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
        List<Comments> comments=commentRepository.findAll();
        System.out.println(comments.size());
    }

    @Test
    void testFindByEmail() {
        List<Comments> comments=commentRepository.findAllByEmail("wanda@gmail.com");
        System.out.println(comments.size());
    }
}
