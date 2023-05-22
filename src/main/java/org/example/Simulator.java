package org.example;
//TODO Stworzenie listy postaci, nadanie im cech wg ustawien z maina, ustawienie dnia i nocy. jakieś liczniki może ?

import java.util.ArrayList;

public class Simulator extends Thread
{

    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();
    ArrayList<Villager> villList = new ArrayList<>();
    GUI screen;
    int speed;


    Simulator(GUI gui, int numOfVamps, int numOfHunt, int numOfVill,int power,int unit,int speed,int huntPower,int huntUnit)
    {
        this.speed = speed;
        screen = gui;
        for(int i = 0;i<numOfVamps;i++)
        {
            vampList.add(new Vampire(unit,power));
        }
        for(int i = 0;i<numOfHunt;i++)
        {
            huntList.add(new Hunter(huntUnit,huntPower));
        }
        for(int i = 0;i<numOfVill;i++)
        {
            villList.add(new Villager());
        }

    }
    @Override
    public void run()
    {
        while(true)
        {

            for (Vampire v: vampList)
            {
                v.move();
            }

            for (Hunter h: huntList)
            {
                h.move();
            }

            for (Villager n: villList)
            {
                n.move();
            }
            screen.setxy(vampList, huntList, villList);
            try
                    {
                        sleep(100/speed);
                    }
                    catch (Exception E) {
                        throw new RuntimeException(E);
                    }
            screen.repaintCanva();

        }
    }

}
