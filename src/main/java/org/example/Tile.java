package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Tile
{
    int x,y;
    int type;
    int id;
    Random rand;
    boolean isOccupied;
    ArrayList <Vampire> vampires = new ArrayList<>();
    ArrayList <Hunter> hunters = new ArrayList<>();
    ArrayList <Villager> villagers = new ArrayList<>();

    Tile(int x, int y,int type, int id)
    {
        rand = new Random();
        this.x= x;
        this.y= y;
        this.type = type;
        this.id= id;
    }

    void addVampier(Vampire entity)
    {
        vampires.add(entity);
        isOccupied=true;
    }
    void addHunter(Hunter entity)
    {
        hunters.add(entity);
        isOccupied=true;
    }
    void addVillager(Villager entity)
    {
        villagers.add(entity);
        isOccupied=true;
    }

    void removeVampire(Vampire entity)
    {
        vampires.remove(entity);
        if(villagers.isEmpty() && hunters.isEmpty() && vampires.isEmpty())
            isOccupied=false;
    }
    void removeHunter(Hunter entity)
    {
        hunters.remove(entity);
        if(villagers.isEmpty() && hunters.isEmpty() && vampires.isEmpty())
            isOccupied=false;
    }
    void removeVillager(Villager entity)
    {
        villagers.remove(entity);
        if(villagers.isEmpty() && hunters.isEmpty() && vampires.isEmpty())
            isOccupied=false;
    }

    int getType()
    {
        return type;
    }

    boolean isHostileToEvil()
    {
        if(!isOccupied)
            return false;
        if(!hunters.isEmpty() || !villagers.isEmpty())
            return true;
        return false;
    }

    boolean isHostileToGood()
    {
        if(!isOccupied)
            return false;
        if(!vampires.isEmpty())
            return true;
        return false;
    }

    Villager vampireFightVillager(Vampire vamp)
    {
        Villager charakter=villagers.get(0);
        if(vamp.transofrms == 1 && tranform(vamp))
        {
            vamp.transofrms = 0;
            charakter.type = 1;
        }
        else
            charakter.type = 3;
        return charakter;
    }

    Hunter vampireFightHunter(Vampire vamp)
    {
        Hunter charakter = hunters.get(0);
        if(vamp.transofrms == 1 && tranform(vamp) && charakter.power < vamp.power)
        {
            vamp.transofrms = 0;
            charakter.type = 1;
            return charakter;
        }
        if(charakter.power < vamp.power)
        {
            charakter.type = 3;
        }
        return charakter;
    }



    private boolean tranform(Vampire vamp)
    {
        if(rand.nextInt(100) <= vamp.unit)
            return true;
        return false;
    }



}
