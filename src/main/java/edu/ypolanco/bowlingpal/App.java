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
package edu.ypolanco.bowlingpal;

import edu.ypolanco.bowlingpal.model.Frame;
import edu.ypolanco.bowlingpal.model.Lane;
import edu.ypolanco.bowlingpal.service.scoreparser.ScoreParser;
import edu.ypolanco.bowlingpal.service.scoreparser.ScoreParserFactory;
import edu.ypolanco.bowlingpal.service.scoreparser.file.FileScoreParserImpl;
import edu.ypolanco.bowlingpal.service.scorerule.BowlingScoreRule;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.TenPinBowlingRule;
import edu.ypolanco.bowlingpal.ui.BowlingUI;
import edu.ypolanco.bowlingpal.ui.cli.BowlingCLI;
import edu.ypolanco.bowlingpal.util.exception.InvalidScoreException;
import edu.ypolanco.bowlingpal.util.exception.NoParserFoundException;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BowlingUI ui = new BowlingCLI();
        ScoreParser scoreParser = new FileScoreParserImpl();
        BowlingScoreRule rule = new TenPinBowlingRule();

        try {
            ui.init(scoreParser, rule);
            ui.displayScore();
        } catch(RuntimeException | InvalidScoreException ex){
            System.out.println("An error occurred during application execurion: "+ ex.getMessage());
            ex.printStackTrace();
        }
    }

}
