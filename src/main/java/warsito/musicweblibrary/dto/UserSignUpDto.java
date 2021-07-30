package warsito.musicweblibrary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class UserSignUpDto {
    private final String username;
    private final String password;
    private final String email;
}