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

package avishek.gorai.passphrase_generator;

import java.awt.GridBagConstraints;

/**
 * I represent the layout of the components.
 * 
 * @author Avishek Gorai
 */
class AppLayoutConstraint
extends GridBagConstraints {
    private static final long serialVersionUID = 8939015350997654593L;
    
    AppLayoutConstraint() {
        super();
        setWeightX(1.0);
        setWeightY(1.0);
        setFill(BOTH);
    }

    AppLayoutConstraint setWeightY(double d) {
        weighty = d;
        return this;
    }
    
    double getWeightY() {
        return weighty;
    }

    AppLayoutConstraint setWeightX(double d) {
        weightx = d;
        return this;
    }
    
    double getWeightX() {
        return weightx;
    }

    AppLayoutConstraint setGridX(int x) {
        gridx = x;
        return this;
    }
    
    int getGridX() {
        return gridx;
    }
    
    AppLayoutConstraint setGridY(int y) {
        gridy = y;
        return this;
    }
    
    int getGridY() {
        return gridy;
    }

    AppLayoutConstraint setGridWidth(int i) {
        gridwidth = i;
        return this;
    }
    
    int getGridWidth() {
        return gridwidth;
    }

    AppLayoutConstraint setFill(int f) {
        fill = f;
        return this;
    }
    
    int getFill() {
        return fill;
    }

    AppLayoutConstraint setGridHeight(int h) {
        gridheight = h;
        return this;
    }
    
    int getGridHeight() {
        return gridheight;
    }
}
