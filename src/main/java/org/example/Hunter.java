package org.example;

public class Hunter
{
    Movement movement;
    int x = 100, y = 100;
    int tx = 10, ty=10;
    Hunter()
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
