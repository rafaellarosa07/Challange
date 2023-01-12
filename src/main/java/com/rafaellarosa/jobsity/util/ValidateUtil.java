package com.rafaellarosa.jobsity.util;


import org.springframework.stereotype.Component;

@Component
public class ValidateUtil {

    public static void validatePinsNumbers(String value) {
        if (value.equals("F")) {
            return;
        }
        if(!value.matches("[F]*[0-9]*")){
            throw new RuntimeException("PARSE NUMBER EXECEPTION!");
        }
        else if (Integer.parseInt(value) > 10 || Integer.parseInt(value) < 0) {
            throw new RuntimeException("PARSE NUMBER EXECEPTION!");
        }
    }
}
