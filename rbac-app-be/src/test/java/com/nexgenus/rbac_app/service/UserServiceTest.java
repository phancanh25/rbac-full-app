package com.nexgenus.rbac_app.service;

import com.nexgenus.rbac_app.entity.Role;
import com.nexgenus.rbac_app.entity.User;
import com.nexgenus.rbac_app.repository.RoleRepository;
import com.nexgenus.rbac_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void testRegisterUser_Success() {
        String username = "testuser";
        String rawPassword = "password";
        String roleName = "ROLE_USER";

        // Ensure user does not already exist
        when(userRepository.existsByUsername(username)).thenReturn(false);
        // When encoding, return an encoded password
        when(passwordEncoder.encode(rawPassword)).thenReturn("encodedPassword");
        // Return a valid role
        Role role = new Role();
        role.setName(roleName);
        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(role));
        // When saving, return a user with the role set
        User savedUser = new User(username, "encodedPassword");
        savedUser.setRoles(Collections.singleton(role));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(username, rawPassword, roleName);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals("encodedPassword", result.getPassword());
        assertTrue(result.getRoles().contains(role));

        verify(userRepository).existsByUsername(username);
        verify(passwordEncoder).encode(rawPassword);
        verify(roleRepository).findByName(roleName);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UsernameAlreadyTaken() {
        String username = "existinguser";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userService.registerUser(username, "password", "ROLE_USER")
        );
        assertEquals("Username is already taken.", exception.getMessage());

        verify(userRepository).existsByUsername(username);
        verifyNoMoreInteractions(roleRepository, passwordEncoder, userRepository);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        String username = "testuser";
        String encodedPassword = "encodedPassword";
        String roleName = "ROLE_USER";

        // Create a user with a role
        User user = new User(username, encodedPassword);
        Role role = new Role();
        role.setName(roleName);
        user.setRoles(Collections.singleton(role));

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(encodedPassword, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(roleName)));

        verify(userRepository).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "nonexistent";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                userService.loadUserByUsername(username)
        );

        verify(userRepository).findByUsername(username);
    }
}

