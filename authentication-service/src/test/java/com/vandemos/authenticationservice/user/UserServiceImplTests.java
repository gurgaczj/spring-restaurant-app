package com.vandemos.authenticationservice.user;

import com.vandemos.authenticationservice.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findByIdTest(){
        User user = createTestUser();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void findByIdTest_repoReturnEmptyResult(){
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(2L));
    }

    @Test
    public void saveUser(){
        User user = createTestUser();
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getEmail(), result.getEmail());
    }

    private User createTestUser() {
        User user = new User();
        user.setEnabled(true);
        user.setActivated(true);
        user.setEmail("some@mail.com");
        user.setPassword("password");
        user.setUsername("username");
        user.setId(1L);
        return user;
    }
}
