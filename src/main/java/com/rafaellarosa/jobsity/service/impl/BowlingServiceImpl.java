package com.rafaellarosa.jobsity.service.impl;

import com.rafaellarosa.jobsity.dto.Frame;
import com.rafaellarosa.jobsity.dto.GamePlayer;
import com.rafaellarosa.jobsity.dto.Round;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BowlingServiceImpl {

    public GamePlayer fillGamePlayers(String[] values, List<GamePlayer> gamePlayers) {

        var gamePlayer = gamePlayers.stream()
                .filter(gamer -> gamer.getPlayerName()
                        .equals(values[0])).findAny()
                .orElseGet(() -> {
                    GamePlayer newGamePlayer = new GamePlayer(values[0], new ArrayList<>());
                    gamePlayers.add(newGamePlayer);
                    return newGamePlayer;
                });

        fillFrame(gamePlayer, values[1]);
        if (gamePlayer.getFrames().size() >= 10) {
            if(gamePlayer.getFrames().get(9).getRounds().size() >= 3){
                calculateScores(gamePlayer.getFrames());
            }
        }
        return gamePlayer;
    }


    private void fillFrame(GamePlayer gamePlayer, String value) {
        verifyFrame(gamePlayer.getFrames(), value);
        verifyLastRound(gamePlayer.getFrames());
    }


    private void verifyFrame(List<Frame> frames, String value) {
        if (frames.get(frames.size() - 1).getRounds().size() == 2) {
            frames.add(new Frame(frames.size() + 1, 0, new ArrayList<>(), false, false));
        }
        fillRounds(frames, value);
    }

    private void fillRounds(List<Frame> frames, String value) {
        frames.stream().forEach(frame -> {
            if (frame.getRounds().size() < 2 && !frame.getIsStrike()) {
                if (frame.getRounds().size() == 0 && value.equals("10") && frame.getNumber() != 10) {
                    frame.getRounds().add(new Round(0));
                    frame.setIsStrike(true);
                }
                frame.getRounds().add(value.equals("F") ? new Round(0, true) :
                        new Round(Integer.parseInt(value)));

            } else {
                setSpare(frame);
            }
        });
    }

    private void setSpare(Frame frame) {
        var count = 0;
        count = frame.getRounds().stream().mapToInt(Round::getValuePinkFalls).sum();
        if (!frame.getIsStrike() && count == 10) {
            frame.setIsSpare(true);
        }
    }

    private void verifyLastRound(List<Frame> frames) {
        List<Round> rounds = new ArrayList<>();
        if (frames.size() > 10) {
            frames.stream().filter(frame -> frame.getNumber() > 10).forEach(frame -> {
                frame.getRounds().forEach(round -> {
                    rounds.add(round);
                });
            });
            frames.get(9).getRounds().addAll(rounds);
            frames.get(9).setIsStrike(false);
            frames.get(9).setIsSpare(false);
            frames.removeIf(frame -> frame.getNumber() > 10);
        }
    }


    private void calculateScores(List<Frame> frames) {
        Integer score = 0;
        for (int i = 0; i < frames.size(); i++) {
            var nextValue = i + 1;
            if (frames.get(i).getIsStrike()) {
                score += 10;
                if (((frames.size() - 1) != i)) {
                    score = verifyStrike(nextValue, frames, score, i);
                    if (frames.get(nextValue).getIsSpare()) {
                        score += 10;
                    } else if (nextValue == frames.size() - 1) {
                        score = verifyLastFrameTen(nextValue, frames, score, 0);
                    }
                }
            } else if (frames.get(i).getIsSpare()) {
                score += frames.get(i).getRounds().stream().mapToInt(Round::getValuePinkFalls).sum();
                score += frames.get(nextValue).getRounds().get(0).getValuePinkFalls();
            } else {
                score += frames.get(i).getRounds().stream().mapToInt(Round::getValuePinkFalls).sum();
            }
            frames.get(i).setScore(score);
        }
    }

    private Integer verifyStrike(int nextValue, List<Frame> frames, Integer score, int current) {
        if (frames.get(nextValue).getIsStrike()) {
            score += 10;
            System.out.println(nextValue);
            verifyStrike(nextValue+1, frames, score, nextValue);
        }
        if ((frames.size() - 1) == nextValue
                && frames.get(frames.size() - 1).getRounds().get(0).getValuePinkFalls().equals(10)
                && (frames.size() - 2) != current) {
            score += 10;
        }
        score += frames.get(nextValue).getRounds().stream().mapToInt(Round::getValuePinkFalls).sum();
        return score;
    }

    private Integer verifyLastFrameTen(int nextValue, List<Frame> frames, Integer score, int value) {
        if (frames.get(nextValue).getRounds().size() > 1) {
            if (frames.get(nextValue).getRounds().get(value).getValuePinkFalls().equals(10)) {
                score += 10;
                score += frames.get(nextValue).getRounds().get(1).getValuePinkFalls();
            }
        }
        return score;
    }


}
