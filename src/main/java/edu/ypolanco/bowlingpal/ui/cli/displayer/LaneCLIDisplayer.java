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
package edu.ypolanco.bowlingpal.ui.cli.displayer;

import edu.ypolanco.bowlingpal.model.Frame;
import edu.ypolanco.bowlingpal.model.Lane;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class LaneCLIDisplayer implements CLIDisplayer<List<Lane>> {

    @Override
    public void display(List<Lane> information) {
        int maxFrames = getMaxFrames(information);
        List<StringBuilder> stringLines = new ArrayList<>();
        
        StringBuilder titleBuilder = new StringBuilder("Frame");
        titleBuilder.append("\t");
        for (int i = 1; i <= maxFrames; i++) {
            titleBuilder.append("\t");
            titleBuilder.append(i);
        }
        stringLines.add(titleBuilder);
        
        for (Lane lane : information) {
            stringLines.add(new StringBuilder(lane.getPlayerName()));
            StringBuilder pinfallsLine = new StringBuilder("Pinfalls\t");
            StringBuilder scoreLine = new StringBuilder("Score\t");
            int laneSize = lane.getFrames().size();
            for (int i = 0; i < laneSize; i++) {
                Frame frame = lane.getFrames().get(i);
                
                switch(frame.getFrameType()){
                    case OPEN:
                        pinfallsLine.append(frame.getShoots().get(0))
                                .append(" -");
                        break;
                    case SPARE:
                        pinfallsLine.append(frame.getShoots().get(0))
                                .append(" /");
                        break;
                    case STRIKE:
                        if(i == laneSize-1){
                            pinfallsLine.append("X ")
                                    .append(maskStrike(frame.getShoots().get(1)))
                                    .append(" ")
                                    .append(maskStrike(frame.getShoots().get(2)))
                                    .append(" ");
                        } else {
                            pinfallsLine.append("X  ");
                        }                        
                        break;
                    case FOUL:
                        pinfallsLine.append("F  ");
                        break;
                    default:
                        pinfallsLine.append(frame.getShoots().get(0))
                                .append(" ")
                                .append(frame.getShoots().get(1));
                }
                pinfallsLine.append("\t");
                scoreLine.append("\t");
                scoreLine.append(frame.getTotalScore());
            }
            stringLines.add(pinfallsLine);
            stringLines.add(scoreLine);
            
        }        
        stringLines.forEach(System.out::println);
    }

    private int getMaxFrames(List<Lane> lanes) {
        return lanes.stream()
                .mapToInt(v -> v.getFrames().size())
                .max().orElseThrow(NoSuchElementException::new);
    }
    
    private String maskStrike(String score){
        return score.equals("10")? "X " : score.toString();
    }

}
