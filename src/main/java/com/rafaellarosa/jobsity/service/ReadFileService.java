package com.rafaellarosa.jobsity.service;

import com.rafaellarosa.jobsity.dto.GamePlayer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReadFileService {
    public List<GamePlayer> readFile(String fileName);
}
