package com.au.GenesianTheatreCompany.ControllerTest;

import com.au.GenesianTheatreCompany.Controller.UserController;
import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Users;
import com.au.GenesianTheatreCompany.service.LoggingService;
import com.au.GenesianTheatreCompany.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;



@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LoggingService loggingService;

    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private LambdaQueryChainWrapper<Users> lambdaQueryChainWrapper;

    @BeforeEach
    void setup() {
        when(userService.lambdaQuery()).thenReturn(lambdaQueryChainWrapper);
        when(lambdaQueryChainWrapper.eq(any(), any())).thenReturn(lambdaQueryChainWrapper); // Chain the eq call
    }

    @Test
    public void testListUsers() throws Exception {
        List<Users> usersList = Arrays.asList(new Users(), new Users());
        when(userService.listAllUser()).thenReturn(Result.suc(usersList));

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", hasSize(2)));

        verify(userService).listAllUser();
    }

    @Test
    public void testSaveUser() throws Exception {
        Users newUser = new Users();
        newUser.setEmail("newuser@example.com");
        Long uid = 1L;

        when(userService.save(newUser)).thenReturn(true);
        when(userService.getEmailByUid(uid)).thenReturn("admin@example.com");

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser))
                        .param("uid", uid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).save(newUser);
        verify(loggingService).writeLog("admin@example.com adds the user: newuser@example.com");
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long uid = 3L;
        Long adminUid = 1L;

        when(userService.removeById(uid)).thenReturn(true);
        when(userService.getEmailByUid(adminUid)).thenReturn("admin@example.com");
        when(userService.getEmailByUid(uid)).thenReturn("deleteduser@example.com");

        mockMvc.perform(get("/user/delete")
                        .param("uid", uid.toString())
                        .param("adminUid", adminUid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).removeById(uid);
        verify(loggingService).writeLog("admin@example.com deletes the user: deleteduser@example.com");
    }
}