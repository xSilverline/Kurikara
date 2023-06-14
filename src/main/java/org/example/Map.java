package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class used to initiate map. Contains every tile used. Loads map from file.
 */
public class Map
{
    /**
     * HashMap that contains all tiles. Keeps everything on good position
     */
    HashMap <Integer,Tile> grid = new HashMap<Integer, Tile>();
    /**
     * String that contains map name ( user should be able to choose map, so in update 1.0.2 should be implemented )
     */
    String fileName = "map1.txt";
    /**
     * Counter providing id for each tile
     */
    int id;

    /**
     * Default constructor initializing  new map.
     */
    Map()
    {
        initializeMapFromFile();
    }

    /**
     * Method used to initialize map. It is loading map from file and putting each tile on correct position.
     */
    void initializeMapFromFile()
    {
        id=0;
        try {
            Scanner scanner = new Scanner(new File("map_village_1.txt"));
            for(int i=0; i<100;i++)
            {
                for(int j=0; j<100;j++)
                {
                    grid.put(id,new Tile(j,i,scanner.nextInt(),id));
                    id++;
                }
            }

        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

    }

}
