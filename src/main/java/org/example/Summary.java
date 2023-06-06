package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Summary extends JFrame implements ActionListener, ChangeListener
{
    JButton saveButton;
    JButton exitButton;
    JLabel timeLabel, daysLabel;
    JLabel vampKilledLabel, villKilledLabel, huntKilledLabel, vampTransformLabel, huntTransformLabel, winningSide;

    int totalTimer,days,killedVillagers,killedVamps,killedHuners,transHunt,transVamp;
    ArrayList<Integer> villHistogram,vampHistogram,huntHistogram,vampkillHistogram,villkillHistogram,huntkillHistogram,vamptransHistogram,hunttransHistogram,dayHistogram;
    Summary(int totalTimer, int days, int killedVillagers, int killedVamps, int killedHunters, int transformedToHunt, int transformedToVamp, String winners, ArrayList<Integer> villHistogram, ArrayList<Integer> vampHistogram, ArrayList<Integer> huntHistogram, ArrayList<Integer> villkillHistogram, ArrayList<Integer> vampkillHistogram, ArrayList<Integer> huntkillHistogram, ArrayList<Integer> vampTransHistogram, ArrayList<Integer> huntTransHistogram, ArrayList<Integer> dayHistory)
    {
        this.villHistogram = villHistogram;
        this.vampHistogram = vampHistogram;
        this.huntHistogram = huntHistogram;
        this.vampkillHistogram = vampkillHistogram;
        this.villkillHistogram = villkillHistogram;
        this.huntkillHistogram = huntkillHistogram;
        this.vamptransHistogram = vampTransHistogram;
        this.hunttransHistogram = huntTransHistogram;
        this.dayHistogram = dayHistory;
        this.totalTimer = totalTimer;
        setTitle("Vampires vs. Hunters - WYNIKI");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1030,1030);
//        pack();

        setLayout(null);
        setLocationRelativeTo(null);

        setBackground(Color.lightGray);

        saveButton = new JButton();
        saveButton.setBounds(215,900,200,50);
        saveButton.addActionListener(this);
        saveButton.setText("Save Result");
        add(saveButton);

        exitButton = new JButton();
        exitButton.setBounds(615,900,200,50);
        exitButton.addActionListener(this);
        exitButton.setText("Exit");

        add(exitButton);


        timeLabel = new JLabel();
        timeLabel.setBounds(30,300,450,30);
        timeLabel.setText("Uplyw czasu: "+ totalTimer/60+"h "+totalTimer%60+"min");
        this.add(timeLabel);

        daysLabel = new JLabel();
        daysLabel.setBounds(30,350,450,30);
        daysLabel.setText("Liczba dni: "+ days);
        this.add(daysLabel);

        villKilledLabel = new JLabel();
        villKilledLabel.setBounds(30,100,450,30);
        villKilledLabel.setText("Liczba utraconych wiesniakow: "+killedVillagers);

        vampKilledLabel = new JLabel();
        vampKilledLabel.setBounds(30,150,450,30);
        vampKilledLabel.setText("Liczba utraconych wampirow: "+killedVamps);

        huntKilledLabel = new JLabel();
        huntKilledLabel.setBounds(30,200,450,30);
        huntKilledLabel.setText("Liczba utraconych lowcow: "+killedHunters);

        huntTransformLabel = new JLabel();
        huntTransformLabel.setBounds(30,400,450,30);
        huntTransformLabel.setText("Liczba zrekrutowanych lowcow: "+ transformedToHunt);
        this.add(huntTransformLabel);

        vampTransformLabel = new JLabel();
        vampTransformLabel.setBounds(30,450,450,30);
        vampTransformLabel.setText("Liczba przemienionych w wampiry:  "+ transformedToVamp);
        this.add(vampTransformLabel);

        winningSide = new JLabel();
        winningSide.setBounds(30,500,450,30);
        winningSide.setText("Strona wygrywajaca:  "+ winners);
        this.add(winningSide);


        add(villKilledLabel);
        add(vampKilledLabel);
        add(huntKilledLabel);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == saveButton)
        {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String fileName = "SIM_RESULT_"+timestamp+".txt";

            try {
                FileWriter fileWriter = new FileWriter(fileName);
                PrintWriter writer = new PrintWriter(fileWriter);

                writer.print("Pop.Wampirow;");
                writer.print("Pop.Wiesniakow;");
                writer.print("Pop.Lowcow;");
                writer.print("Zabite Wampiry;");
                writer.print("Zabici wiesniacy;");
                writer.print("Zabici Lowcy;");
                writer.print("Przemiany Wamp;");
                writer.print("Przemiany Lowcow;");
                writer.print("Dni;");
                writer.print("Czas\n");

                for(int i=0;i<totalTimer;i++)
                {
                    writer.print(vampHistogram.get(i)+";");
                    writer.print(villHistogram.get(i)+";");
                    writer.print(huntHistogram.get(i)+";");
                    writer.print(vampkillHistogram.get(i)+";");
                    writer.print(villkillHistogram.get(i)+";");
                    writer.print(huntkillHistogram.get(i)+";");
                    writer.print(vamptransHistogram.get(i)+";");
                    writer.print(hunttransHistogram.get(i)+";");
                    writer.print(dayHistogram.get(i)+";");
                    writer.print(i+";\n");
                }

                writer.flush();
                writer.close();




            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }
        if(e.getSource() == exitButton)
        {
            dispose();
            System.exit(0);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
