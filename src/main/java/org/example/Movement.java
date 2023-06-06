package org.example;

import java.util.Random;

public class Movement
{
    Random rand = new Random();
    int x = 500, y = 500;
    int tx = 10, ty=10;
    int lastmove;
    Movement()
    {
    }
    public void goodmove(int x, int y, int left, int right, int up, int down,int lastmove)
    {
        this.x = x;
        this.y = y;
        this.lastmove = lastmove;
        switch (lastmove)
        {
            case 0 ->{
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
            case 1 ->{
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
            case 2 ->{
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
            case 3 ->{
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

    private void goUp(int y)
    {
        this.y=y-10;
        lastmove = 0;
    }
    private void goDown(int y)
    {
        this.y=y+10;
        lastmove = 1;
    }
    private void goLeft(int x)
    {
        this.x = x-10;
        lastmove = 2;
    }
    private void goRight(int x)
    {
        this.x = x+10;
        lastmove = 3;
    }
    private void universalMove(int x, int y)
    {
        this.x=x+tx;
        this.y=y+ty;
        int i = rand.nextInt(4)%4;
        switch (i) {
            case 0 -> {
                tx = 10;
                ty = 0;
            }
            case 1 -> {
                tx = -10;
                ty = 0;
            }
            case 2 -> {
                tx = 0;
                ty = 10;
            }
            case 3 -> {
                tx = 0;
                ty = -10;
            }
            default -> {
            }
        }

    }

    private boolean isGoodLeftBlocked(int left)
    {
        if(left == 0 || left == 3)
            return true;
        return false;
    }
    private boolean isEvilLeftBlocked(int left)
    {
        if(left == 0 || left == 4 || left == 2)
            return true;
        return false;
    }

    private boolean isGoodRightBlocked(int right)
    {
        if(right == 0 || right == 3)
            return true;
        return false;
    }
    private boolean isEvilRightBlocked(int right)
    {
        if(right == 0 || right == 4 || right == 2)
            return true;
        return false;
    }

    private boolean isGoodUpBlocked(int up)
    {
        if(up == 0 || up == 3)
            return true;
        return false;
    }
    private boolean isEvilUpBlocked(int up)
    {
        if(up == 0 || up == 4 || up == 2)
            return true;
        return false;
    }
    private boolean isGoodDownBlocked(int down)
    {
        if(down == 0 || down == 3)
            return true;
        return false;
    }
    private boolean isEvilDownBlocked(int down)
    {
        if(down == 0 || down == 4 || down == 2)
            return true;
        return false;
    }



}
