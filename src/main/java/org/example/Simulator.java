package org.example;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class is the heart of whole project.
 * It runs simulation and decides what should be done next.
 * It controls behaviour of entities when they meet on map
 * Also, it gathers all data used for statistic purposes.
 */
public class Simulator extends Thread
{
    /**
     * List of alive vampires
     */
    ArrayList<Vampire> vampList = new ArrayList<>();
    /**
     * List of alive hunters
     */
    ArrayList<Hunter> huntList = new ArrayList<>();
    /**
     * List of alive villagers
     */
    ArrayList<Villager> villList = new ArrayList<>();
    /**
     * List of vampires that has been changed into.
     * Used to update vampList after whole turn.
     */
    ArrayList<Vampire> vampClone = new ArrayList<>();
    /**
     * List of vampires that has been killed.
     * Used to update vampList after whole turn.
     */
    ArrayList<Vampire> vampTrash = new ArrayList<>();
    /**
     * List of hunters that has been changed into.
     * Used to update huntList after whole turn.
     */
    ArrayList<Hunter> huntClone = new ArrayList<>();
    /**
     * List of population of vampires on each day.
     */
    ArrayList<Integer> vampHistogram = new ArrayList<>();
    /**
     * List of population of hunters on each day.
     */
    ArrayList<Integer> huntHistogram = new ArrayList<>();
    /**
     * List of population of villagers on each day.
     */
    ArrayList<Integer> villHistogram = new ArrayList<>();
    /**
     * List of numbers of killed vampires on each day.
     */
    ArrayList<Integer> vampkillHistogram = new ArrayList<>();
    /**
     * List of numbers of killed hunters on each day.
     */
    ArrayList<Integer> huntkillHistogram = new ArrayList<>();
    /**
     * List of numbers of killed villagers on each day.
     */
    ArrayList<Integer> villkillHistogram = new ArrayList<>();
    /**
     * List of numbers of transformed into vampires on each day.
     */
    ArrayList<Integer> vampTransHistogram = new ArrayList<>();
    /**
     * List of numbers of transformed into hunters on each day.
     */
    ArrayList<Integer> huntTransHistogram = new ArrayList<>();
    /**
     * List of days - control purpose
     */
    ArrayList<Integer> dayHistory = new ArrayList<>();
    /**
     * GUI class, needed to make changes on screen in real time
     */
    GUI screen;
    /**
     * Time acceleration, divides sleep time to make simulation faster
     */
    int speed;
    /**
     * Saves temporary horizontal position of entity
     */
    int currentX;
    /**
     * Saves temporary vertical position of entity
     */
    int currentY;
    /**
     * Saves temporary field on which entity is.
     */
    Tile currentTile;
    /**
     * A string that contains winning side.
     */
    String winners;
    /**
     * An integer to count time in simulation, reset on 1440 what equals one day (in sim)
     */
    int timer;
    /**
     * An integer to count total time in simulation ( 1 tick = minute is sim world)
     */
    int totalTimer;
    /**
     * An integer to count number of days
     */
    int days;
    /**
     * An integer to count total number of entities transformed to vampires
     */
    int transformedToVamp;
    /**
     * An integer to count total number of entities transformed to hunters
     */
    int transformedToHunt;
    /**
     * An integer to count total number of vampires killed
     */
    int killedVamps;
    /**
     * An integer to count total number of villagers killed
     */
    int killedVillagers;
    /**
     * An integer to count total number of hunters killed
     */
    int killedHunters;
    JFrame frame;

    Map map;

    /**
     *
     * @param gui GUI class used to show sim animation
     * @param map Class containing map information
     * @param numOfVamps number of vampires
     * @param numOfHunt number of hunters
     * @param numOfVill number of villagers
     * @param power init. power of vampires
     * @param unit probability of transforming int vampire
     * @param speed time acceleration
     * @param huntPower init. power of hunters
     * @param huntUnit probability of transforming int hunters
     * @param frame SimFrame/GhostFrame class , used to close window after simulation ends
     */
    Simulator(GUI gui, Map map, int numOfVamps, int numOfHunt, int numOfVill, int power, int unit, int speed, int huntPower, int huntUnit, JFrame frame)
    {
        this.frame = frame;
        this.map = map;
        this.speed = speed;
        screen = gui;
        for(int i = 0;i<numOfVamps;i++)
        {
            vampList.add(new Vampire(unit,power,i));
        }
        for(int i = 0;i<numOfHunt;i++)
        {
            huntList.add(new Hunter(huntUnit,huntPower,i));
        }
        for(int i = 0;i<numOfVill;i++)
        {
            villList.add(new Villager(i));
        }
        totalTimer = 0;
        timer = 0;
        days = 0;

    }

