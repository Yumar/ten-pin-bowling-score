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
import edu.ypolanco.bowlingpal.service.util.BowlingTestUtil;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class TenPinBowlingRuleTest {

    public TenPinBowlingRuleTest() {
    }

    @Test
    public void testApplyRule() throws Exception {
        System.out.println("Test valid score TenPinBowlingRule.applyRules");
        Map<String, List<String>> score = BowlingTestUtil.getValidScoreMap();
        TenPinBowlingRule instance = new TenPinBowlingRule();
        List<Lane> expResult = BowlingTestUtil.getValidLanes();
        List<Lane> result = instance.applyRule(score);
        assertEquals(expResult.size(), result.size());
        
        StringBuilder expBuilder = new StringBuilder();
        for (Lane lane : expResult) {
            expBuilder.append(lane.getPlayerName());
            System.out.println(lane.getPlayerName());
            for (Frame frame : lane.getFrames()) {
                System.out.println("Frame");                
                expBuilder.append("Frame");
                for (String shoot : frame.getShoots()) {
                    System.out.print(shoot + "|");
                    expBuilder.append(shoot + "|");

                }
                System.out.println(frame.getTotalScore());
                expBuilder.append(frame.getTotalScore());

            }
        }
        
        StringBuilder resBuilder = new StringBuilder();
        for (Lane lane : result) {
            resBuilder.append(lane.getPlayerName());
            System.out.println(lane.getPlayerName());
            for (Frame frame : lane.getFrames()) {
                System.out.println("Frame");                
                resBuilder.append("Frame");
                for (String shoot : frame.getShoots()) {
                    System.out.print(shoot + "|");
                    resBuilder.append(shoot + "|");

                }
                System.out.println(frame.getTotalScore());
                resBuilder.append(frame.getTotalScore());

            }
        }
        
        assertEquals(expBuilder.toString(), resBuilder.toString());
    }

}
