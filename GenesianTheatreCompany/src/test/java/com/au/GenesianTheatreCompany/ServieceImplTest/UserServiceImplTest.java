package com.au.GenesianTheatreCompany.ServieceImplTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.au.GenesianTheatreCompany.service.impl.UserServiceImpl;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Users;
import com.au.GenesianTheatreCompany.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllUser() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(new Users(), new Users());
        when(userMapper.listAll()).thenReturn(expectedUsers);

        // Act
        Result result = userService.listAllUser();

        // Assert
        assertNotNull(result);
        assertTrue(result.getData() instanceof List<?>);
        assertEquals(expectedUsers, result.getData());
        verify(userMapper).listAll();
    }

    @Test
    public void testSaveUser() {
        // Arrange
        String username = "testUser";
        String password = "testPass";
        when(userMapper.saveUser(username, password)).thenReturn(true);

        // Act
        boolean success = userService.saveUser(username, password);

        // Assert
        assertTrue(success);
        verify(userMapper).saveUser(username, password);
    }

    @Test
    public void testCheckUidIsValid() {
        // Arrange
        Long uid = 1L;
        when(userMapper.checkUidIsValid(uid)).thenReturn(true);

        // Act
        boolean isValid = userService.checkUidIsValid(uid);

        // Assert
        assertTrue(isValid);
        verify(userMapper).checkUidIsValid(uid);
    }

    @Test
    public void testGetEmailByUid() {
        // Arrange
        Long uid = 1L;
        String expectedEmail = "user@example.com";
        when(userMapper.getEmailByUid(uid)).thenReturn(expectedEmail);

        // Act
        String email = userService.getEmailByUid(uid);

        // Assert
        assertEquals(expectedEmail, email);
        verify(userMapper).getEmailByUid(uid);
    }
}