package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class GUI extends JPanel
{

    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();
    ArrayList<Villager> villList = new ArrayList<>();
    Map map;

    int alpha = 255;
    JLabel msg,day;
    int time;


    GUI(Map grid)
    {
        map=grid;

        this.setPreferredSize(new Dimension(1000,1000));
        this.setBackground(new Color(133, 133, 133, 255));
        setLayout(null);
        msg = new JLabel();
        msg.setBounds(950,30,100,30);
        msg.setForeground(Color.white);

        this.add(msg);

        day = new JLabel();
        day.setBounds(940,45,100,30);
        day.setForeground(Color.white);

        this.add(day);
    }

    public synchronized void setxy(ArrayList<Vampire> v, ArrayList<Hunter> h, ArrayList<Villager> n,int time,int days)
    {
        vampList = v;
        huntList = h;
        villList = n;
        this.time=time;
        msg.setText(String.valueOf(time/60)+":"+String.valueOf(time%60));
        day.setText("Day: "+days);
    }
    public synchronized void setBrighter()
    {
        if(alpha<=250)
            alpha+=5;
    }
    public synchronized void setDarker()
    {
        if(alpha>=55)
            alpha-=5;
    }
    public synchronized void setNight()
    {
        alpha = 55;
    }

    public synchronized void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        float thickness = 2;
        Stroke oldStroke = gd.getStroke();
        for( Tile tile: map.grid.values())
        {
            gd.setColor(new Color(45, 45, 45));
            gd.setStroke(new BasicStroke(thickness));
//            gd.drawRect(tile.x*10, tile.y*10, 10,10);
            if(tile.type== 0)
            {
                gd.drawRect(tile.x*10, tile.y*10, 9,9);
                gd.setColor(new Color(65, 65, 65,alpha));
            }

            if(tile.type== 1)
            {
                gd.setColor(new Color(143, 143, 143, alpha));
                gd.drawRect(tile.x*10, tile.y*10, 9,9);
                gd.setColor(new Color(133, 133, 133, alpha));
            }
            if(tile.type== 2)
                gd.setColor(new Color(108, 129, 197,alpha));

            if(tile.type== 3)
                gd.setColor(new Color(229, 129, 129, alpha));
            if(tile.type== 4)
                gd.setColor(new Color(148, 234, 132,alpha));

            if(tile.type== 5)
                gd.setColor(new Color(132, 197, 195,alpha));

//            if(tile.type== 0)
//                gd.fillRoundRect(tile.x*10,tile.y*10,10,10,2,2);
//            else
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

