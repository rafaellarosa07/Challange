package com.rafaellarosa.jobsity;

import com.rafaellarosa.jobsity.dto.GamePlayer;
import com.rafaellarosa.jobsity.service.ReadFileService;
import com.rafaellarosa.jobsity.service.impl.PrintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaChallengeApplication implements CommandLineRunner {

    @Autowired
    private ReadFileService readFileService;

    @Autowired
    private PrintServiceImpl printService;

    public static void main(String[] args) {
        SpringApplication.run(JavaChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String filePath = Arrays.stream(args).collect(Collectors.joining("-"));
        List<GamePlayer> gamePlayers = readFileService.readFile(filePath);
        System.out.print(printService.getResultBowling(gamePlayers));
    }
}