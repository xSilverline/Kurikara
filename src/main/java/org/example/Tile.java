package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Tile
{
    int x,y;
    int type;
    int id;
    boolean isOccupied;
    ArrayList <Charakter> entities = new ArrayList<>();

    Tile(int x, int y,int type, int id)
    {
        this.x= x;
        this.y= y;
        this.type = type;
        this.id= id;
    }

    void addEntity(Charakter entity)
    {
        entities.add(entity);
        isOccupied=true;
    }

    void removeEntity(Charakter entity)
    {
        entities.remove(entity);
        if(entities.isEmpty())
            isOccupied=false;
    }

    int getType()
    {
        return type;
    }

    boolean isHostile(Charakter entity)
    {
        if(!isOccupied)
            return false;

        for(Charakter charakter:entities)
        {
            if(charakter.getType() == 1)
            {
                if(entity.getType() == 2 || entity.getType() == 3)
                    return true;
            }
            if(charakter.getType() == 2 || entity.getType() == 3)
            {
                if(entity.getType() == 1)
                    return true;
            }
        }

        return false;

    }



}
