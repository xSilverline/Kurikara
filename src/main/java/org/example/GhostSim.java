package org.example;

import javax.swing.*;


/**
 * Class GhostSim is used to create window in which text asking for patience will be shown.
 * When created, sets all properties of window, also used to launch simulation with parameters from class Menu
 */
public class GhostSim extends JFrame
{
    /**
     *
     * @param n number of villagers
     * @param v number of vampires
     * @param h number of hunters
     * @param power initial power of vampires
     * @param unit probability of transforming into vampire
     * @param speed time acceleration
     * @param huntPower initial power of hunters
     * @param huntUnit probability of transforming into hunter
     */
    GhostSim(int n, int v, int h, int power, int unit, int speed, int huntPower, int huntUnit)
    {
        /**
         * Setting new map for simulation
         */
        Map map = new Map();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1015,1040);
        setVisible(true);
        setLocationRelativeTo(null);

        setLayout(null);
        JLabel msg = new JLabel();
        msg.setBounds(375,375,250,30);
        msg.setText("Simulation is running, please wait");
        this.add(msg);

        /**
         * Creating new simulatoion class
         */
        Simulator sim = new Simulator(null,map,v,h,n,power,unit,speed,huntPower,huntUnit,this);

        sim.start();

    }

}
