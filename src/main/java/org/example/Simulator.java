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
    int currentX;
    int currentY;
    Tile currentTile;

    Map map;


    Simulator(GUI gui,Map map, int numOfVamps, int numOfHunt, int numOfVill,int power,int unit,int speed,int huntPower,int huntUnit)
    {
        this.map = map;
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
                currentX= v.getX()/10;
                currentY= v.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                currentTile.removeEntity(v);

                v.move();

                currentX= v.getX()/10;
                currentY= v.getY()/10;
                currentTile= map.grid.get(currentX+currentY*100);
                currentTile.addEntity(v);

                v.setMove(map.grid.get(currentX+(currentY-1)*100).type,map.grid.get(currentX+(currentY+1)*100).type,map.grid.get(currentX-1+currentY*100).type,map.grid.get(currentX+1+currentY*100).type);


            }

            for (Hunter h: huntList)
            {
                h.move();
            }

            for (Villager n: villList)
            {
                n.move();
            }
            screen.setxy(vampList, huntList, villList,currentTile);
            try
                    {
                        sleep(10000/speed);
                    }
                    catch (Exception E) {
                        throw new RuntimeException(E);
                    }
            screen.repaintCanva();

        }
    }

}
