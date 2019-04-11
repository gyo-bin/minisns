package kr.hs.dgsw.web0325.Service;

import kr.hs.dgsw.web0325.Domain.Comment;
import kr.hs.dgsw.web0325.Protocol.CommentUserNameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUserNameProtocol> list();
    CommentUserNameProtocol view(long id);
    CommentUserNameProtocol add(Comment comment);
    Comment update(long id, Comment comment);
    boolean delete(long id);
}