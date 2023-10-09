package com.example.testtask.service;

import com.example.testtask.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SymbolFrequencyService {

    private final Logger logger = LoggerFactory.getLogger(SymbolFrequencyService.class);
     public Map<Character, Integer> getfrequency(String input){
         validateInput(input);
         Map<Character, Integer> frequencyMap = new HashMap<>();

         for (char c : input.toCharArray()) {
             frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
         }

         return frequencyMap.entrySet()
                 .stream()
                 .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                 .collect(Collectors.toMap(
                         Map.Entry::getKey,
                         Map.Entry::getValue,
                         (e1, e2) -> e1,
                         LinkedHashMap::new
                 ));
     }

    private void validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            logger.error("Input string seems to be empty");
            throw new InvalidInputException("Input should not be empty.");
        }

        if (!input.matches("^[A-Za-z]+$")) {
            logger.error("Input string incorrect format");
            throw new InvalidInputException("Input should contain only Latin alphabet symbols.");
        }
    }
}
