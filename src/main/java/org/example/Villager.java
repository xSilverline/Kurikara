package org.example;

public class Villager extends Charakter
{
    Movement movement;
    int x = 500, y = 500;
    int tx = 10, ty=10;

    int left,right,forw,back;
    int type = 0;
    Villager()
    {
        movement = new Movement();
        left=5;
        right=5;
        forw=5;
        back=5;

    }

    public void move()
    {
        movement.goodmove(x,y,left,right,forw,back);
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


}
