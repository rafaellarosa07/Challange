package com.rafaellarosa.jobsity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayer {

    private String playerName;

    private List<Frame> frames;



    public GamePlayer(String playerName){
        this.playerName = playerName;
    }

    public List<Frame> getFrames() {
        if(ObjectUtils.isEmpty(this.frames)){
            frames = new ArrayList<>();
            frames.add(new Frame(1, 0, new ArrayList<>(), false, false));
        }
        return frames;
    }

}
