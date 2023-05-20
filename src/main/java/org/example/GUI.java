package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JPanel
{
    int x,y;
    int vamps,hunts,villager;
    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();

    GUI()
    {
        this.setPreferredSize(new Dimension(1020,1020));
    }

    public void setxy(ArrayList<Vampire> v, ArrayList<Hunter> h)
    {
        vampList = v;
        huntList = h;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;
        gd.setColor(Color.red);
        for (Vampire vampire : vampList) {
            gd.fillOval(vampire.x, vampire.y, 10, 10);
        }
        gd.setColor(Color.blue);
        for (Hunter hunter : huntList) {
            gd.fillOval(hunter.x, hunter.y, 10, 10);
        }

    }


    public void repaintCanva()
    {
        repaint();
    }
}

