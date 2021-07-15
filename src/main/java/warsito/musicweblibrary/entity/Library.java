package warsito.musicweblibrary.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated
    private Rate rate;

    @OneToOne
    @JoinColumn(name = "ALBUM_ID")
    @NotNull
    private Album album;

    private LocalDateTime createdAt;

    @Size(max = 100, message = "maximum size of comment : 100")
    private String comment;

    @PrePersist
    void createdAt(){
        this.createdAt = LocalDateTime.now();
    }
}
