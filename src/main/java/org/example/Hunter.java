package org.example;

public class Hunter extends Charakter
{
    Movement movement;
    int x = 100, y = 100;
    int tx = 10, ty=10;
    int power,unit;

    int type = 2;
    Hunter(int unit,int power)
    {
        movement = new Movement();
        this.power=power;
        this.unit=unit;
        left=2;
        right=2;
        forw=2;
        back=2;
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
