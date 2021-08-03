package warsito.musicweblibrary.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class)
    private final User user;

    @NonNull
    private Integer rate;

    @OneToOne(targetEntity = Album.class)
    @NotNull
    private final Album album;

    private final LocalDateTime createdAt;

    @NonNull
    private LocalDateTime modifiedAt;

    @Size(max = 100, message = "maximum size of comment : 100")
    @NonNull
    private String comment;
}
