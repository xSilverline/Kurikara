package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class Menu extends JFrame implements ActionListener, ChangeListener
{
    JButton startButton;
    JButton exitButton;
    JButton ghostButton;
    JFormattedTextField villNumber;
    JFormattedTextField vampNumber;
    JFormattedTextField huntNumber;

    JSlider powerSlider,speedSlider,unitSlider,huntPowerSlider,huntUnitSlider;
    JLabel powerLabel,huntPowerLabel;
    JLabel vampLabel, villLabel, huntLabel, unitLabel, speedLabel, huntUnitLabel;

    Menu()
    {
        setTitle("Vampires vs. Hunters - SIM");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1030,1030);
        setLayout(null);
        setLocationRelativeTo(null);

        setBackground(Color.lightGray);
// __________________________________________________ BUTTONS ______________________________________________
        startButton = new JButton();
        startButton.setBounds(215,900,200,50);
        startButton.addActionListener(this);
        startButton.setText("Start Simulation");
        add(startButton);

        exitButton = new JButton();
        exitButton.setBounds(615,900,200,50);
        exitButton.addActionListener(this);
        exitButton.setText("Exit");

        add(exitButton);

        ghostButton = new JButton();
        ghostButton.setBounds(400,800,200,50);
        ghostButton.addActionListener(this);
        ghostButton.setText("Start Simulation (NO GUI)");

        add(ghostButton);

// __________________________________________________ TEXTFIELDS ______________________________________________
        NumberFormat numberFormat = NumberFormat.getInstance();
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMaximum(10000);
        numberFormatter.setMinimum(0);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setCommitsOnValidEdit(true);

        villNumber = new JFormattedTextField(numberFormatter);
        villNumber.setBounds(150,100,50,30);
        villNumber.setValue(1);
        this.add(villNumber);

        vampNumber = new JFormattedTextField(numberFormatter);
        vampNumber.setBounds(150,150,50,30);
        vampNumber.setValue(1);
        this.add(vampNumber);

        huntNumber = new JFormattedTextField(numberFormatter);
        huntNumber.setBounds(150,200,50,30);
        huntNumber.setValue(1);
        this.add(huntNumber);

        powerSlider = new JSlider(1,10,10);
        powerSlider.setBounds(250,300,200,50);
        powerSlider.setPaintTicks(true);
        powerSlider.setMinorTickSpacing(1);
        powerSlider.setPaintTrack(true);
        powerSlider.setMajorTickSpacing(5);
        powerSlider.addChangeListener(this);
        powerSlider.setSnapToTicks(true);
        this.add(powerSlider);

        huntPowerSlider = new JSlider(1,10,10);
        huntPowerSlider.setBounds(250,350,200,50);
        huntPowerSlider.setPaintTicks(true);
        huntPowerSlider.setMinorTickSpacing(1);
        huntPowerSlider.setPaintTrack(true);
        huntPowerSlider.setMajorTickSpacing(5);
        huntPowerSlider.addChangeListener(this);
        huntPowerSlider.setSnapToTicks(true);
        this.add(huntPowerSlider);


        speedSlider = new JSlider(1,10,10);
        speedSlider.setBounds(250,400,200,50);
        speedSlider.setPaintTicks(true);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTrack(true);
        speedSlider.setMajorTickSpacing(5);
        speedSlider.addChangeListener(this);
        speedSlider.setSnapToTicks(true);
        this.add(speedSlider);

        unitSlider = new JSlider(0,100,10);
        unitSlider.setBounds(250,450,300,50);
        unitSlider.setPaintTicks(true);
        unitSlider.setMinorTickSpacing(1);
        unitSlider.setPaintTrack(true);
        unitSlider.setMajorTickSpacing(10);
        unitSlider.addChangeListener(this);
        unitSlider.setSnapToTicks(true);
        this.add(unitSlider);

        huntUnitSlider = new JSlider(0,100,10);
        huntUnitSlider.setBounds(250,500,300,50);
        huntUnitSlider.setPaintTicks(true);
        huntUnitSlider.setMinorTickSpacing(1);
        huntUnitSlider.setPaintTrack(true);
        huntUnitSlider.setMajorTickSpacing(10);
        huntUnitSlider.addChangeListener(this);
        huntUnitSlider.setSnapToTicks(true);
        this.add(huntUnitSlider);


// __________________________________________________ LABELS ______________________________________________
        powerLabel = new JLabel();
        powerLabel.setBounds(30,300,250,30);
        powerLabel.setText("Moc Startowa Wampirow: "+ powerSlider.getValue());
        this.add(powerLabel);

        huntPowerLabel = new JLabel();
        huntPowerLabel.setBounds(30,350,250,30);
        huntPowerLabel.setText("Moc Startowa Lowcow: "+ huntPowerSlider.getValue());
        this.add(huntPowerLabel);

        villLabel = new JLabel("Liczba wiesniakow");
        villLabel.setBounds(30,100,150,30);

        vampLabel = new JLabel("Liczba wampirow");
        vampLabel.setBounds(30,150,150,30);

        huntLabel = new JLabel("Liczba lowcow");
        huntLabel.setBounds(30,200,150,30);

        speedLabel = new JLabel();
        speedLabel.setBounds(30,400,250,30);
        speedLabel.setText("Zmiana czasu x"+ speedSlider.getValue());
        this.add(speedLabel);

        unitLabel = new JLabel();
        unitLabel.setBounds(30,450,250,30);
        unitLabel.setText("Wsp. jednostek Wampirow "+ unitSlider.getValue()+"%");
        this.add(unitLabel);

        huntUnitLabel = new JLabel();
        huntUnitLabel.setBounds(30,500,250,30);
        huntUnitLabel.setText("Wsp. jednostek Lowcow "+ huntUnitSlider.getValue()+"%");
        this.add(huntUnitLabel);


        add(villLabel);
        add(vampLabel);
        add(huntLabel);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startButton)
        {
            dispose();
            new SimFrame((int) villNumber.getValue(),(int) vampNumber.getValue(),(int) huntNumber.getValue(),powerSlider.getValue(), unitSlider.getValue(),speedSlider.getValue(),huntPowerSlider.getValue(),huntUnitSlider.getValue());
        }
        if(e.getSource() == exitButton)
        {
            dispose();
            System.exit(0);
        }
        if(e.getSource() == ghostButton)
        {
            dispose();
            new GhostSim((int) villNumber.getValue(),(int) vampNumber.getValue(),(int) huntNumber.getValue(),powerSlider.getValue(), unitSlider.getValue(),speedSlider.getValue(),huntPowerSlider.getValue(),huntUnitSlider.getValue());
        }

    }


    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(e.getSource()==powerSlider)
            powerLabel.setText("Moc Startowa Wampirow: "+ powerSlider.getValue());
        if(e.getSource() == speedSlider)
            speedLabel.setText("Zmiana czasu x"+ speedSlider.getValue());
        if(e.getSource() == unitSlider)
            unitLabel.setText("Wsp. jednostek Wampirow "+ unitSlider.getValue()+"%");
        if(e.getSource() == huntUnitSlider)
            huntUnitLabel.setText("Wsp. jednostek Lowcow "+ huntUnitSlider.getValue()+"%");
        if(e.getSource()==huntPowerSlider)
            huntPowerLabel.setText("Moc Startowa Lowcow: "+ huntPowerSlider.getValue());
    }
}
