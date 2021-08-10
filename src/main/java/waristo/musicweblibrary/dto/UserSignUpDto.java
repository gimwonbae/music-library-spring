package waristo.musicweblibrary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class UserSignUpDto {
    private final String username;
    private final String password;
    private final String email;
}