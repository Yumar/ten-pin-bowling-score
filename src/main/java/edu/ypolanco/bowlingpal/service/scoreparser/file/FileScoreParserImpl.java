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
package edu.ypolanco.bowlingpal.service.scoreparser.file;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import edu.ypolanco.bowlingpal.util.exception.InvalidScoreException;
import edu.ypolanco.bowlingpal.util.BowlingUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
@AllArgsConstructor
public class FileScoreParserImpl implements FileScoreParser {

    private File source;

    @Override
    public Map<String, List<String>> parseScore(File source) throws InvalidScoreException {
        setSource(source);
        return paseScore();
    }

    @Override
    public void setSource(File source) {
        this.source = source;
    }

    @Override
    public Map<String, List<String>> paseScore() throws InvalidScoreException{
        Map<String, List<String>> score = null;
        try (BufferedReader reader
                = new BufferedReader(new FileReader(source))) {
            score = readLines(reader);
        } catch (IOException ex) {
            Logger.getLogger(FileScoreParserImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new InvalidScoreException("unable to access source file");
        } 
        return score;
    }

    @Override
    public Map<String, List<String>> readLines(BufferedReader reader) throws IOException, InvalidScoreException {
        String line;
        Map<String, List<String>> result = new HashMap<>();
        
        while ((line = reader.readLine()) != null) {
            String[] lineArr = line.split(" ");
            if(!BowlingUtil.isValidScore(lineArr[1])) throw new InvalidScoreException("Score format is invalid");
            
            List<String> scoreList = result.get(lineArr[0]);            
            if(scoreList!=null){
                scoreList.add(lineArr[1]);
            }else{
                scoreList = new ArrayList<>();
                scoreList.add(lineArr[1]);
                result.put(lineArr[0], scoreList);
            }
        }
        
        return result;
    }

}
