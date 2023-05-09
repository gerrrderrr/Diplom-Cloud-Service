package ru.netology.cloudservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import ru.netology.cloudservice.dto.JWTElement;
import ru.netology.cloudservice.dto.LogInCredentials;
import ru.netology.cloudservice.entity.Role;
import ru.netology.cloudservice.entity.User;
import ru.netology.cloudservice.service.SessionService;

@SpringBootTest
public class SessionTest {

    @Autowired
    SessionService service;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void logIn() {
        LogInCredentials credentials = new LogInCredentials();
        credentials.setLogin("admin");
        credentials.setPassword("1111111111");
        Mockito.doReturn(Mockito.mock(Authentication.class))
                .when(authenticationManager).authenticate(Mockito.mock(Authentication.class));
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.doReturn(new User(credentials.getLogin(), "", credentials.getPassword(), "", "", 1, Role.USER))
                .when(authentication).getPrincipal();

        JWTElement jwtElement = service.logIn(credentials).getBody();
        String jwt = jwtElement.getJwt();
        Assertions.assertNotNull(jwt);
    }
}
