package com.au.GenesianTheatreCompany.ControllerTest;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.Controller.ShowController;
import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.service.LoggingService;
import com.au.GenesianTheatreCompany.service.ShowService;
import com.au.GenesianTheatreCompany.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ShowController.class)
public class ShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowService showService;
    @MockBean
    private LoggingService loggingService;
    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // Example Test for list() method
    @Test
    public void testListShows() throws Exception {
        // Prepare the data expected in the response
        List<Show> shows = new ArrayList<>();
        // Add sample data to shows list
        shows.add(new Show()); // Configure the Show object as necessary

        // Mock result with a data field
        Result mockResult = new Result();
        mockResult.setData(shows); // Assuming Result has a setData method to include data

        when(showService.listAll()).thenReturn(mockResult);

        mockMvc.perform(get("/show/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists())  // Ensure the data field exists
                .andExpect(jsonPath("$.data[0]").exists()); // Check if data array has elements

        verify(showService, times(1)).listAll();
    }
    @Test
    public void testSaveShow() throws Exception {
        Show show = new Show();
        show.setPname("New Production");
        Long uid = 1L;

        when(userService.getEmailByUid(uid)).thenReturn("user@example.com");
        when(showService.save(show)).thenReturn(true);

        mockMvc.perform(post("/show/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(show))
                        .param("uid", uid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).getEmailByUid(uid);
        verify(showService).save(show);
        verify(loggingService).writeLog("user@example.com adds the new production: New Production");
    }
    @Test
    public void testModShow() throws Exception {
        Show show = new Show();
        show.setPname("Updated Production");
        Long uid = 1L;

        when(userService.getEmailByUid(uid)).thenReturn("user@example.com");
        when(showService.updateById(show)).thenReturn(true);

        mockMvc.perform(post("/show/mod")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(show))
                        .param("uid", uid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).getEmailByUid(uid);
        verify(showService).updateById(show);
        verify(loggingService).writeLog("user@example.com updates the production: Updated Production");
    }
    @Test
    public void testDeleteShow() throws Exception {
        Long pid = 1L;
        Long uid = 2L;

        when(userService.getEmailByUid(uid)).thenReturn("user@example.com");
        when(showService.getPnameByPid(pid)).thenReturn("Old Production");
        when(showService.removeById(pid)).thenReturn(true);

        mockMvc.perform(get("/show/delete")
                        .param("pid", pid.toString())
                        .param("uid", uid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).getEmailByUid(uid);
        verify(showService).removeById(pid);
        verify(loggingService).writeLog("user@example.com deletes the production: Old Production");
    }

//    @Test
//    public void testFindAllDistinctYears() throws Exception {
//        List<Integer> years = Arrays.asList(2018, 2019, 2020);
//
//        when(showService.findAllDistinctYears()).thenReturn(Result.suc(years));
//
//        mockMvc.perform(get("/show/previousShow"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.code", is(200)))  // Checks response status code in Result
//                .andExpect(jsonPath("$.msg", is("successful")))  // Checks response message in Result
//                .andExpect(jsonPath("$.data", hasSize(3)))  // Checks the size of the data array
//                .andExpect(jsonPath("$.data[0]", is(2018)))  // Verifies the first year in the data array
//                .andExpect(jsonPath("$.data[1]", is(2019)))  // Verifies the second year
//                .andExpect(jsonPath("$.data[2]", is(2020)));  // Verifies the third year
//
//        verify(showService).findAllDistinctYears();
//    }
    @Test
    public void testFindShowsByStartYear() throws Exception {
        int year = 2020;
        List<Show> shows = new ArrayList<>();
        shows.add(new Show());  // 设置Show具体属性

        when(showService.findShowsByStartYear(year)).thenReturn(Result.suc(shows));

        mockMvc.perform(get("/show/previousShow/" + year))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0]").exists());

        verify(showService).findShowsByStartYear(year);
    }
    @Test
    public void testFindShowDetailByPid() throws Exception {
        Long pid = 1L;
        Show show = new Show();  // 设置Show具体属性

        when(showService.findShowDetailByPid(pid)).thenReturn(Result.suc(show));

        mockMvc.perform(get("/show/detail/" + pid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists());

        verify(showService).findShowDetailByPid(pid);
    }

    @Test
    public void testFSearchWithKeyword() throws Exception {
        String kw = "Hamlet";
        List<Show> shows = new ArrayList<>();
        shows.add(new Show());  // 设置Show具体属性

        when(showService.list(any())).thenReturn(shows);

        mockMvc.perform(get("/show/fSearch")
                        .param("kw", kw))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0]").exists());

        verify(showService).list(any());
    }

    @Test
    public void testFSearchWithYear() throws Exception {
        Integer year = 2020;
        List<Show> shows = new ArrayList<>();
        shows.add(new Show());  // 设置Show具体属性

        when(showService.list(any())).thenReturn(shows);

        mockMvc.perform(get("/show/fSearch")
                        .param("year", year.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0]").exists());

        verify(showService).list(any());
    }

    @Test
    public void testFSearchWithBoth() throws Exception {
        String kw = "Hamlet";
        Integer year = 2020;
        List<Show> shows = new ArrayList<>();
        shows.add(new Show());  // 设置Show具体属性

        when(showService.list(any())).thenReturn(shows);

        mockMvc.perform(get("/show/fSearch")
                        .param("kw", kw)
                        .param("year", year.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0]").exists());

        verify(showService).list(any());
    }

}
