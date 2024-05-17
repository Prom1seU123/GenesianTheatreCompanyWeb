package com.au.GenesianTheatreCompany.ControllerTest;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.Controller.PageController;
import com.au.GenesianTheatreCompany.entity.Pages;
import com.au.GenesianTheatreCompany.service.LoggingService;
import com.au.GenesianTheatreCompany.service.PageService;
import com.au.GenesianTheatreCompany.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
public class PageControllerTest {

    @Mock
    private PageService pageService;

    @Mock
    private UserService userService;

    @Mock
    private LoggingService loggingService;

    @InjectMocks
    private PageController pageController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        // Arrange
        Result expected = Result.suc(List.of(new Pages(), new Pages()));
        when(pageService.listAllPages()).thenReturn(expected);

        // Act
        Result result = pageController.list();

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
        verify(pageService).listAllPages();
    }

    @Test
    public void testGetPageById() {
        // Arrange
        Long pgid = 1L;
        Result expected = Result.suc(new Pages());
        when(pageService.getPageById(pgid)).thenReturn(expected);

        // Act
        Result result = pageController.getPageById(pgid);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
        verify(pageService).getPageById(pgid);
    }

    @Test
    public void testMod() {
        // Arrange
        Pages pages = new Pages();
        pages.setPgid(1L);
        Long adminUid = 2L;
        String adminEmail = "admin@example.com";
        when(userService.getEmailByUid(adminUid)).thenReturn(adminEmail);
        when(pageService.updateById(pages)).thenReturn(true);
        String logMessage = String.format("%s modifies the page id: %s", adminEmail, pages.getPgid());

        // Act
        Result result = pageController.mod(pages, adminUid);

        // Assert
        assertEquals(Result.suc(true), result);
        verify(userService).getEmailByUid(adminUid);
        verify(loggingService).writeLog(logMessage);
        verify(pageService).updateById(pages);
    }
}
