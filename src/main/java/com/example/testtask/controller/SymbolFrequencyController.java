package com.example.testtask.controller;

import com.example.testtask.service.SymbolFrequencyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SymbolFrequencyController {

    private final SymbolFrequencyService service;

    private final Logger logger = LoggerFactory.getLogger(SymbolFrequencyController.class);

    @PostMapping("/frequency")
    public Map<Character, Integer> calcFrequency(@RequestBody String input) {
        logger.info("Calculate frequency POST called.");
        return service.getfrequency(input);
    }





}
