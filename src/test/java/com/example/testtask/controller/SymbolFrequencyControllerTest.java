package com.example.testtask.controller;

import com.example.testtask.exception.InvalidInputException;
import com.example.testtask.service.SymbolFrequencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(SymbolFrequencyController.class)
@ExtendWith(MockitoExtension.class)
public class SymbolFrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SymbolFrequencyService symbolFrequencyService;

    @Test
    public void testCalcFrequency() throws Exception {

        String input = "abc";
        Map<Character, Integer> mockResult = new HashMap<>();
        mockResult.put('a', 1);
        mockResult.put('b', 1);
        mockResult.put('c', 1);

        when(symbolFrequencyService.getfrequency(anyString())).thenReturn(mockResult);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/frequency")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalcFrequencyInvalidInput() throws Exception {


        String input = "abc123";

        when(symbolFrequencyService.getfrequency(input))
                .thenThrow(new InvalidInputException("Input should contain only Latin alphabet symbols."));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/frequency")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalcFrequencyEmptyInput() throws Exception {

        when(symbolFrequencyService.getfrequency(anyString()))
                .thenThrow(new InvalidInputException("Input should not be empty."));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/frequency")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("")).andExpect(status().isBadRequest());

    }
}


