package org.example;
//TODO Stworzenie listy postaci, nadanie im cech wg ustawien z maina, ustawienie dnia i nocy. jakieś liczniki może ?

import java.util.ArrayList;

public class Simulator extends Thread
{

    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();
    GUI screen;
    Vampire v1 = new Vampire();
    Vampire v2 = new Vampire();

    Simulator(GUI gui, int numOfVamps, int numOfHunt)
    {
        screen = gui;
        for(int i = 0;i<numOfVamps;i++)
        {
            vampList.add(new Vampire());
        }
        for(int i = 0;i<numOfHunt;i++)
        {
            huntList.add(new Hunter());
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
            screen.setxy(vampList, huntList);
            try
                    {
                        sleep(100);
                    }
                    catch (Exception E) {
                        throw new RuntimeException(E);
                    }
            screen.repaintCanva();

        }
    }

}
