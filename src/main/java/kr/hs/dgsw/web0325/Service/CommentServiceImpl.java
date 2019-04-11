package kr.hs.dgsw.web0325.Service;

import kr.hs.dgsw.web0325.Domain.Comment;
import kr.hs.dgsw.web0325.Domain.User;
import kr.hs.dgsw.web0325.Protocol.CommentUserNameProtocol;
import kr.hs.dgsw.web0325.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }


    @Override
    public CommentUserNameProtocol add(Comment comment) {
        commentRepository.save(comment);
        return new CommentUserNameProtocol(comment, userService.view(comment.getUserId()).map(User::getName).orElse(null));
    }

    @Override
    public List<CommentUserNameProtocol> list() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentUserNameProtocol> protocols = new ArrayList<>();
        comments.forEach(comment -> {
            Optional<User> found = userService.view(comment.getUserId());
            String userName = found.map(User::getName).orElse(null);
            protocols.add(new CommentUserNameProtocol(comment, userName));
        });

        return protocols;
    }

    @Override
    public CommentUserNameProtocol view(long id) {
        return commentRepository.findById(id).map(
                comment -> {
                    Optional<User> found = userService.view(comment.getUserId());
                    String userName = found.map(User::getName).orElse(null);
                    return new CommentUserNameProtocol(comment, userName);
                }).orElse(null);
    }

    @Override
    public Comment update(long id, Comment comment) {
        return commentRepository.findById(id).map(
                found -> {
                    found.setContent(Optional.ofNullable(comment.getContent()).orElse(found.getContent()));
                    return commentRepository.save(found);
                }).orElse(null);
    }

    @Override
    public boolean delete(long id) {
        return commentRepository.findById(id).map(
                comment -> {
                    commentRepository.delete(comment);
                    return true;
                }).orElse(false);
    }
}
