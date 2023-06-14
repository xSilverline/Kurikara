package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GhostSim extends JFrame implements ActionListener
{
    GhostSim(int n, int v, int h, int power, int unit, int speed, int huntPower, int huntUnit)
    {
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

        Simulator sim = new Simulator(null,map,v,h,n,power,unit,speed,huntPower,huntUnit,this);


        sim.start();

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
