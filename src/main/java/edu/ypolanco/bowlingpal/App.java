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

import edu.ypolanco.bowlingpal.model.Lane;
import edu.ypolanco.bowlingpal.service.scoreparser.ScoreParser;
import edu.ypolanco.bowlingpal.service.scoreparser.ScoreParserFactory;
import edu.ypolanco.bowlingpal.service.scorerule.BowlingScoreRule;
import edu.ypolanco.bowlingpal.service.scorerule.tenpin.TenPinBowlingRule;
import edu.ypolanco.bowlingpal.ui.BowlingUI;
import edu.ypolanco.bowlingpal.ui.cli.BowlingCLI;
import edu.ypolanco.bowlingpal.util.exception.InvalidScoreException;
import edu.ypolanco.bowlingpal.util.exception.NoParserFoundException;
import java.util.List;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class App {
    
    private BowlingUI ui;
    private ScoreParser scoreParser;
    private BowlingScoreRule rule;

    public App(BowlingUI ui, BowlingScoreRule rule) {
        this.ui = ui;
        this.rule = rule;
    }

    public void run(){
        try {
            ScoreParserFactory parserFactory = new ScoreParserFactory();
            Object source = this.ui.askForSource();
            
            this.scoreParser = parserFactory.createScoreParser(source);
            List<Lane> lanes = this.rule
                    .applyRule(this.scoreParser.parseScore());
            
            this.ui.displayScore(lanes);
        } catch (NoParserFoundException | InvalidScoreException | RuntimeException ex) {
            this.ui.displayError(ex.getLocalizedMessage());
        } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BowlingUI ui = new BowlingCLI();
        BowlingScoreRule rule = new TenPinBowlingRule();
        App app = new App(ui, rule);
        
        app.run();
    }

}
