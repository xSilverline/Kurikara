package org.example;

public abstract class Charakter
{
    Movement movement;
    int x , y ;
    int tx = 10, ty = 10;
    int unit,power;
    int type;
    int left,right,forw,back;

    Charakter()
    {
        movement = new Movement();
    }

    public void move()
    {

    }
    int getType() {
        return type;
    }

    void setMove(int forw, int back, int left, int right)
    {
        this.left=left;
        this.right = right;
        this.forw = forw;
        this.back = back;
    }

}
