package org.example;

public class Vampire
{
    Movement movement;
    int x = 900, y = 900;
    int tx = 10, ty=10;
    int unit,power;
    Vampire(int unit,int power)
    {
         movement = new Movement();
        this.unit = unit;
        this.power = power;
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
