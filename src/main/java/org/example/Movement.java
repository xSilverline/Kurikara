package org.example;

import java.util.Random;

public class Movement
{
    Random rand = new Random();
    int x = 500, y = 500;
    int tx = 10, ty=10;
    Movement()
    {
    }
    public void move(int x, int y)
    {
        this.x=x+tx;
        this.y=y+ty;
        int i = rand.nextInt(4)%4;
        switch (i) {
            case 0 -> {
                tx = 10;
                ty = 0;
            }
            case 1 -> {
                tx = -10;
                ty = 0;
            }
            case 2 -> {
                tx = 0;
                ty = 10;
            }
            case 3 -> {
                tx = 0;
                ty = -10;
            }
            default -> {
            }
        }

        if((x<=10 && tx<0) || (x>=1010 && tx>0))
            tx*=-1;
        if((y<=10 && ty<0) || (y>=1010 && ty>0))
            ty*=-1;
    }


}
