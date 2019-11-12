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
package edu.ypolanco.bowlingpal.service.util;

import edu.ypolanco.bowlingpal.model.Frame;
import edu.ypolanco.bowlingpal.model.Lane;
import edu.ypolanco.bowlingpal.service.scoreparser.file.FileScoreParserImplTest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class BowlingTestUtil {

    public static void writeValidFile(File file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("Jeff 10\n"
                    + "John 10\n"
                    + "Jeff 7\n"
                    + "Jeff 3\n"
                    + "John 7\n"
                    + "John 3\n"
                    + "Jeff 9\n"
                    + "Jeff 0\n"
                    + "John 9\n"
                    + "John 0\n"
                    + "Jeff 10\n"
                    + "John 10\n"
                    + "Jeff 0\n"
                    + "Jeff 8\n"
                    + "John 0\n"
                    + "John 8\n"
                    + "Jeff 8\n"
                    + "Jeff 2\n"
                    + "John 8\n"
                    + "John 2\n"
                    + "Jeff F\n"
                    + "Jeff 6\n"
                    + "John F\n"
                    + "John 6\n"
                    + "Jeff 10\n"
                    + "John 10\n"
                    + "Jeff 10\n"
                    + "John 10\n"
                    + "Jeff 10\n"
                    + "Jeff 8\n"
                    + "Jeff 1\n"
                    + "John 10\n"
                    + "John 8\n"
                    + "John 1");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileScoreParserImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileScoreParserImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Map<String, List<String>> getValidScoreMap() {
        Map<String, List<String>> expecMap = new HashMap<>();
        List<String> jeffScore = Arrays.asList(new String[]{"10", "7", "3", "9", "0", "10", "0", "8", "8", "2", "F", "6", "10", "10", "10", "8", "1"});
        expecMap.put("Jeff", jeffScore);
        List<String> johnScore = Arrays.asList(new String[]{"10", "7", "3", "9", "0", "10", "0", "8", "8", "2", "F", "6", "10", "10", "10", "8", "1"});
        expecMap.put("John", johnScore);

        return expecMap;
    }

    public static List<Lane> getValidLanes() {
        List<Lane> testLanes = new ArrayList<>();
        
        Lane lane1 = new Lane();
        lane1.setPlayerName("Jeff");
        
        Lane lane2 = new Lane();
        lane2.setPlayerName("John");        
        
        List<Frame> frames = new ArrayList<>();

        Frame frame1 = new Frame();
        frame1.setFrameType(Frame.Type.STRIKE);
        frame1.setShoots(Arrays.asList(new String[]{"10"}));
        frame1.setTotalScore(20);
        frames.add(frame1);
        
        Frame frame2 = new Frame();
        frame2.setFrameType(Frame.Type.SPARE);
        frame2.setShoots(Arrays.asList(new String[]{"7","3"}));
        frame2.setTotalScore(39);
        frames.add(frame2);
        
        Frame frame3 = new Frame();
        frame3.setFrameType(Frame.Type.OPEN);
        frame3.setShoots(Arrays.asList(new String[]{"9","0"}));
        frame3.setTotalScore(48);
        frames.add(frame3);
        
        Frame frame4 = new Frame();
        frame4.setFrameType(Frame.Type.STRIKE);
        frame4.setShoots(Arrays.asList(new String[]{"10"}));
        frame4.setTotalScore(66);
        frames.add(frame4);
        
        Frame frame5 = new Frame();
        frame5.setFrameType(Frame.Type.DEFAULT);
        frame5.setShoots(Arrays.asList(new String[]{"0","8"}));
        frame5.setTotalScore(74);
        frames.add(frame5);
        
        Frame frame6 = new Frame();
        frame6.setFrameType(Frame.Type.SPARE);
        frame6.setShoots(Arrays.asList(new String[]{"8","2"}));
        frame6.setTotalScore(84);
        frames.add(frame6);
        
        Frame frame7 = new Frame();
        frame7.setFrameType(Frame.Type.FOUL);
        frame7.setShoots(Arrays.asList(new String[]{"F","6"}));
        frame7.setTotalScore(90);
        frames.add(frame7);
        
        Frame frame8 = new Frame();
        frame8.setFrameType(Frame.Type.STRIKE);
        frame8.setShoots(Arrays.asList(new String[]{"10"}));
        frame8.setTotalScore(120);
        frames.add(frame8);
        
        Frame frame9 = new Frame();
        frame9.setFrameType(Frame.Type.STRIKE);
        frame9.setShoots(Arrays.asList(new String[]{"10"}));
        frame9.setTotalScore(148);
        frames.add(frame9);
        
        Frame frame10 = new Frame();
        frame10.setFrameType(Frame.Type.STRIKE);
        frame10.setShoots(Arrays.asList(new String[]{"10","8","1"}));
        frame10.setTotalScore(167);
        frames.add(frame10);
        
        lane1.setFrames(frames);
        lane2.setFrames(frames);        
        testLanes.add(lane1);
        testLanes.add(lane2);
        return testLanes;
    }

}
