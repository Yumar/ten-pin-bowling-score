/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ypolanco.bowlingpal.service.scoreparser;

import edu.ypolanco.bowlingpal.service.scoreparser.file.FileScoreParser;
import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ypolanco
 */
public class ScoreParserFactoryTest {
    
    public ScoreParserFactoryTest() {
    }

    @Test
    public void testCreateScoreParser() throws Exception {
        System.out.println("Test ScoreParserFactory with File source");
        File source = new File("valid.txt");
        ScoreParserFactory instance = new ScoreParserFactory();
        ScoreParser scoreParser = instance.createScoreParser(source);
        assertTrue(scoreParser instanceof FileScoreParser);
    }
    
}
