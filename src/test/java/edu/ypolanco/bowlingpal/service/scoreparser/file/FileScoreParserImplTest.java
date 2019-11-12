/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ypolanco.bowlingpal.service.scoreparser.file;

import edu.ypolanco.bowlingpal.service.util.BowlingTestUtil;
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

    public FileScoreParserImplTest() {
    }

    @Test
    public void testValidFile() throws Exception {
        System.out.println("Test FileScoreParser");
        File source = File.createTempFile("tempfile", ".txt"); 
        BowlingTestUtil.writeValidFile(source);
        FileScoreParserImpl instance = new FileScoreParserImpl();
        Map<String, List<String>> expResult = BowlingTestUtil.getValidScoreMap();
        Map<String, List<String>> result = instance.parseScore(source);
        assertEquals(expResult, result);
    }

}
