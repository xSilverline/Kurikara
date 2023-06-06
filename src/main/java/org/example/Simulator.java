package org.example;
//TODO Stworzenie listy postaci, nadanie im cech wg ustawien z maina, ustawienie dnia i nocy. jakieś liczniki może ?

import java.util.ArrayList;

public class Simulator extends Thread
{

    ArrayList<Vampire> vampList = new ArrayList<>();
    ArrayList<Hunter> huntList = new ArrayList<>();
    ArrayList<Villager> villList = new ArrayList<>();
    ArrayList<Vampire> vampClone = new ArrayList<>();
    ArrayList<Vampire> vampTrash = new ArrayList<>();
    ArrayList<Hunter> huntClone = new ArrayList<>();

    ArrayList<Integer> vampHistogram = new ArrayList<>();
    ArrayList<Integer> huntHistogram = new ArrayList<>();
    ArrayList<Integer> villHistogram = new ArrayList<>();
    GUI screen;
    int speed;
    int currentX;
    int currentY;
    Tile currentTile;

    int timer;
    int totalTimer;
    int days;
    int transformedToVamp;
    int transformedToHunt;
    int killedVamps;
    int killedVillagers;
    int killedHunters;

    Map map;


    Simulator(GUI gui,Map map, int numOfVamps, int numOfHunt, int numOfVill,int power,int unit,int speed,int huntPower,int huntUnit)
    {
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

    @Override
    public synchronized void run()
    {
        while(true)
        {
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
            vampList.addAll(vampClone);
            vampList.removeAll(vampTrash);
            vampTrash.clear();
            vampClone.clear();
            screen.setxy(vampList, huntList, villList);
            vampHistogram.add(vampList.size());
            huntHistogram.add(huntList.size());
            villHistogram.add(villList.size());
            timer++;
            totalTimer++;
            if(timer == 3600)
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
                System.err.println(days);
            }
            if((villList.isEmpty() && huntList.isEmpty())|| vampList.isEmpty())
            {
                System.out.println("K-Vamps: "+killedVamps);
                System.out.println("T-Vamps: "+transformedToVamp);
                System.out.println("K-Hunts: "+killedHunters);
                System.out.println("T-Hunts: "+transformedToHunt);
                System.out.println("K-Vills: "+killedVillagers);
                System.out.println("Days: "+days);

                if(villList.isEmpty())
                    System.out.println("Winner: Vamps");
                else
                    System.out.println("Winner: Hunters");

                try {
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
            screen.repaintCanva();

        }
    }

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
                System.err.println("REKRU!!!");
            }
        }

    }

    private void VampCheckHostility(Vampire v)
    {
        if(currentTile.isHostileToEvil())
        {
            if(!currentTile.hunters.isEmpty())
            {
                Hunter attackedHunter = currentTile.vampireFightHunter(v);
                if(attackedHunter.type == 1)
                {
                    currentTile.removeHunter(attackedHunter);
                    huntList.remove(attackedHunter);
//
                    Vampire newVamp = new Vampire(v.unit, (v.power+attackedHunter.power)/2,vampList.size()+vampClone.size());
                    newVamp.transofrms=0;
                    vampClone.add(newVamp);
                    currentTile.addVampier(newVamp);

                    transformedToVamp++;
                }
                if(attackedHunter.type == 3)
                {
                    currentTile.removeHunter(attackedHunter);
                    huntList.remove(attackedHunter);
                    killedHunters++;
                }
                if(attackedHunter.type == 2)
                {
                    currentTile.removeVampire(v);
                    vampTrash.add(v);
                    killedVamps++;
                }
            }
            if(!currentTile.villagers.isEmpty())
            {
                Villager attackedVillager = currentTile.vampireFightVillager(v);
                if(attackedVillager.type == 1)
                {
                    currentTile.removeVillager(attackedVillager);
                    villList.remove(attackedVillager);

                    Vampire newVamp = new Vampire(v.unit, v.power/2,vampList.size()+vampClone.size());
                    newVamp.transofrms = 0;
                    vampClone.add(newVamp);
                    currentTile.addVampier(newVamp);

                    System.err.println("Transformer! "+vampList.size()+" "+vampClone.size());
                    transformedToVamp++;
                }
                if(attackedVillager.type == 3)
                {
                    currentTile.removeVillager(attackedVillager);
                    villList.remove(attackedVillager);

                    System.err.println("Deded! "+villList.size());
                    killedVillagers++;
                }
            }
        }
    }

}
