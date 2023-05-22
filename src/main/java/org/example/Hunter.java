package org.example;

public class Hunter
{
    Movement movement;
    int x = 100, y = 100;
    int tx = 10, ty=10;
    int power,unit;
    Hunter(int unit,int power)
    {
        movement = new Movement();
        this.power=power;
        this.unit=unit;
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
