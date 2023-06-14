package org.example;

/**
 * Class Villager is used to create and save all data bound with the unit
 */
public class Villager extends Charakter
{
    /**
     * Initiate movement of entity
     */
    Movement movement;
    /**
     * integers used for initial position ( spawn )
     */
    int x = 500, y = 500;
    /**
     * Integers used for initial move length
     */
    int tx = 10, ty=10;
    /**
     * Integer saving unit ID number
     */
    int unitID;

    /**
     * Integers used to save what tiles are around this unit
     */
    int left,right,forw,back;
    /**
     * Integer defining type of entity, 0 = Villager, 1 = Vampire, 2 = Hunter
     */
    int type = 0;
    /**
     * Integers containing surrounding tiles id numbers.
     */
    int leftid,rightid,upid,downid;
    /**
     * Integer containing what move was made last time ( to continue movement in some direction )
     */
    int lastmove;

    /**
     * Default constructor
     * @param unitID Unit ID number
     */
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

    /**
     * Method used to move entity in some direction and save new position in memory of this entity
     */
    public void move()
    {
        movement.goodmove(x,y,left,right,forw,back,lastmove);
        lastmove = movement.lastmove;
        x = movement.x;
        y = movement.y;
        tx = movement.tx;
        ty = movement.ty;
    }

    /**
     * Method to set in what direction entity is able to go
     * @param forw tile type above current position
     * @param upid above tile id
     * @param back tile type bellow
     * @param downid bellow tile id
     * @param left tile type on left
     * @param leftid left tile id
     * @param right tile type on right
     * @param rightid right tile id
     */
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

    /**
     * method to get X position
     * @return x position
     */
    int getX()
    {
        return x;
    }

    /**
     * Method to get Y position
     * @return y position
     */
    int getY()
    {
        return y;
    }


}
