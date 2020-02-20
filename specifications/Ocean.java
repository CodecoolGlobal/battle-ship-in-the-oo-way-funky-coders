public class Ocean
{
    private Square[][] ocean;
    private int points;

    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    public Ocean()
    {
        if (NUM_ROWS > 26)
        {
            throw new IllegalArgumentException("ERROR! NUM_ROWS CANNOT BE > 26");
        }
        
        ocean = new Square[NUM_ROWS][NUM_COLS];
        
        for (int row = 0; row < ocean.length; row++)
        {
            for (int col = 0; col < ocean[row].length; col++)
            {
                Square tempLoc = new Square();
                ocean[row][col] = tempLoc;
            }
        }
    }
    
    // Mark a hit in this square by calling the markHit method
    // on the Square object.  
    public void markHit(int row, int col)
    {
        ocean[row][col].markHit();
        points++;
    }
    
    // Mark a miss on this square.    
    public void markMiss(int row, int col)
    {
        ocean[row][col].markMiss();
    }
    
    // Set the status of this square object.
    public void setStatus(int row, int col, int status)
    {
        ocean[row][col].setStatus;
    }
    
    // Get the status of this square in the ocean  
    public int getStatus(int row, int col)
    {
        return ocean[row][col].getStatus();
    }
    
    // Return whether or not this Square has already been guessed.
    public boolean alreadyGuessed(int row, int col)
    {
        return !ocean[row][col].isUnguessed();
    }
    
    // Set whether or not there is a ship at this square to the val   
    public void setShip(int row, int col, boolean val)
    {
        ocean[row][col].setShip(val);
    }
    
    // Return whether or not there is a ship here   
    public boolean hasShip(int row, int col)
    {
        return ocean[row][col].hasShip();
    }
    
    // Get the Square object at this row and column position
    public Square get(int row, int col)
    {
        return ocean[row][col];
    }
    
    // Return the number of rows in the Ocean
    public int numRows()
    {
        return NUM_ROWS;
    }

    // Return the number of columns in the ocean
    public int numCols()
    {
        return NUM_COLS;
    }
    
    public void printStatus()
    {
        generalPrintMethod(0);
    }
    
    public void printShips()
    {
        generalPrintMethod(1);
    }
    
    public void printCombined()
    {
        generalPrintMethod(2);
    }
    
    public boolean hasLost()
    {
        if (points >= 17)
            return true;
        else
            return false;
    }
    
    public void addShip(Ship s)
    {
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        int dir = s.getDirection();
        
        if (!(s.isDirectionSet()) || !(s.isSquareSet()))
            throw new IllegalArgumentException("ERROR! Direction or Square is unset/default");
        
        // 0 - hor; 1 - ver
        if (dir == 0) // Hortizontal
        {
            for (int i = col; i < col+length; i++)
            {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                ocean[row][i].setShip(true);
                ocean[row][i].setLengthOfShip(length);
                ocean[row][i].setDirectionOfShip(dir);
            }
        }
        else if (dir == 1) // Vertical
        {
            for (int i = row; i < row+length; i++)
            {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                ocean[i][col].setShip(true);
                ocean[i][col].setLengthOfShip(length);
                ocean[i][col].setDirectionOfShip(dir);
            }
        }
    }
    
    // Type: 0 for status, 1 for ships, 2 for combined
    private void generalPrintMethod(int type)
    {
        System.out.println();
        // Print columns (HEADER)
        System.out.print("  ");
        for (int i = 1; i <= NUM_COLS; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // Print rows
        int endLetterForLoop = (NUM_ROWS - 1) + 65;
        for (int i = 65; i <= endLetterForLoop; i++)
        {
            char theChar = (char) i;
            System.out.print(theChar + " ");
            
            for (int j = 0; j < NUM_COLS; j++)
            {
                if (type == 0) // type == 0; status
                {
                    if (ocean[switchCounterToIntegerForArray(i)][j].isUnguessed())
                        System.out.print("- ");
                    else if (ocean[switchCounterToIntegerForArray(i)][j].checkMiss())
                        System.out.print("O ");
                    else if (ocean[switchCounterToIntegerForArray(i)][j].checkHit())
                        System.out.print("X ");
                }
                else if (type == 1) // type == 1; ships
                {
                    if (ocean[switchCounterToIntegerForArray(i)][j].hasShip())
                    {
                        // System.out.print("X ");
                        if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 2)
                        {
                            System.out.print("D ");
                        }
                        else if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 3)
                        {
                            System.out.print("C ");
                        }
                        else if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 4)
                        {
                            System.out.print("B ");
                        }
                        else if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 5)
                        {
                            System.out.print("A ");
                        }
                    }
                        
                    else if (!(ocean[switchCounterToIntegerForArray(i)][j].hasShip()))
                    {
                        System.out.print("- ");
                    }
                        
                }
                else // type == 2; combined
                {
                    if (ocean[switchCounterToIntegerForArray(i)][j].checkHit())
                        System.out.print("X ");
                    else if (ocean[switchCounterToIntegerForArray(i)][j].hasShip())
                    {
                        // System.out.print("X ");
                        if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 2)
                        {
                            System.out.print("D ");
                        }
                        else if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 3)
                        {
                            System.out.print("C ");
                        }
                        else if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 4)
                        {
                            System.out.print("B ");
                        }
                        else if (ocean[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 5)
                        {
                            System.out.print("A ");
                        }
                    }
                    else if (!(ocean[switchCounterToIntegerForArray(i)][j].hasShip()))
                    {
                        System.out.print("- ");
                    }  
                }
            }
            System.out.println();
        }
    }
    
    public int switchCounterToIntegerForArray (int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 65:    toReturn = 0;
                        break;
            case 66:    toReturn = 1;
                        break;
            case 67:    toReturn = 2;
                        break;
            case 68:    toReturn = 3;
                        break;
            case 69:    toReturn = 4;
                        break;
            case 70:    toReturn = 5;
                        break;
            case 71:    toReturn = 6;
                        break;
            case 72:    toReturn = 7;
                        break;
            case 73:    toReturn = 8;
                        break;
            case 74:    toReturn = 9;
                        break;
            case 75:    toReturn = 10;
                        break;
            case 76:    toReturn = 11;
                        break;
            case 77:    toReturn = 12;
                        break;
            case 78:    toReturn = 13;
                        break;
            case 79:    toReturn = 14;
                        break;
            case 80:    toReturn = 15;
                        break;
            case 81:    toReturn = 16;
                        break;
            case 82:    toReturn = 17;
                        break;
            case 83:    toReturn = 18;
                        break;
            case 84:    toReturn = 19;
                        break;
            case 85:    toReturn = 20;
                        break;
            case 86:    toReturn = 21;
                        break;
            case 87:    toReturn = 22;
                        break;
            case 88:    toReturn = 23;
                        break;
            case 89:    toReturn = 24;
                        break;
            case 90:    toReturn = 25;
                        break;
            default:    toReturn = -1;
                        break;
        }
        
        if (toReturn == -1)
        {
            throw new IllegalArgumentException("ERROR OCCURED IN SWITCH");
        }
        else
        {
            return toReturn;
        }
    }   
}