package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map
{
    HashMap <Integer,Tile> grid = new HashMap<Integer, Tile>();
    String fileName = "map1.txt";
    int id;

    Map()
    {
        initializeMapFromFile();
    }

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

    void saveMapToFile()
    {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter writer = new PrintWriter(fileWriter);

            for(int i=0; i<100;i++)
            {
                for(int j=0; j<100;j++)
                {
                    writer.printf(String.valueOf(grid.get(i+j).type)+" ");
                }
                writer.println();
            }
//
//            for(Tile tile: grid)
//            {
//                writer.print((tile.type));
//            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
