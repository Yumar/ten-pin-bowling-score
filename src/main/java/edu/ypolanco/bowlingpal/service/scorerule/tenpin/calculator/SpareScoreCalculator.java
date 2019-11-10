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
package edu.ypolanco.bowlingpal.service.scorerule.tenpin.calculator;

import edu.ypolanco.bowlingpal.model.Frame;
import static edu.ypolanco.bowlingpal.util.BowlingUtil.getShootPoints;
import java.util.List;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class SpareScoreCalculator implements ScoreCalculator{

    @Override
    public int calculate(int index, List<Frame> frames) {
        int thisShoot = getShootPoints(frames.get(index).getShoots().get(0));
        int secondShoot = getShootPoints(frames.get(index).getShoots().get(1));
        int thirdShoot = getShootPoints(frames.get(index + 1).getShoots().get(0));
        return thisShoot + secondShoot + thirdShoot;
    }
    
}
