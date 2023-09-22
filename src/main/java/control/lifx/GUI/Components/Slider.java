package control.lifx.GUI.Components;

import javax.swing.*;
import javax.swing.event.*;

import control.lifx.Light;

public class Slider extends JPanel {
    public Slider(Light light) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                light.setBrightness(value);
           }
        });

        add(slider);
        setAlignmentX(CENTER_ALIGNMENT);
        setBorder(BorderFactory.createTitledBorder("Brightness"));
    }
}