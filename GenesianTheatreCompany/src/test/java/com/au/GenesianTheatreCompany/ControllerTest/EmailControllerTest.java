//package com.au.GenesianTheatreCompany.ControllerTest;
//
//import com.au.GenesianTheatreCompany.Controller.MailController;
//import com.au.GenesianTheatreCompany.Common.Result;
//import com.au.GenesianTheatreCompany.entity.DTO.Email;
//import com.au.GenesianTheatreCompany.service.EmailService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(MailController.class)
//public class EmailControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EmailService emailService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testSendMail() throws Exception {
//        Email email = new Email();
//        email.setEmail("genesian42@outlook.com");
//        email.setSubject("Subject");
//        email.setContent("Content of the email");
//
//        Result expectedResult = Result.suc("Email sent successfully");
//
//        when(emailService.sendEmail(email.getEmail(), email.getSubject(), email.getContent()))
//                .thenReturn(expectedResult);
//
//        mockMvc.perform(post("/send-mail")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(email)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.code", is(200)))
//                .andExpect(jsonPath("$.msg", is("successful")))
//                .andExpect(jsonPath("$.data", is("Email sent successfully")));
//
//        verify(emailService).sendEmail(email.getEmail(), email.getSubject(), email.getContent());
//    }
//
//}
