package control.lifx.GUI.Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class NullPanel extends JPanel {
    public NullPanel() {
        setPreferredSize(new Dimension(220, 190));
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);

        JLabel nullPanel = new JLabel("No Light Found");
        nullPanel.setPreferredSize(new Dimension(115, 115));
        
        Border part1 = BorderFactory.createLineBorder(Color.black);
        Border part2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        nullPanel.setBorder(BorderFactory.createCompoundBorder(part1, part2));

        add(nullPanel);
    }
}
