package org.example;

import javax.swing.*;


/**
 * Class SimFrame is used to create window in which simulation will be shown.
 * When created, sets all properties of window, also used to launch simulation with parameters from class Menu
 */
public class SimFrame extends JFrame
{

    /**
     * New simulation
     */
    Simulator sim;

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
    SimFrame(int n, int v, int h, int power, int unit, int speed, int huntPower, int huntUnit)
    {
        /**
         * Setting new map for simulation
         */
        Map map = new Map();
        /**
         * Creating panel on which map and all entities will be shown
         */
        GUI gui = new GUI(map);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1015,1035);
        setVisible(true);
        setLocationRelativeTo(null);

        add(gui);

        sim = new Simulator(gui,map,v,h,n,power,unit,speed,huntPower,huntUnit,this);

        sim.start();

    }

}
