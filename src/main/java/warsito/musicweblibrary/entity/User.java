package warsito.musicweblibrary.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private String id;

    @NotBlank
    @Size(min = 4, max = 10, message = "size of username : 4 to 10")
    private String username;

    @Email
    @NotBlank
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Size(min = 8, max = 15, message = "size of password: 8 to 15")
    private String password;

    @NotBlank
    @Max(value = 10)
    private int level;

    @PrePersist
    void createdAt() { this.createdAt = LocalDateTime.now();}
}
