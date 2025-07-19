/* Copyright (C) 2025 Avishek Gorai
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package indi.avishek144.passphrase_generator.passphrase_generator_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * @author AVISHEK
 */
class GeneratePassphrase
implements ActionListener {
    private App sourceFrame;

	public GeneratePassphrase(App source_frame)
    {
        setSourceFrame(source_frame);
    }
	
    /**
	 * @param sourceFrame the sourceFrame to set
	 */
	private GeneratePassphrase setSourceFrame(App sourceFrame) {
		this.sourceFrame = sourceFrame;
		return this;
	}

    /**
	 * @return the sourceFrame
	 */
	private App getSourceFrame() {
		return sourceFrame;
	}

	@Override
    public void actionPerformed(ActionEvent e)
    {
        var word_table = getSourceFrame().getWordTable();
        var number_of_dice = getSourceFrame().getNumberOfDice();
        var random_generator = new Random();
        var number_of_words = getSourceFrame().getNumberOfWords();
        
        var passphrase = new StringBuilder();
        
        for (var index = 1; index <= number_of_words; ++index) {
	        var number = 0;
	        for (var j = 1; j <= number_of_dice; ++j) {
	        	number = number * 10 + random_generator.nextInt(5) + 1;
	        }
	        
	        passphrase.append(word_table.get(number)).append(' ');
        }
        
        passphrase.deleteCharAt(passphrase.length()-1);
        getSourceFrame().setPassphrase(passphrase.toString());
    }   
}