    /**
     * Main method used to run whole simulation.
     * Using thread and loop to make actions continuously
     */
    @Override
    public synchronized void run()
    {
        while(true)
        {
            /*
              for every villager gets position on map, sets corresponding tile active.
              Removes villager from tile.
              Moves villager to new position
              sets new tile active basing on new current position
              Ads villager to active tile.
              on the end saves to villager memory what tiles are around him.
             */
            for (Villager n: villList)
            {
                currentX= n.getX()/10;
                currentY= n.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                currentTile.removeVillager(n);
                n.move();

                currentX= n.getX()/10;
                currentY= n.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                currentTile.addVillager(n);
                n.setMove(map.grid.get(currentX+(currentY-1)*100).type,map.grid.get(currentX+(currentY-1)*100).id,map.grid.get(currentX+(currentY+1)*100).type,map.grid.get(currentX+(currentY+1)*100).id,map.grid.get(currentX-1+currentY*100).type,map.grid.get(currentX-1+currentY*100).id,map.grid.get(currentX+1+currentY*100).type,map.grid.get(currentX+1+currentY*100).id);
            }
            /*
            Same as villager, with addition that after getting active tile, checks if its occupied and makes action depending on it.
             */

            for (Hunter h: huntList)
            {
                currentX= h.getX()/10;
                currentY= h.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                HunterCheckRecruitment(h);
                currentTile.removeHunter(h);
                h.move();

                currentX= h.getX()/10;
                currentY= h.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                currentTile.addHunter(h);
                h.setMove(map.grid.get(currentX+(currentY-1)*100).type,map.grid.get(currentX+(currentY-1)*100).id,map.grid.get(currentX+(currentY+1)*100).type,map.grid.get(currentX+(currentY+1)*100).id,map.grid.get(currentX-1+currentY*100).type,map.grid.get(currentX-1+currentY*100).id,map.grid.get(currentX+1+currentY*100).type,map.grid.get(currentX+1+currentY*100).id);
                HunterCheckRecruitment(h);
            }

            huntList.addAll(huntClone);
            huntClone.clear();
            /*
            same as hunter
             */
                        for (Vampire v: vampList)
            {

                currentX= v.getX()/10;
                currentY= v.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);

                VampCheckHostility(v);

                currentTile.removeVampire(v);

                v.move();

                currentX= v.getX()/10;
                currentY= v.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                currentTile.addVampier(v);
                v.setMove(map.grid.get(currentX+(currentY-1)*100).type,map.grid.get(currentX+(currentY-1)*100).id,map.grid.get(currentX+(currentY+1)*100).type,map.grid.get(currentX+(currentY+1)*100).id,map.grid.get(currentX-1+currentY*100).type,map.grid.get(currentX-1+currentY*100).id,map.grid.get(currentX+1+currentY*100).type,map.grid.get(currentX+1+currentY*100).id);

                VampCheckHostility(v);
            }
                        /*
                        Everything bellow update corresponding lists or adds to history. Also, updates info for GUI to repaint
                         */
            vampList.addAll(vampClone);
            vampList.removeAll(vampTrash);
            vampTrash.clear();
            vampClone.clear();
            if(screen != null)
                screen.setxy(vampList, huntList, villList,timer,days);
            vampHistogram.add(vampList.size());
            huntHistogram.add(huntList.size());
            villHistogram.add(villList.size());
            vampkillHistogram.add(killedVamps);
            villkillHistogram.add(killedVillagers);
            huntkillHistogram.add(killedHunters);
            vampTransHistogram.add(transformedToVamp);
            huntTransHistogram.add(transformedToHunt);
            dayHistory.add(days);
            timer++;
            totalTimer++;
            /*
            Changes alpha mask on GUI, from 6AM to 2PM makes day brighter, from 2PM to 10PM makes darker, night is from 10PM to 6AM and alpha is reduced greatly.
             */
            if(timer>=360 && timer < 840) // 6AM to 2PM
            {
                if(timer%12 == 0) {
                    assert screen != null;
                    screen.setBrighter();
                }

            }
            if(timer>=840 && timer < 1320) // 2PM to 10 PM
            {
                if(timer%12 == 0) {
                    assert screen != null;
                    screen.setDarker();
                }

            }
            if(timer<=360 || timer >= 1320) // Night
            {
                assert screen != null;
                screen.setNight();
            }
            if(timer == 1440) // resets timer, makes new day, refreshes transformations.
            {
                days++;
                timer = 0;
                for(Vampire v:vampList)
                {
                    v.transofrms = 1;
                }
                for(Hunter h: huntList)
                {
                    h.recruitments =1;
                }
            }
            if((villList.isEmpty() && huntList.isEmpty())|| vampList.isEmpty()) // decides who is winner and end sim.
            {
                System.out.println("K-Vamps: "+killedVamps);
                System.out.println("T-Vamps: "+transformedToVamp);
                System.out.println("K-Hunts: "+killedHunters);
                System.out.println("T-Hunts: "+transformedToHunt);
                System.out.println("K-Vills: "+killedVillagers);
                System.out.println("Days: "+days);

                if(huntList.isEmpty())
                    winners = "Wampiry";
                else
                    winners = "Lowcy";

                try { // simulation ends, window is disposed and window with results is created.
                    sleep(1000);

                    frame.dispose();
                    new Summary(totalTimer,days,killedVillagers,killedVamps,killedHunters,transformedToHunt,transformedToVamp,winners,villHistogram,vampHistogram,huntHistogram,villkillHistogram,vampkillHistogram,huntkillHistogram,vampTransHistogram,huntTransHistogram,dayHistory);
                    wait();


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try
                    {
                        sleep(200/speed);
                    }
                    catch (Exception E) {
                        throw new RuntimeException(E);
                    }
            if(screen != null)
                screen.repaintCanva();// repaints canva

        }
    }

    /**
     * Method that checks if tile is occupied. If it's not hostile, then hunter tries to recruit villager
     * If action is successful villagers is removed from list and new hunter is added to list.
     * @param h contains current hunter with all his traits
     */
    private void HunterCheckRecruitment(Hunter h)
    {
        if(!currentTile.isHostileToGood() && !currentTile.villagers.isEmpty())
        {

            Villager recruit = currentTile.hunterRecruitment(h);
            if(recruit.type == 2)
            {
                currentTile.removeVillager(recruit);
                villList.remove(recruit);

                Hunter newHunter = new Hunter(h.unit,h.power/2,huntList.size()+huntClone.size());
                newHunter.recruitments = 0;
                huntClone.add(newHunter);
                currentTile.addHunter(newHunter);

                transformedToHunt++;

            }
        }

    }

    /**
     * checks if current tile is occupied.
     * if it's occupied by villager, then 2 options are possible - villager is killed or transformed into vampire
     * if occupied by hunter, then hunter can be transformed or killed, or vampire can be killed.
     * @param v Current vampire with all traits
     */
    private void VampCheckHostility(Vampire v)
    {
        if(currentTile.isHostileToEvil())
        {
            if(!currentTile.hunters.isEmpty())
            {
                Hunter attackedHunter = currentTile.vampireFightHunter(v);
                if(attackedHunter.type == 1) // hunter tranforms into vampire
                {
                    currentTile.removeHunter(attackedHunter);
                    huntList.remove(attackedHunter);

                    Vampire newVamp = new Vampire(v.unit, (v.power+attackedHunter.power)/2,vampList.size()+vampClone.size());
                    newVamp.transofrms=0;
                    vampClone.add(newVamp);
                    currentTile.addVampier(newVamp);

                    transformedToVamp++;
                }
                if(attackedHunter.type == 3) // hunter killed :)
                {
                    currentTile.removeHunter(attackedHunter);
                    huntList.remove(attackedHunter);
                    killedHunters++;
                }
                if(attackedHunter.type == 2) // vampire killed :(
                {
                    currentTile.removeVampire(v);
                    vampTrash.add(v);
                    killedVamps++;
                }
            }
            if(!currentTile.villagers.isEmpty())
            {
                Villager attackedVillager = currentTile.vampireFightVillager(v);
                if(attackedVillager.type == 1) // villager transformed
                {
                    currentTile.removeVillager(attackedVillager);
                    villList.remove(attackedVillager);

                    Vampire newVamp = new Vampire(v.unit, v.power/2,vampList.size()+vampClone.size());
                    newVamp.transofrms = 0;
                    vampClone.add(newVamp);
                    currentTile.addVampier(newVamp);

                    transformedToVamp++;
                }
                if(attackedVillager.type == 3) // villager killed
                {
                    currentTile.removeVillager(attackedVillager);
                    villList.remove(attackedVillager);

                    killedVillagers++;
                }
            }
        }
    }

}
