package org.example;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JPanel
{

    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();
    ArrayList<Villager> villList = new ArrayList<>();
    Map map;

    Tile tile1;


    GUI(Map grid)
    {
        map=grid;
//        map.saveMapToFile();
        this.setPreferredSize(new Dimension(1000,1000));
    }

    public void setxy(ArrayList<Vampire> v, ArrayList<Hunter> h, ArrayList<Villager> n,Tile tile)
    {
        vampList = v;
        huntList = h;
        villList = n;
        this.tile1 = tile;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        float thickness = 2;
        Stroke oldStroke = gd.getStroke();
        for( Tile tile: map.grid.values())
        {
            gd.setColor(Color.black);
            gd.setStroke(new BasicStroke(thickness));
            gd.drawRect(tile.x*10, tile.y*10, 10,10);
            if(tile.type== 0)
                gd.setColor(Color.black);
            if(tile.type== 1)
                gd.setColor(Color.gray);
            if(tile.type== 2)
                gd.setColor(new Color(19, 179, 201));
            if(tile.type== 3)
                gd.setColor(new Color(217, 80, 80));
            if(tile.type== 4)
                gd.setColor(new Color(148, 234, 132));
            if(tile.type== 5)
                gd.setColor(new Color(157, 239, 237));

            gd.fillRect(tile.x*10, tile.y*10, 10,10);
            gd.setStroke(oldStroke);
        }
        gd.setColor(Color.red);
        for (Vampire vampire : vampList) {
            gd.fillOval(vampire.x, vampire.y, 10, 10);
        }
        gd.setColor(Color.blue);
        for (Hunter hunter : huntList) {
            gd.fillOval(hunter.x, hunter.y, 10, 10);
        }
        gd.setColor(Color.yellow);
        for (Villager villager : villList) {
            gd.fillOval(villager.x, villager.y, 10, 10);
        }
//        gd.setColor(Color.yellow);
//        gd.fillRect(tile1.x*10, tile1.y*10, 10,10);


    }


    public void repaintCanva()
    {
        repaint();
    }
}

