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
    public void goodmove(int x, int y, int left, int right, int up, int down)
    {
        universalMove(x,y);

        if((x<=10 && tx<0) || (x>=990 && tx>0))
            tx*=-1;
        if((y<=10 && ty<0) || (y>=990 && ty>0))
            ty*=-1;
    }

    public void evilmove(int x, int y, int left, int right, int up, int down, int lastmove)
    {
        this.x = x;
        this.y = y;
        this.lastmove = lastmove;
        switch (lastmove)
        {
            case 0 ->{
                if(!isEvilUpBlocked(up) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goUp(y);
                else if (isEvilUpBlocked(up) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goDown(y);
                else if (!isEvilUpBlocked(up) && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromThree(up,left,right,x,y);
                else if (!isEvilUpBlocked(up) && !isEvilLeftBlocked(left))
                    decideFromTwo(up,left,x,y);
                else if (!isEvilUpBlocked(up) && !isEvilRightBlocked(right))
                    decideFromTwo(up,right,x,y);
                else if (!isEvilRightBlocked(right))
                    goRight(x);
                else if (!isEvilLeftBlocked(left))
                    goLeft(x);

            }
            case 1 ->{
                if(!isEvilDownBlocked(down) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goDown(y);
                else if (isEvilDownBlocked(down) && isEvilLeftBlocked(left) && isEvilRightBlocked(right))
                    goUp(y);
                else if (!isEvilDownBlocked(down) && !isEvilLeftBlocked(left) && !isEvilRightBlocked(right))
                    decideFromThree(down,left,right,x,y);
                else if (!isEvilDownBlocked(down) && !isEvilLeftBlocked(left))
                    decideFromTwo(down,left,x,y);
                else if (!isEvilDownBlocked(down) && !isEvilRightBlocked(right))
                    decideFromTwo(down,right,x,y);
                else if (!isEvilRightBlocked(right))
                    goRight(x);
                else if (!isEvilLeftBlocked(left))
                    goLeft(x);
            }
            case 2 ->{
                if(!isEvilLeftBlocked(left) && isEvilUpBlocked(up) && isEvilDownBlocked(down))
                    goLeft(x);
                else if (isEvilLeftBlocked(left) && isEvilUpBlocked(up) && isEvilDownBlocked(down))
                    goRight(x);
                else if (!isEvilLeftBlocked(left) && !isEvilUpBlocked(up) && !isEvilDownBlocked(down))
                    decideFromThree(left,up,down,x,y);
                else if (!isEvilLeftBlocked(left) && !isEvilUpBlocked(up))
                    decideFromTwo(left,up,x,y);
                else if (!isEvilLeftBlocked(left) && !isEvilDownBlocked(down))
                    decideFromTwo(left,down,x,y);
                else if (!isEvilUpBlocked(up))
                    goUp(y);
                else if (!isEvilDownBlocked(down))
                    goDown(y);
            }
            case 3 ->{
                if(!isEvilRightBlocked(right) && isEvilUpBlocked(up) && isEvilDownBlocked(down))
                    goRight(x);
                else if (isEvilRightBlocked(right) && isEvilUpBlocked(up) && isEvilDownBlocked(down))
                    goLeft(x);
                else if (!isEvilRightBlocked(right) && !isEvilUpBlocked(up) && !isEvilDownBlocked(down))
                    decideFromThree(right,up,down,x,y);
                else if (!isEvilRightBlocked(right) && !isEvilUpBlocked(up))
                    decideFromTwo(right,up,x,y);
                else if (!isEvilRightBlocked(right) && !isEvilDownBlocked(down))
                    decideFromTwo(right,down,x,y);
                else if (!isEvilUpBlocked(up))
                    goUp(y);
                else if (!isEvilDownBlocked(down))
                    goDown(y);
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
