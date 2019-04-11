package kr.hs.dgsw.web0325.Protocol;

import kr.hs.dgsw.web0325.Domain.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentUserNameProtocol extends Comment {
    private String userName;

    public CommentUserNameProtocol() {
        super();
    }

    public CommentUserNameProtocol(Comment comment, String userName) {
        super(comment);
        this.userName = userName;
    }
}
