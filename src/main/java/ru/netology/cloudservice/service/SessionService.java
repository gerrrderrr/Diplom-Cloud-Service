package ru.netology.cloudservice.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.netology.cloudservice.dto.JWTElement;
import ru.netology.cloudservice.dto.LogInCredentials;
import ru.netology.cloudservice.util.JwsProvider;

@Service
@AllArgsConstructor
public class SessionService {
    private AuthenticationManager authenticationManager;
    private JwsProvider provider;

    public ResponseEntity<JWTElement> logIn(LogInCredentials credentials) {
        String login = credentials.getLogin();
        String password = credentials.getPassword();
        Authentication authentication = authenticate(login, password);
        String username = getUsernameFromAuthentication(authentication);
        JWTElement jwt = generateJwt(username);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    private Authentication authenticate(String login, String password) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login, password));
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return user.getUsername();
    }

    private JWTElement generateJwt(String username) {
        String jwt = provider.build(username);
        return new JWTElement(jwt);
    }

    public ResponseEntity<String> logOut() {
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }
}
