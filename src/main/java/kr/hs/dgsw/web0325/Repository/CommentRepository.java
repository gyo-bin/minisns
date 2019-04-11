package kr.hs.dgsw.web0325.Repository;


import kr.hs.dgsw.web0325.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}