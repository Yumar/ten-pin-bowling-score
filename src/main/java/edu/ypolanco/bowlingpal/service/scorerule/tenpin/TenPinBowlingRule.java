/*
 * Copyright 2019 Yumarx <jumarpolanco@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.ypolanco.bowlingpal.service.scorerule.tenpin;

import edu.ypolanco.bowlingpal.model.Frame;
import edu.ypolanco.bowlingpal.model.Lane;
import edu.ypolanco.bowlingpal.service.scorerule.BowlingScoreRule;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.calculator.DefaultScoreCalculator;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.calculator.OpenScoreCalculator;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.calculator.ScoreCalculator;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.calculator.SpareScoreCalculator;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.calculator.StrikeScoreCalculator;
import static edu.ypolanco.bowlingpal.util.BowlingUtil.getShootPoints;
import edu.ypolanco.bowlingpal.util.exception.InvalidScoreException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class TenPinBowlingRule implements BowlingScoreRule {
    public static final int NUMBER_OF_PINS = 10;

    @Override
    public int getNumberOfPins() {
        return NUMBER_OF_PINS;
    }

    @Override
    public List<Lane> applyRule(Map<String, List<String>> score) throws InvalidScoreException{
        List<Lane> lanes = new ArrayList<>();

        score.forEach((k, v) -> {
            Lane lane = new Lane();
            lane.setPlayerName(k);
            lane.setFrames(calculateTotals(calculateShoots(v)));
        });

        return lanes;
    }

    private List<Frame> calculateShoots(List<String> stringList) throws InvalidScoreException {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i = i + 2) {
            Frame frame = new Frame();
            List<String> shoots = new ArrayList<>();
            if (i == stringList.size() - 3 && stringList.get(i).equals(getNumberOfPins())) { //verifies if is the last frame
                if (validateShoot(stringList.get(i)) && validateShoot(stringList.get(i + 1)) && validateShoot(stringList.get(i + 2))) {
                    shoots.add(stringList.get(i));
                    shoots.add(stringList.get(i + 1));
                    shoots.add(stringList.get(i + 2));
                    frame.setShoots(shoots);
                }
            }
            if (validateShoot(stringList.get(i)) && validateShoot(stringList.get(i + 1))) {
                shoots.add(stringList.get(i));
                shoots.add(stringList.get(i + 1));
                frame.setShoots(shoots);
            }
        }
        return frames;
    }

    private boolean validateShoot(String shoot) throws InvalidScoreException {
        boolean isValid = shoot.equalsIgnoreCase("F") || Integer.parseInt(shoot) <= getNumberOfPins();

        if (!isValid) {
            throw new InvalidScoreException("Score out of range");
        }

        return true;
    }

    private List<Frame> calculateTotals(List<Frame> frames) {
        for (int i = 0; i < frames.size(); i++) {
            ScoreCalculator calculator = null;
            int thisShoot = getShootPoints(frames.get(i).getShoots().get(0));
            int secondShoot = getShootPoints(frames.get(i).getShoots().get(1));
            if (thisShoot == getNumberOfPins()) {
                frames.get(i).setFrameType(Frame.Type.STRIKE);
                calculator = new StrikeScoreCalculator();
            } else if ((thisShoot + secondShoot) == getNumberOfPins()) {
                frames.get(i).setFrameType(Frame.Type.SPARE);
                calculator = new SpareScoreCalculator();
            } else if(secondShoot == 0){
                frames.get(i).setFrameType(Frame.Type.OPEN);
                calculator = new OpenScoreCalculator();
            } else {
                calculator = new DefaultScoreCalculator();
            }
            frames.get(i).setTotalScore(calculator.calculate(i, frames));
        }
        return frames;
    }

    

}
