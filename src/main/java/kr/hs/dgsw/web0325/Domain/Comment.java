package kr.hs.dgsw.web0325.Domain;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Comment(Comment comment) {
        id = comment.id;
        userId = comment.userId;
        content = comment.content;
        created = comment.created;
        modified = comment.modified;
    }
}