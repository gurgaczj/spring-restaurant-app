package com.vandemos.authenticationservice.user;

import com.vandemos.authenticationservice.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTests {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private AppUserDetailsService userDetailsService;

    @Test
    public void loadUserByUsernameTest(){
        User user = UserServiceImplTests.createTestUser();

        String username = user.getUsername();
        Mockito.when(userService.findUserByUsername(username)).thenReturn(user);

        UserDetails result = userDetailsService.loadUserByUsername(username);

        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
        assertTrue(result.getAuthorities().stream()
                .map(Object::toString)
                .anyMatch(auth -> auth.equals(user.getRole().getRoleEnum().name())));
    }

    @Test
    public void loadUserByUsernameTest_methodReturnEmpty(){
        String username = "someUsername";
        Mockito.when(userService.findUserByUsername(username)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}
