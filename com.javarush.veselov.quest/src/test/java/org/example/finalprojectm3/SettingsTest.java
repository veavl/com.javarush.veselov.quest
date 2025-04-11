package org.example.finalprojectm3;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SettingsTest {

    @Test
    void getParameterCheckException() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        assertThrows(IllegalArgumentException.class,
                () -> Settings.getParameter(request, null)
        );
    }

    @Test
    void getParameterCheckExceptionText() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> Settings.getParameter(request, null)
        );
        assertEquals("Name cannot be null", exception.getMessage());
    }
}