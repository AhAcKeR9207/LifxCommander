package control.lifx.GUI.Components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import control.lifx.Light;

public class ColorButton extends JButton {
    public ColorButton(Light light) {
        setText("Color");
        setAlignmentX(CENTER_ALIGNMENT);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Pick a color", light.getColor());
                light.setColor(color);
            }
        });
    }
}