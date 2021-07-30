package warsito.musicweblibrary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class UserSignInDto {
    private final String username;
    private final String password;
}