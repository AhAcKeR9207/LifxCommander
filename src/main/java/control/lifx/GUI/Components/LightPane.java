package control.lifx.GUI.Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public abstract class LightPane extends JLayeredPane {
    private JPanel back1, back2, back3, back4;
    private JPanel bulb1, bulb2, bulb3, bulb4, bulb5, bulb6, bulb7;
    private JPanel decor1, decor2, decor3, decor4, decor5, decor6, decor7, decor8, decor9, decor10, decor11, decor12;

    /**
    * Creates new LightPanel2
    */
    public LightPane () {
        // Configures pane
        setPreferredSize(new Dimension(220, 190));
        int center = getPreferredSize().width / 2;
        makePanels(center);
        JPanel[] back = {back1, back2, back3, back4};
        for (int i = 0; i < back.length; i++) {
            add(back[i], 0);
        }

        JPanel[] bulbColor = {bulb1, bulb2, bulb3, bulb4, bulb5, bulb6, bulb7};
        for (int i = 0; i < bulbColor.length; i++) {
            add(bulbColor[i], 1);
        }

        JPanel[] decor = {decor1, decor2, decor3, decor4, decor5, decor6, decor7, decor8, decor9, decor10, decor11, decor12};
        for (int i = 0; i < decor.length; i++) {
            add(decor[i], 0);
        }

        setAlignmentX(CENTER_ALIGNMENT);
    }

    public void setBulbColor(Color color) {
        System.out.println(color);
        bulb1.setBackground(color);
        bulb2.setBackground(color);
        bulb3.setBackground(color);
        bulb4.setBackground(color);
        bulb5.setBackground(color);
        bulb6.setBackground(color);
        bulb7.setBackground(color);
    }

    /** 
     * The light needs to be made up of multiple small shapes.
     * Yes, this is necessary.  Believe me, I tried to find an easier way.
     * I would prefer the bulbcolor panel be a single thing, but the bulbs kept rendering weirdly after I updated the colors, so I was forced to split them up.  It did allow me to remove a lot of stuff though!
    */
    public void makePanels(int center) {
        // If the top left corner of the panel is to the left of the center, the x value must be transformed to the left.
        // If the top left corner of the panel is to the right of the center, the x value must be transformed to the right.
        // If the panel is symmetrical across the center, then the x transformation is equal to panelWidth/2;
        back1 = new Panel(center - 55, 140, 30, 40, Color.white); // bottom left white
        back2 = new Panel(center + 25, 140, 30, 40, Color.white); // bottom right white
        back3 = new Panel(center - 55, 0, 110, 140, Color.black); // top black
        back4 = new Panel(center - 25, 140, 50, 40, Color.black); // bottom black
        bulb1 = new Panel(center - 25, 10, 50, 10, Color.red); // top 1x5 red
        bulb2 = new Panel(center - 35, 20, 70, 10, Color.red); // top 1x7 red
        bulb3 = new Panel(center - 45, 30, 30, 60, Color.red); // left 6x3 red
        bulb4 = new Panel(center - 5, 30, 50, 60, Color.red); // right 6x5 red
        bulb5 = new Panel(center - 35, 90, 20, 20, Color.red); // bottom left 2x2 red
        bulb6 = new Panel(center + 15, 90, 20, 20, Color.red); // bottom right 2x2 red
        bulb7 = new Panel(center - 25, 100, 50, 30, Color.red); // bottom 3x5 red
        decor1 = new Panel(center - 55, 0, 30, 10, Color.white); // top left horixontal
        decor2 = new Panel(center - 55, 0, 10, 30, Color.white); // top left vertical
        decor3 = new Panel(center + 25, 0, 30, 10, Color.white); // top right horizontal
        decor4 = new Panel(center + 45, 0, 10, 30, Color.white); // top right vertical
        decor5 = new Panel(center - 55, 90, 10, 50, Color.white); // bottom left large
        decor6 = new Panel(center - 45, 110, 10, 30, Color.white); // bottom left medium
        decor7 = new Panel(center - 35, 130, 10, 10, Color.white); // bottom left small
        decor8 = new Panel(center + 45, 90, 10, 50, Color.white); // bottom right large
        decor9 = new Panel(center + 35, 110, 10, 30, Color.white); // bottom right medium
        decor10 = new Panel(center + 25, 130, 10, 10, Color.white); // bottom right small
        decor11 = new Panel(center - 15, 140, 30, 10, Color.white); // top strip
        decor12 = new Panel(center - 15, 160, 30, 10, Color.white); // bottom strip
    }
}