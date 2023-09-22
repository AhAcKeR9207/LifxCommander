package control.lifx.GUI.Components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import control.lifx.Light;

public class ToggleButton extends JToggleButton {
    public ToggleButton (Light light) {
        // Borders
        Border raisedResized = BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), new EmptyBorder(10, 10, 10, 10));
        Border loweredResized = BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.LOWERED), new EmptyBorder(10, 10, 10, 10));
        Border active = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 1), loweredResized);
        Border inactive = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 1), raisedResized);

        // Setup
        setAlignmentX(CENTER_ALIGNMENT);
        setText("ON");
        setBorder(inactive);
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                if (isSelected()) {
                    setText("OFF");
                    setBorder(active);
                    light.setState(false);
                } else {
                    setText("ON");
                    setBorder(inactive);
                    light.setState(true);
                }
            }
        });
    }
}