package org.example;

public class Vampire extends Charakter {
    Movement movement;
    int unitID;
    int x = 910, y = 940;
    int tx = 10, ty=10;
    int unit,power;
    int type = 1;
    int transofrms;
    int left,right,forw,back;
    int leftid,rightid,upid,downid;
    int lastmove;
    int killstreak;
    Vampire(int unit,int power,int unitID)
    {
        this.unitID = unitID;
        movement = new Movement();
        this.unit = unit;
        this.power = power;
        left=3;
        right=3;
        forw=3;
        back=3;
        lastmove = 0;
        transofrms = 1;

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

    void addKill()
    {
        killstreak++;
        if(killstreak%10 == 0 && power<10)
            power++;
    }

}
