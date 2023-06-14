package org.example;
/**
 * Class Hunter is used to create and save all data bound with the unit
 */
public class Hunter extends Charakter
{
    /**
     * Initiate movement of entity
     */
    Movement movement;
    /**
     * Integer saving unit ID number
     */
    int unitID;
    /**
     * integers used for initial position ( spawn )
     */
    int x = 50, y = 50;
    /**
     * Integers used for initial move length
     */
    int tx = 10, ty=10;
    /**
     * Integer containing this unit power and probability this unit has to transform other entities into this type
     */
    int power,unit;
    /**
     * Integer containing what move was made last time ( to continue movement in some direction )
     */
    int lastmove;
    /**
     * Integers containing surrounding tiles id numbers.
     */
    int leftid,rightid,upid,downid;
//    int killstreak=0;

    /**
     * Integer containing if transformation is available
     */
    int recruitments = 1;

    /**
     * Integer defining type of entity, 0 = Villager, 1 = Vampire, 2 = Hunter
     */
    int type = 2;
    /**
     * Default constructor
     * @param unit Probability this unit has to transform others
     * @param power Power of this unit
     * @param unitID Unit ID number
     */
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
    /**
     * Method used to move entity in some direction and save new position in memory of this entity
     */
    public void move()
    {
        movement.goodmove(x,y,left,right,forw,back,lastmove);
        lastmove=movement.lastmove;
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

//    void addKill()
//    {
//        killstreak++;
//        if(power<10 && killstreak%10 == 0)
//            power++;
//    }

}
