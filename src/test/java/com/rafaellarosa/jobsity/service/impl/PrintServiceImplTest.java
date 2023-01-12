package com.rafaellarosa.jobsity.service.impl;

import com.rafaellarosa.jobsity.dto.GamePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {

    @InjectMocks
    private PrintServiceImpl printService;

    private GamePlayer gamePlayer;



    @BeforeEach
    void setUp() {
        initialize();
    }

    @Test
    void getResultBowling() {
    }



    private void initialize(){
        gamePlayer = new GamePlayer();

    }
}