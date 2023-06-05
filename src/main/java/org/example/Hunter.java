package org.example;

public class Hunter extends Charakter
{
    Movement movement;
    int unitID;
    int x = 100, y = 100;
    int tx = 10, ty=10;
    int power,unit;
    int lastmove;
    int leftid,rightid,upid,downid;

    int type = 2;
    Hunter(int unit,int power, int unitID)
    {
        this.unitID = unitID;
        movement = new Movement();
        this.power=power;
        this.unit=unit;
        left=2;
        right=2;
        forw=2;
        back=2;
        lastmove=3;
    }
    public void move()
    {
        movement.goodmove(x,y,left,right,forw,back,lastmove);
        lastmove=movement.lastmove;
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
