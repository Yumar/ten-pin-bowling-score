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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */
public class AppTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private final String breakLine = System.lineSeparator();

    public AppTest() {
    }

    @AfterAll
    public void tearDownClass() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testValid() throws IOException {
        String expOutput = "Please enter score file path..."+breakLine
                + "Frame		1	2	3	4	5	6	7	8	9	10"+breakLine
                + "Jeff"+breakLine
                + "Pinfalls	X  	7 /	9 -	X  	0 8	8 /	F 6	X  	X  	X 8 1 	"+breakLine
                + "Score		20	39	48	66	74	84	90	120	148	167"+breakLine
                + "John"+breakLine
                + "Pinfalls	3 /	6 3	X  	8 1	X  	X  	9 -	7 /	4 4	X 9 0 	"+breakLine
                + "Score		16	25	44	53	82	101	110	124	132	151"+breakLine;

        String input = "valid.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        System.setIn(in);
        App.main(null);
        out.flush();

        assertEquals(expOutput, out.toString());
    }

    @Test
    public void testZeros() throws IOException {
        String expOutput = "Please enter score file path..."+breakLine
                + "Frame		1	2	3	4	5	6	7	8	9	10"+breakLine
                + "John"+breakLine
                + "Pinfalls	0 -	0 -	0 -	0 -	0 -	0 -	0 -	0 -	0 -	0 -	"+breakLine
                + "Score		0	0	0	0	0	0	0	0	0	0"+breakLine;

        String input = "zeros.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        System.setIn(in);
        App.main(null);
        out.flush();
        
        assertEquals(expOutput, out.toString());
    }

    @Test
    public void testPerfect() throws IOException {
        String expOutput = "Please enter score file path..."+breakLine
                + "Frame		1	2	3	4	5	6	7	8	9	10"+breakLine
                + "Carl"+breakLine
                + "Pinfalls	X  	X  	X  	X  	X  	X  	X  	X  	X  	X X  X  	"+breakLine
                + "Score		30	60	90	120	150	180	210	240	270	300"+breakLine;

        String input = "perfect.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        System.setIn(in);
        App.main(null);
        out.flush();

        assertEquals(expOutput, out.toString());
    }

}
