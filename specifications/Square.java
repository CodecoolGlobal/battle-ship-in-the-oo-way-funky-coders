public class Square
{
    // Global Vars
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    
    // Instance Variables
    private boolean hasShip;
    private int status;
    private int lengthOfShip;
    private int directionOfShip;
    
    // Square constructor. 
    public Square()
    {
        // Set initial values
        status = 0;
        hasShip = false;
        lengthOfShip = -1;
        directionOfShip = -1;
    }

    // Was this Square a hit?
    public boolean checkHit()
    {
        if (status == HIT)
            return true;
        else
            return false;
    }

    // Was this square a miss?
    public boolean checkMiss()
    {
        if (status == MISSED)
            return true;
        else
            return false;
    }

    // Was this square unguessed?
    public boolean isUnguessed()
    {
        if (status == UNGUESSED)
            return true;
        else
            return false;
    }

    // Mark this square a hit.
    public void markHit()
    {
        setStatus(HIT);
    }

    // Mark this square a miss.
    public void markMiss()
    {
        setStatus(MISSED);
    }

    // Return whether or not this square has a ship.
    public boolean hasShip()
    {
        return hasShip;
    }

    // Set the value of whether this square has a ship.
    public void setShip(boolean val)
    {
        this.hasShip = val;
    }

    // Set the status of this Square.
    public void setStatus(int status)
    {
        this.status = status;
    }

    // Get the status of this Square.
    public int getStatus()
    {
        return status;
    }
    
    public int getLengthOfShip()
    {
        return lengthOfShip; 
    }
    
    public void setLengthOfShip(int val)
    {
        lengthOfShip = val;
    }
    
    public int getDirectionOfShip()
    {
        return directionOfShip; 
    }
    
    public void setDirectionOfShip(int val)
    {
        directionOfShip = val;
    }
}