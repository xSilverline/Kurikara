package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JPanel
{

    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();
    ArrayList<Villager> villList = new ArrayList<>();

    GUI()
    {
        this.setPreferredSize(new Dimension(1020,1020));
    }

    public void setxy(ArrayList<Vampire> v, ArrayList<Hunter> h, ArrayList<Villager> n)
    {
        vampList = v;
        huntList = h;
        villList = n;
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
        gd.setColor(Color.gray);
        for (Villager villager : villList) {
            gd.fillOval(villager.x, villager.y, 10, 10);
        }

    }


    public void repaintCanva()
    {
        repaint();
    }
}

