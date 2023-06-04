package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimFrame extends JFrame implements ActionListener
{
    SimFrame(int n, int v, int h, int power, int unit, int speed, int huntPower, int huntUnit)
    {
        Map map = new Map();
        GUI gui = new GUI(map);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1015,1040);
        setVisible(true);
        setLocationRelativeTo(null);
        add(gui);

        Simulator sim = new Simulator(gui,map,v,h,n,power,unit,speed,huntPower,huntUnit); // zmienic na zmienna


        sim.start();

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
