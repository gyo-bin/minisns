package kr.hs.dgsw.web0325.Controller;

import kr.hs.dgsw.web0325.Domain.Comment;
import kr.hs.dgsw.web0325.Protocol.CommentUserNameProtocol;
import kr.hs.dgsw.web0325.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    public List<CommentUserNameProtocol> list() {
        return commentService.list();
    }

    @GetMapping("/comment/{id}")
    public CommentUserNameProtocol view(@PathVariable long id) {
        return commentService.view(id);
    }

    @PostMapping("/comment")
    public Comment add(@RequestBody Comment comment) {
        return commentService.add(comment);
    }

    @PutMapping("/comment/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment comment) {
        return commentService.update(id, comment);
    }

    @DeleteMapping("/comment/{id}")
    public boolean delete(@PathVariable long id) {
        return commentService.delete(id);
    }
}