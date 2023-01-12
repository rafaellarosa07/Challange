package com.rafaellarosa.jobsity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Frame {

    private Integer number;
    private Integer score;
    private List<Round> rounds;

    private Boolean isStrike;

    private Boolean isSpare;

}
