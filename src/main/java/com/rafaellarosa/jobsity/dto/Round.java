package com.rafaellarosa.jobsity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    private Integer valuePinkFalls;

    private Boolean isFoul;

    public Round(Integer valuePinkFalls){
        this.valuePinkFalls = valuePinkFalls;
        this.isFoul = false;
    }


}
