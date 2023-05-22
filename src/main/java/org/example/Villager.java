package org.example;

public class Villager
{
    Movement movement;
    int x = 500, y = 500;
    int tx = 10, ty=10;
    Villager()
    {
        movement = new Movement();

    }

    public void move()
    {
        movement.move(x,y);
        x = movement.x;
        y = movement.y;
        tx = movement.tx;
        ty = movement.ty;
    }

}
