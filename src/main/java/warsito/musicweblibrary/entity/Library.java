package warsito.musicweblibrary.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import warsito.musicweblibrary.Rate;

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

    @OneToOne(targetEntity = User.class)
    private User user;

    @Enumerated
    private Rate rate;

    @OneToOne(targetEntity = Album.class)
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
