package org.example;

public class Vampire extends Charakter {
    Movement movement;
    int x = 910, y = 940;
    int tx = 10, ty=10;
    int unit,power;
    int type = 1;
    int left,right,forw,back;
    int lastmove;
    Vampire(int unit,int power)
    {
        movement = new Movement();
        this.unit = unit;
        this.power = power;
        left=3;
        right=3;
        forw=3;
        back=3;
        lastmove = 0;
    }

    public void move()
    {
        movement.evilmove(x,y,left,right,forw,back,lastmove);
        lastmove = movement.lastmove;
        x = movement.x;
        y = movement.y;
        tx = movement.tx;
        ty = movement.ty;
    }

    void setMove(int forw, int back, int left, int right)
    {
        this.left=left;
        this.right = right;
        this.forw = forw;
        this.back = back;
    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }

}
