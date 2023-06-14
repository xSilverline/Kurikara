package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Class used to show in real time animation of current situation of simulation.
 * Displays movement of the units and whole city map.
 */
public class GUI extends JPanel
{

    /**
     * List of current vampires on map
     */
    ArrayList<Vampire> vampList = new ArrayList<>();
    /**
     * List of current hunters on map
     */
    ArrayList<Hunter> huntList = new ArrayList<>();
    /**
     * List of current villagers on map
     */
    ArrayList<Villager> villList = new ArrayList<>();
    Map map;

    /**
     * Integer containg value of alpha mask used in day-night cycle
     */
    int alpha = 255;
    /**
     * Labels used to show day number and time
     */
    JLabel msg,day;
    /**
     * Current in-sim time
     */
    int time;


    /**
     * Default constructor
     * Initiates map grid and panel layout
     * @param grid map of village
     */
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

    /**
     * Updates current entity lists and time
     * @param v list of vampires
     * @param h list of hunters
     * @param n list of villagers
     * @param time in-sim time
     * @param days in-sim day
     */
    public synchronized void setxy(ArrayList<Vampire> v, ArrayList<Hunter> h, ArrayList<Villager> n,int time,int days)
    {
        vampList = v;
        huntList = h;
        villList = n;
        this.time=time;
        msg.setText(String.valueOf(time/60)+":"+String.valueOf(time%60));
        day.setText("Day: "+days);
    }

    /**
     * Adds alpha value , makes map brighter
     */
    public synchronized void setBrighter()
    {
        if(alpha<=250)
            alpha+=5;

    }

    /**
     * Lowers alpha value, makes map darker
     */
    public synchronized void setDarker()
    {
        if(alpha>=55)
            alpha-=5;

    }

    /**
     * Sets alpha value low to make map dark
     */
    public synchronized void setNight()
    {
        alpha = 55;
    }

    /**
     * Method used paint all objects, entities and whole map on each cycle
     * @param g the <code>Graphics</code> object to protect
     */
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


            gd.fillRect(tile.x*10, tile.y*10, 10,10);
            gd.setStroke(oldStroke);
        }
        gd.setColor(new Color(222, 9, 9,255));
        for (Vampire vampire : vampList) {
            gd.fillOval(vampire.x, vampire.y, 10, 10);
        }
        gd.setColor(new Color(15, 62, 243,255));
        for (Hunter hunter : huntList) {
            gd.fillOval(hunter.x, hunter.y, 10, 10);
        }
        gd.setColor(new Color(255, 181, 2,255));
        for (Villager villager : villList) {
            gd.fillOval(villager.x, villager.y, 10, 10);
        }


    }

    /**
     * Method used to repaint (refresh) canva from other functions
     */
    public void repaintCanva()
    {
        repaint();
    }
}

