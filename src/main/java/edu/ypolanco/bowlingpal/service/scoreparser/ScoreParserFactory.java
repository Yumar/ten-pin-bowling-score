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
package edu.ypolanco.bowlingpal.service.scoreparser;

import edu.ypolanco.bowlingpal.service.scoreparser.file.FileScoreParser;
import edu.ypolanco.bowlingpal.service.scoreparser.file.FileScoreParserImpl;
import edu.ypolanco.bowlingpal.service.scoreparser.string.StringScoreParser;
import edu.ypolanco.bowlingpal.util.exception.NoParserFoundException;
import edu.ypolanco.bowlingpal.service.scoreparser.string.StringScoreParserImpl;
import java.io.File;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class ScoreParserFactory {
    
    public ScoreParser createScoreParser(Object source) throws NoParserFoundException{
        if(source instanceof String){
            return createSringScoreParser((String) source);
        } else if(source instanceof File){
            return createFileScoreParser((File) source);
        } else{
            throw new NoParserFoundException();
        }
    }
    
    private StringScoreParser createSringScoreParser(String source){
        return new StringScoreParserImpl(source);
    }
    
    private FileScoreParser createFileScoreParser(File source){
        return new FileScoreParserImpl(source);
    }
}
