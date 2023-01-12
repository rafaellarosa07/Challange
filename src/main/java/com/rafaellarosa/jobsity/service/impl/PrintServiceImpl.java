package com.rafaellarosa.jobsity.service.impl;

import com.rafaellarosa.jobsity.dto.GamePlayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintServiceImpl {


    private static String TAB = "\t";


    public String getResultBowling(List<GamePlayer> gamePlayerList) {
        StringBuilder stringBuilder = fillHeader();

        gamePlayerList.stream().forEach(gamePlayer -> {
            stringBuilder.append(gamePlayer.getPlayerName()).append("\n");
            stringBuilder.append("Pinfalls" + TAB);

            gamePlayer.getFrames().stream().forEach(frame -> {
                if (frame.getIsStrike() && frame.getNumber() != 10) {
                    getIsStrike(stringBuilder);
                } else if (frame.getIsSpare() && frame.getNumber() != 10) {
                    stringBuilder.append(frame.getRounds().get(0).getValuePinkFalls());
                    getIsSpare(stringBuilder);
                } else {
                    frame.getRounds().forEach(round -> {
                        if (round.getIsFoul()) {
                            stringBuilder.append("F");
                        } else {
                            if (frame.getNumber().equals(10) && round.getValuePinkFalls().equals(10)) {
                                stringBuilder.append("X");
                            } else {
                                stringBuilder.append(round.getValuePinkFalls());
                            }
                        }
                        stringBuilder.append(TAB);
                    });

                }
            });
            getScore(stringBuilder);
            gamePlayer.getFrames().forEach(frame -> {
                stringBuilder.append(frame.getScore()).append(TAB).append(TAB);
            });
            stringBuilder.append("\n");
        });
        stringBuilder.append("\n");

        return stringBuilder.toString();

    }


    private StringBuilder fillHeader() {
        StringBuilder stringBuilder = new StringBuilder("Frame");
        stringBuilder.append("\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n");
        return stringBuilder;
    }


    private StringBuilder getScore(StringBuilder stringBuilder) {
        return stringBuilder.append("\nScore\t\t");
    }

    private StringBuilder getIsStrike(StringBuilder stringBuilder) {
        stringBuilder.append(TAB);
        stringBuilder.append("X");
        stringBuilder.append(TAB);
        return stringBuilder;
    }

    private StringBuilder getIsSpare(StringBuilder stringBuilder) {
        stringBuilder.append(TAB);
        stringBuilder.append("/");
        stringBuilder.append(TAB);
        return stringBuilder;
    }


}
