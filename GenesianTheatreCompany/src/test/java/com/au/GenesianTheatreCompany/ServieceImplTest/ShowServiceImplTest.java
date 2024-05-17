package com.au.GenesianTheatreCompany.ServieceImplTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.au.GenesianTheatreCompany.service.impl.ShowServiceImpl;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.mapper.ShowMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ShowServiceImplTest {

    @Mock
    private ShowMapper showMapper;

    @InjectMocks
    private ShowServiceImpl showService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAll() {
        // Arrange
        List<Show> expectedShows = Arrays.asList(new Show(), new Show());
        when(showMapper.listAll()).thenReturn(expectedShows);

        // Act
        Result result = showService.listAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(expectedShows, result.getData());
        verify(showMapper).listAll();
    }

    @Test
    public void testFindShowsByStartYear() {
        // Arrange
        int year = 2020;
        List<Show> expectedShows = Arrays.asList(new Show(), new Show());
        when(showMapper.findShowsByStartYear(year)).thenReturn(expectedShows);

        // Act
        Result result = showService.findShowsByStartYear(year);

        // Assert
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(expectedShows, result.getData());
        verify(showMapper).findShowsByStartYear(year);
    }

    @Test
    public void testFindAllDistinctYears() {
        // Arrange
        List<Integer> expectedYears = Arrays.asList(2019, 2020, 2021);
        when(showMapper.findAllDistinctYears()).thenReturn(expectedYears);

        // Act
        Result result = showService.findAllDistinctYears();

        // Assert
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(expectedYears, result.getData());
        verify(showMapper).findAllDistinctYears();
    }

    @Test
    public void testFindShowDetailByPid() {
        // Arrange
        Long pid = 1L;
        Show expectedShow = new Show();
        when(showMapper.findShowDetailByPid(pid)).thenReturn(expectedShow);

        // Act
        Result result = showService.findShowDetailByPid(pid);

        // Assert
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(expectedShow, result.getData());
        verify(showMapper).findShowDetailByPid(pid);
    }

    @Test
    public void testGetPnameByPid() {
        // Arrange
        Long pid = 1L;
        String expectedPname = "Hamlet";
        when(showMapper.getPnameByPid(pid)).thenReturn(expectedPname);

        // Act
        String actualPname = showService.getPnameByPid(pid);

        // Assert
        assertEquals(expectedPname, actualPname);
        verify(showMapper).getPnameByPid(pid);
    }
}
