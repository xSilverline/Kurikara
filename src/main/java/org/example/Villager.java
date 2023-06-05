package org.example;

public class Villager extends Charakter
{
    Movement movement;
    int x = 500, y = 500;
    int tx = 10, ty=10;
    int unitID;

    int left,right,forw,back;
    int type = 0;
    int leftid,rightid,upid,downid;
    int lastmove;
    Villager(int unitID)
    {
        this.unitID = unitID;
        movement = new Movement();
        left=5;
        right=5;
        forw=5;
        back=5;
        lastmove = 2;

    }

    public void move()
    {
        movement.goodmove(x,y,left,right,forw,back,lastmove);
        lastmove = movement.lastmove;
        x = movement.x;
        y = movement.y;
        tx = movement.tx;
        ty = movement.ty;
    }

    void setMove(int forw,int upid, int back, int downid,int left, int leftid, int right, int rightid)
    {
        this.left=left;
        this.right = right;
        this.forw = forw;
        this.back = back;
        this.upid = upid;
        this.downid = downid;
        this.leftid = leftid;
        this.rightid = rightid;
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
