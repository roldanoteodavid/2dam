package org.example.authenticationserver_davidroldan.ui.auth;

import lombok.RequiredArgsConstructor;
import org.example.authenticationserver_davidroldan.domain.modelo.Credentials;
import org.example.authenticationserver_davidroldan.domain.modelo.LoginTokens;
import org.example.authenticationserver_davidroldan.domain.servicios.ServiceCredentials;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final ServiceCredentials serviceCredentials;

    @PostMapping("/login")
    public LoginTokens doLogin(@RequestBody Credentials c) {
        return serviceCredentials.doLogin(c.getUsername(), c.getPassword());
    }

    @PostMapping("/register")
    public Boolean singUp(@RequestBody Credentials c) {
        if (c.getRole() == null) {
            c.setRole("USER");
        }
        return serviceCredentials.singUp(new Credentials(0, c.getUsername(), c.getPassword(), c.getRole()));
    }

    @GetMapping("/refresh")
    public LoginTokens refreshToken(@RequestParam(name = "token") String token) {
        LoginTokens c = serviceCredentials.refreshToken(token);
        if (c != null) {
            return new LoginTokens(c.getAccessToken(), c.getRefreshToken());
        } else {
            return null;
        }
    }

}
