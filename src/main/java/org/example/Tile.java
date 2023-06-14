package org.example;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class containing information about what happens on current tile
 * Here happens combat
 */
public class Tile
{
    /**
     * Position x and y of tile
     */
    int x,y;
    /**
     * type of tile
     */
    int type;

    /**
     * Id number of tile
     */
    int id;
    /**
     * To generate random outcome
     */
    Random rand;
    /**
     * Boolean that tells if tile is occupied
     */
    boolean isOccupied;
    /**
     * List of vampires on tile
     */
    ArrayList <Vampire> vampires = new ArrayList<>();
    /**
     * List of hunters on tile
     */
    ArrayList <Hunter> hunters = new ArrayList<>();
    /**
     * List of villagers on tile
     */
    ArrayList <Villager> villagers = new ArrayList<>();

    /**
     * Default constructor
     * @param x position x
     * @param y position y
     * @param type type of tile
     * @param id id of tile
     */
    Tile(int x, int y,int type, int id)
    {
        rand = new Random();
        this.x= x;
        this.y= y;
        this.type = type;
        this.id= id;
    }

    /**
     * Method used to add vampire to this tile
     * @param entity vampire to be added
     */

    void addVampier(Vampire entity)
    {
        vampires.add(entity);
        isOccupied=true;
    }
    /**
     * Method used to add hunter to this tile
     * @param entity hunter to be added
     */
    void addHunter(Hunter entity)
    {
        hunters.add(entity);
        isOccupied=true;
    }
    /**
     * Method used to add villager to this tile
     * @param entity villager to be added
     */
    void addVillager(Villager entity)
    {
        villagers.add(entity);
        isOccupied=true;
    }
    /**
     * Method used to remove vampire from this tile
     * @param entity vampire to be removed
     */
    void removeVampire(Vampire entity)
    {
        vampires.remove(entity);
        if(villagers.isEmpty() && hunters.isEmpty() && vampires.isEmpty())
            isOccupied=false;
    }
    /**
     * Method used to remove hunter from this tile
     * @param entity hunter to be removed
     */
    void removeHunter(Hunter entity)
    {
        hunters.remove(entity);
        if(villagers.isEmpty() && hunters.isEmpty() && vampires.isEmpty())
            isOccupied=false;
    }
    /**
     * Method used to remove villager from this tile
     * @param entity villager to be removed
     */
    void removeVillager(Villager entity)
    {
        villagers.remove(entity);
        if(villagers.isEmpty() && hunters.isEmpty() && vampires.isEmpty())
            isOccupied=false;
    }

    /**
     * Checks if it's hostile to vampires
     * @return boolean value
     */
    boolean isHostileToEvil()
    {
        if(!isOccupied)
            return false;
        if(!hunters.isEmpty() || !villagers.isEmpty())
            return true;
        return false;
    }

    /**
     * Checks if it's hostile to villagers and hunters
     * @return boolean value
     */
    boolean isHostileToGood()
    {
        if(!isOccupied)
            return false;
        if(!vampires.isEmpty())
            return true;
        return false;
    }

    /**
     * method that returns outcome of combat between vampire and villager
     * @param vamp current vampire
     * @return villager with some type value
     */

    Villager vampireFightVillager(Vampire vamp)
    {
        Villager charakter=villagers.get(0);
        if(vamp.transofrms == 1 && tranform(vamp))
        {
            vamp.transofrms = 0;
            charakter.type = 1;
        }
        else
        {
            charakter.type = 3;
            vamp.addKill();
        }

        return charakter;
    }
    /**
     * method that returns outcome of combat between vampire and hunter
     * @param vamp current vampire
     * @return hunter with some type value
     */

    Hunter vampireFightHunter(Vampire vamp)
    {
        Hunter charakter = hunters.get(0);
        if(vamp.transofrms == 1 && tranform(vamp) && charakter.power < vamp.power)
        {
            vamp.transofrms = 0;

            charakter.type = 1;
            return charakter;
        }
        if(charakter.power == vamp.power)
        {
            int i = rand.nextInt(2)%2;
            if(i == 0)
            {
                charakter.type = 3;
                vamp.addKill();
            }

        }
        if(charakter.power < vamp.power)
        {
            charakter.type = 3;
            vamp.addKill();
        }
//        else
//            charakter.addKill();
        return charakter;
    }
    /**
     * method that returns outcome of recruitment between hunter and villager
     * @param hunter current hunter
     * @return villager with some type value
     */
    Villager hunterRecruitment(Hunter hunter)
    {
        Villager recruit = villagers.get(0);
        if(hunter.recruitments == 1 && succesfulRecru(hunter))
        {
            hunter.recruitments = 0;
            recruit.type = 2;
            return recruit;
        }
        return recruit;
    }

    /**
     * Method that decides whether recruitment was successful
     * @param h current hunter
     * @return boolean value
     */
    private boolean succesfulRecru(Hunter h)
    {
        if(h.unit == 0)
            return false;
        if(h.unit == 100)
            return true;
        if(rand.nextInt(100) <=h.unit)
            return true;
        return false;
    }


    /**
     * Method that decides whether transformation was successful
     * @param vamp current vampire
     * @return boolean value
     */
    private boolean tranform(Vampire vamp)
    {
        if(vamp.unit == 0)
            return false;
        if(vamp.unit == 100)
            return true;
        if(rand.nextInt(100) <= vamp.unit)
            return true;
        return false;
    }



}
