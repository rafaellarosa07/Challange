package com.rafaellarosa.jobsity.service.impl;

import com.google.gson.Gson;
import com.rafaellarosa.jobsity.dto.GamePlayer;
import com.rafaellarosa.jobsity.service.ReadFileService;
import com.rafaellarosa.jobsity.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ReadFileServiceImpl implements ReadFileService {


    @Autowired
    public BowlingServiceImpl bowlingService;

    public List<GamePlayer> readFile(String fileName) {
        List<GamePlayer> gamePlayerList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                String[] values = line.split("\\t");
                ValidateUtil.validatePinsNumbers(values[1]);
                bowlingService.fillGamePlayers(values, gamePlayerList);
            });
            return gamePlayerList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
