package com.vandemos.authenticationservice.security;

import com.vandemos.authenticationservice.role.Role;
import com.vandemos.authenticationservice.role.RoleEnum;
import com.vandemos.authenticationservice.user.User;
import com.vandemos.authenticationservice.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FormLoginTests {

    @LocalServerPort
    private int port;

    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String username = "username";
    private static final String password = "password";

    public void init(){
        userRepository.deleteAll();
        User user = new User();
        user.setEnabled(true);
        user.setActivated(true);
        user.setEmail("some@mail.com");
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);

        Role role = new Role();
        role.setRoleEnum(RoleEnum.USER);
        user.setRole(role);
        userRepository.save(user);
    }

    @Test
    public void loginTest(){
        init();

        MultiValueMap<String, String> loginForm = new LinkedMultiValueMap<>();
        loginForm.add("username", username);
        loginForm.add("password", password);

        Mono<ClientResponse> response = WebClient
                .create("http://localhost:" + port + "/login")
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(loginForm)
                .exchange();

        StepVerifier
                .create(response)
                .assertNext(clientResponse -> {
                    assertFalse(clientResponse.headers().header(HttpHeaders.AUTHORIZATION).isEmpty(),
                            "Authorization header is empty");
                    assertFalse(clientResponse.headers().header("Refresh-Token").isEmpty(),
                            "Refresh-Token header is empty");
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void loginTest_wrongCredentials(){
        init();

        MultiValueMap<String, String> loginForm = new LinkedMultiValueMap<>();
        loginForm.add("username", username);
        loginForm.add("password", "some_wrong_password");

        Mono<ClientResponse> response = WebClient
                .create("http://localhost:" + port + "/login")
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(loginForm)
                .exchange();

        StepVerifier
                .create(response)
                .assertNext(clientResponse -> {
                    assertTrue(clientResponse.headers().header(HttpHeaders.AUTHORIZATION).isEmpty(),
                            "Authorization header is not empty");
                    assertTrue(clientResponse.headers().header("Refresh-Token").isEmpty(),
                            "Refresh-Token header is not empty");
                    assertEquals(HttpStatus.BAD_REQUEST, clientResponse.statusCode());
                })
                .expectComplete()
                .verify();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
