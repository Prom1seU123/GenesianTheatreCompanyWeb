package com.au.GenesianTheatreCompany.ServieceImplTest;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Pages;
import com.au.GenesianTheatreCompany.mapper.PageMapper;
import com.au.GenesianTheatreCompany.service.impl.PageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PageServiceImplTest {

    @Mock
    private PageMapper pageMapper;

    @InjectMocks
    private PageServiceImpl pageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAllPages() {
        // Arrange
        Pages page1 = new Pages();
        page1.setPgid(1L);
        Pages page2 = new Pages();
        page2.setPgid(2L);
        List<Pages> pages = Arrays.asList(page1, page2);

        when(pageMapper.listAll()).thenReturn(pages);

        // Act
        Result result = pageService.listAllPages();

        // Assert
        assertEquals(Result.suc(pages), result);
    }

    @Test
    void testGetPageById() {
        // Arrange
        Long pgid = 1L;
        Pages page = new Pages();
        page.setPgid(pgid);

        when(pageMapper.getPageById(pgid)).thenReturn(page);

        // Act
        Result result = pageService.getPageById(pgid);

        // Assert
        assertEquals(Result.suc(page), result);
    }
}
