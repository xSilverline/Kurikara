package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimFrame extends JFrame implements ActionListener
{
    JButton nuke;
    Simulator sim;
    SimFrame(int n, int v, int h, int power, int unit, int speed, int huntPower, int huntUnit)
    {
        Map map = new Map();
        GUI gui = new GUI(map);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1055,1040);
        setVisible(true);
        setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        nuke = new JButton("NUKE");
        nuke.setBounds(1020,0,30,30);
        nuke.setBackground(Color.red);
        nuke.setForeground(Color.white);
        nuke.addActionListener(this);
        add(gui);
        add(nuke);

        sim = new Simulator(gui,map,v,h,n,power,unit,speed,huntPower,huntUnit,this);


        sim.start();

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == nuke)
        {
            System.err.println("Nuke");
            sim.nukeIt();
        }
    }
}
