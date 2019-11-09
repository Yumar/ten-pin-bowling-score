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
package edu.ypolanco.bowlingpal.service.scoreparser.string;

import edu.ypolanco.bowlingpal.model.Lane;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 *
 * @author Yumarx <jumarpolanco@gmail.com>
 */

@AllArgsConstructor
public class StringScoreParserImpl implements StringScoreParser{
    private String source;

    @Override
    public List<Lane> parseScore(String source) {
        this.setSource(source);
        return this.paseScore();
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public List<Lane> paseScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
