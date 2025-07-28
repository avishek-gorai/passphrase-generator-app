package avishek.gorai.passphrase_generator;

import java.awt.GridBagConstraints;

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

    AppLayoutConstraint setGridx(int x) {
        gridx = x;
        return this;
    }
    
    int getGridx() {
        return gridx;
    }
    
    AppLayoutConstraint setGridy(int y) {
        gridy = y;
        return this;
    }
    
    int getGridy() {
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
