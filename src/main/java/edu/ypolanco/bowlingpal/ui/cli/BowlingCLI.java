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
package edu.ypolanco.bowlingpal.ui.cli;

import edu.ypolanco.bowlingpal.model.Lane;
import edu.ypolanco.bowlingpal.ui.BowlingUI;
import edu.ypolanco.bowlingpal.ui.Displayer;
import edu.ypolanco.bowlingpal.ui.cli.displayer.LaneCLIDisplayer;
import edu.ypolanco.bowlingpal.util.exception.InvalidScoreException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class BowlingCLI implements BowlingUI {

    @Override
    public void displayScore(List<Lane> lanes) throws InvalidScoreException {
        Displayer<List<Lane>> scoreDisplayer = new LaneCLIDisplayer();
        scoreDisplayer.display(lanes);
    }

    private File askForFilePath() throws FileNotFoundException {
        File file = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter score file path...");
        file = new File(scanner.nextLine());
        if (file.exists() && !file.isDirectory()) {
            return file;
        }
        throw new FileNotFoundException("No file found for the provided path");
    }

    @Override
    public Object askForSource() throws RuntimeException {
        try {
            return askForFilePath();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void displayError(String errorMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
