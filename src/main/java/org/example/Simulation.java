package org.example;


import javax.swing.*;

public class Simulation
{
    public static void main(String[] args)
    {
        // TODO Komunikacja użytkownik - program, okienko, parametry ustawiane, przekazać do symulatora

        JFrame frame = new JFrame("Vampires vs. Hunters - SIM");
        GUI gui = new GUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1030,1030);
        frame.setVisible(true);
        frame.add(gui);
        frame.setLocationRelativeTo(null);

        Simulator sim = new Simulator(gui,10,10); // zmienic na zmienna


        sim.start();
    }
}