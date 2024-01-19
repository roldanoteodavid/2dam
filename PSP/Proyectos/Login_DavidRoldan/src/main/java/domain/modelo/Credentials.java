package domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    private String username;
    private String email;
    private String password;
    private String role;
    private String accessToken;
    private String refreshToken;
    private String temporalCode;
    private String temporalPassword;
}
