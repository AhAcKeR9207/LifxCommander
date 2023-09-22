package control.lifx.GUI;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.lifx.Light;

public class LifxGUI extends JFrame{
    public LifxGUI (ArrayList<Light> lights) {
        setTitle("Lifx GUI");
        JPanel main = new JPanel();

        for (Light light : lights) {
            main.add(light.getPanel());
        }

        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

        add(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}