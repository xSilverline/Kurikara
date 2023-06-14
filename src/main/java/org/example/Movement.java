package org.example;

import java.util.Random;

/**
 * Class Movement that is responsible for correct and logical movement of entities
 */
public class Movement
{
    /**
     * Random number generator - used in case when there are too many paths and it's hard to decide
     */
    Random rand = new Random();
    /**
     * Default position , overwritten by each entity class
     */
    int x = 500, y = 500;
    /**
     * Default step, overwritten by each entity class
     */
    int tx = 10, ty=10;
    /**
     * Saves temporary last move that entity has done.
     */
    int lastmove;

    /**
     * Default constructor
     */
    Movement()
    {
    }

    /**
     * Logic responsible for good entity movement - villagers and hunters. It allows to go into houses and church
     * @param x x position
     * @param y y position
     * @param left tile type on left
     * @param right tile type on right
     * @param up tile type above
     * @param down tile type bellow
     * @param lastmove type of last move made by entity
     */
    public void goodmove(int x, int y, int left, int right, int up, int down,int lastmove)
    {
        this.x = x;
        this.y = y;
        this.lastmove = lastmove;
        switch (lastmove)
        {
            case 0 ->{ // going up
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && ! isGoodRightBlocked(right))
                    decideFromThree(0,2,3,x,y); // Case - no path blocked, crossroad
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(0,3,x,y); //Case - left is blocked, go up or right
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    decideFromTwo(0,2,x,y); // Case - right is blocked, go up or left
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down)  && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(2,3,x,y); // Case - Up is blocked, go left or right
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goLeft(x); // Case - only left
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goRight(x); // Case - only right
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goUp(y); // Case - only Up
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goDown(y); // Case - go back


            }
            case 1 ->{ // going down
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && ! isGoodRightBlocked(right))
                    decideFromThree(1,2,3,x,y); // Case - no path blocked, crossroad
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(1,3,x,y); //Case - left is blocked, go down or right
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    decideFromTwo(1,2,x,y); // Case - right is blocked, go down or left
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down)  && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(2,3,x,y); // Case - down is blocked, go left or right
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goLeft(x); // Case - only left
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goRight(x); // Case - only right
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goDown(y); // Case - only down
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goUp(y); // Case - go back

            }
            case 2 ->{ // going left
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && ! isGoodRightBlocked(right))
                    decideFromThree(0,1,2,x,y); // Case - no path blocked, crossroad
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(0,2,x,y); //Case - down is blocked, go up or left
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(0,1,x,y); // Case - left is blocked, go up or down
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down)  && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(2,1,x,y); // Case - Up is blocked, go left or down
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goDown(y); // Case - only down
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goUp(y); // Case - only up
                if(isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goLeft(x); // Case - only left
                if(isGoodUpBlocked(up) && isGoodDownBlocked(down) && isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goRight(x); // Case - go back

            }
            case 3 ->{ // going right
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && ! isGoodRightBlocked(right))
                    decideFromThree(0,1,3,x,y); // Case - no path blocked, crossroad
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(0,3,x,y); //Case - down is blocked, go up or right
                if(!isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    decideFromTwo(0,1,x,y); // Case - right is blocked, go up or down
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down)  && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    decideFromTwo(3,1,x,y); // Case - Up is blocked, go right or down
                if(isGoodUpBlocked(up) && !isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goDown(y); // Case - only down
                if(!isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goUp(y); // Case - only up
                if(isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && !isGoodRightBlocked(right))
                    goRight(x); // Case - only right
                if(isGoodUpBlocked(up) && isGoodDownBlocked(down) && !isGoodLeftBlocked(left) && isGoodRightBlocked(right))
                    goLeft(x); // Case - go back

            }

        }
    }

    /**
     * Logic responsible for vampires movement. It doesn't allow to go into houses and church
     * @param x x position
     * @param y y position
     * @param left tile type on left
     * @param right tile type on right
     * @param up tile type above
     * @param down tile type bellow
     * @param lastmove type of last move made by entity
     */
    public void evilmove(int x, int y, int left, int right, int up, int down, int lastmove)
    {
        this.x = x;
        this.y = y;
        this.lastmove = lastmove;
        switch (lastmove)
        {
            case 0 ->{
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && ! isEvilRightBlocked(right))
                    decideFromThree(0,2,3,x,y); // Case - no path blocked, crossroad
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(0,3,x,y); //Case - left is blocked, go up or right
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    decideFromTwo(0,2,x,y); // Case - right is blocked, go up or left
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down)  && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(2,3,x,y); // Case - Up is blocked, go left or right
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goLeft(x); // Case - only left
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goRight(x); // Case - only right
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goUp(y); // Case - only Up
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goDown(y); // Case - go back


            }
            case 1 ->{
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && ! isEvilRightBlocked(right))
                    decideFromThree(1,2,3,x,y); // Case - no path blocked, crossroad
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(1,3,x,y); //Case - left is blocked, go down or right
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    decideFromTwo(1,2,x,y); // Case - right is blocked, go down or left
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down)  && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(2,3,x,y); // Case - down is blocked, go left or right
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goLeft(x); // Case - only left
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goRight(x); // Case - only right
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goDown(y); // Case - only down
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goUp(y); // Case - go back

            }
            case 2 ->{
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && ! isEvilRightBlocked(right))
                    decideFromThree(0,1,2,x,y); // Case - no path blocked, crossroad
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(0,2,x,y); //Case - down is blocked, go up or left
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(0,1,x,y); // Case - left is blocked, go up or down
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down)  && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(2,1,x,y); // Case - Up is blocked, go left or down
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goDown(y); // Case - only down
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goUp(y); // Case - only up
                if(isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goLeft(x); // Case - only left
                if(isEvilUpBlocked(up) && isEvilDownBlocked(down) && isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goRight(x); // Case - go back

            }
            case 3 ->{
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && ! isEvilRightBlocked(right))
                    decideFromThree(0,1,3,x,y); // Case - no path blocked, crossroad
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(0,3,x,y); //Case - down is blocked, go up or right
                if(!isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    decideFromTwo(0,1,x,y); // Case - right is blocked, go up or down
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down)  && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromTwo(3,1,x,y); // Case - Up is blocked, go right or down
                if(isEvilUpBlocked(up) && !isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goDown(y); // Case - only down
                if(!isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goUp(y); // Case - only up
                if(isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    goRight(x); // Case - only right
                if(isEvilUpBlocked(up) && isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goLeft(x); // Case - go back

            }

        }

    }

    /**
     * Method used to choose from 3 potential paths. RNG
     * @param a direction no. 1
     * @param b direction no. 2
     * @param c direction no. 3
     * @param x x position
     * @param y y position
     */
    private void decideFromThree(int a, int b, int c, int x, int y)
    {
        int i = rand.nextInt(3)%3;
        int chosenDirection;

        switch (i) {
            case 0 -> {
                chosenDirection = a;
            }
            case 1 -> {
                chosenDirection = b;
            }
            case 2 -> {
                chosenDirection = c;
            }
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }
        if(chosenDirection == 0)
            goUp(y);
        if(chosenDirection == 1)
            goDown(y);
        if(chosenDirection == 2)
            goLeft(x);
        if(chosenDirection == 3)
            goRight(x);
    }

    /**
     * Method used to decide from two good paths
     * @param a direction no.1
     * @param b direction no.2
     * @param x position x
     * @param y position y
     */
    private void decideFromTwo(int a, int b, int x, int y)
    {
        int i = rand.nextInt(2)%2;
        int chosenDirection;

        switch (i) {
            case 0 -> {
                chosenDirection = a;
            }
            case 1 -> {
                chosenDirection = b;
            }
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }
        if(chosenDirection == 0)
            goUp(y);
        if(chosenDirection == 1)
            goDown(y);
        if(chosenDirection == 2)
            goLeft(x);
        if(chosenDirection == 3)
            goRight(x);
    }

    /**
     * Method used to move entity above
     * @param y position y
     */
    private void goUp(int y)
    {
        this.y=y-10;
        lastmove = 0;
    }
    /**
     * Method used to move entity bellow
     * @param y position y
     */
    private void goDown(int y)
    {
        this.y=y+10;
        lastmove = 1;
    }
    /**
     * Method used to move entity to left
     * @param x position x
     */
    private void goLeft(int x)
    {
        this.x = x-10;
        lastmove = 2;
    }
    /**
     * Method used to move entity to right
     * @param x position x
     */
    private void goRight(int x)
    {
        this.x = x+10;
        lastmove = 3;
    }

    /**
     * Method checking if left is blocked for villagers and hunters
     * @param left type of tile on left
     * @return boolean value
     */
    private boolean isGoodLeftBlocked(int left)
    {
        if(left == 0 || left == 3)
            return true;
        return false;
    }
    /**
     * Method checking if left is blocked for vampires
     * @param left type of tile on left
     * @return boolean value
     */
    private boolean isEvilLeftBlocked(int left)
    {
        if(left == 0 || left == 4 || left == 2)
            return true;
        return false;
    }

    /**
     * Method checking if right is blocked for villagers and hunters
     * @param right type of tile on right
     * @return boolean value
     */
    private boolean isGoodRightBlocked(int right)
    {
        if(right == 0 || right == 3)
            return true;
        return false;
    }
    /**
     * Method checking if right is blocked for vampires
     * @param right type of tile on right
     * @return boolean value
     */
    private boolean isEvilRightBlocked(int right)
    {
        if(right == 0 || right == 4 || right == 2)
            return true;
        return false;
    }
    /**
     * Method checking if above is blocked for villagers and hunters
     * @param up type of tile above
     * @return boolean value
     */
    private boolean isGoodUpBlocked(int up)
    {
        if(up == 0 || up == 3)
            return true;
        return false;
    }
    /**
     * Method checking if above is blocked for vampires
     * @param up type of tile above
     * @return boolean value
     */
    private boolean isEvilUpBlocked(int up)
    {
        if(up == 0 || up == 4 || up == 2)
            return true;
        return false;
    }
    /**
     * Method checking if bellow is blocked for villagers and hunters
     * @param down type of tile bellow
     * @return boolean value
     */
    private boolean isGoodDownBlocked(int down)
    {
        if(down == 0 || down == 3)
            return true;
        return false;
    }
    /**
     * Method checking if bellow is blocked for vampires
     * @param down type of tile bellow
     * @return boolean value
     */
    private boolean isEvilDownBlocked(int down)
    {
        if(down == 0 || down == 4 || down == 2)
            return true;
        return false;
    }



}
