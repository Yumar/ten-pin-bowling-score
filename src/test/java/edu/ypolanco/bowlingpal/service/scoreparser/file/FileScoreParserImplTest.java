/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ypolanco.bowlingpal.service.scoreparser.file;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ypolanco
 */
public class FileScoreParserImplTest {
    private final String validContent = "";
    
    public FileScoreParserImplTest() {
    }
    

    @Test
    public void testValidFile() throws Exception {
        System.out.println("Test FileScoreParser");
        File source = File.createTempFile("tmp", validContent);
        FileScoreParserImpl instance = new FileScoreParserImpl();
        Map<String, List<String>> expResult = null;
        Map<String, List<String>> result = instance.parseScore(source);
        assertEquals(expResult, result);
    }
    
}
