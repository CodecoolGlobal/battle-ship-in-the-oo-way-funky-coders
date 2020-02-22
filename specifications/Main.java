import java.util.Scanner;

public class Main
{
    public static Scanner reader = new Scanner(System.in);
      
    public static void main(String[] args)
    {
        System.out.println("JAVA BATTLESHIP"); 
        
        System.out.println("\nPlayer 1 SETUP:");
        Player userPlayer1 = new Player();
        setup(userPlayer1);
        
        System.out.println("Player 2 SETUP:");
        Player userPlayer2 = new Player();
        setup(userPlayer2);
        
        String result = "";
        while(true)
        {
            System.out.println(result);
            System.out.println("\nPLAYER 1 MAKE GUESS:");
            result = askForGuess(userPlayer1, userPlayer2);
            
            if (userPlayer1.playerOcean.hasLost())
            {
                System.out.println("PLAYER 1 HIT!...PLAYER 2 LOSES");
                break;
            }
            else if (userPlayer2.playerOcean.hasLost())
            {
                System.out.println("PLAYER 2 HIT!...PLAYER 1 LOSES");
                break;
            }
            
            System.out.println(result);
            System.out.println("\nPLAYER 2 MAKE GUESS:");
            result = askForGuess(userPlayer2, userPlayer1);

            if (userPlayer1.playerOcean.hasLost())
            {
                System.out.println("PLAYER 1 HIT!...PLAYER 2 LOSES");
                break;
            }
            else if (userPlayer2.playerOcean.hasLost())
            {
                System.out.println("PLAYER 2 HIT!...PLAYER 1 LOSES");
                break;
            }
                
        }

    }
    

    private static String askForGuess(Player p, Player opp)
    {
        System.out.println("Viewing My Guesses:");
        p.oppOcean.printStatus();
        
        int row = -1;
        int col = -1;
        
        String oldRow = "Z";
        int oldCol = -1;
        
        while(true)
        {
            System.out.print("Type in row (A-J): ");
            String userInputRow = reader.next();
            userInputRow = userInputRow.toUpperCase();
            oldRow = userInputRow;
            row = convertLetterToInt(userInputRow);
                    
            System.out.print("Type in column (1-10): ");
            col = reader.nextInt();
            oldCol = col;
            col = convertUserColToProCol(col);
                    
            //System.out.println("DEBUG: " + row + col);
                    
            if (col >= 0 && col <= 9 && row != -1)
                break;
                    
            System.out.println("Invalid square!");
        }
        
        if (opp.playerOcean.hasShip(row, col))
        {
            p.oppOcean.markHit(row, col);
            opp.playerOcean.markHit(row, col);
            return "** USER HIT AT " + oldRow + oldCol + " **";
        }
        else
        {
            p.oppOcean.markMiss(row, col);
            opp.playerOcean.markMiss(row, col);
            return "** USER MISS AT " + oldRow + oldCol + " **";
        }
    }
    
    private static void setup(Player p)
    {
        p.playerOcean.printShips();
        System.out.println();
        int counter = 1;
        int normCounter = 0;
        while (p.numOfShipsLeft() > 0)
        {
            for (Ship s: p.ships)
            {
                System.out.println("\nShip #" + counter + ": Length-" + s.getLength());
                int row = -1;
                int col = -1;
                int dir = -1;
                while(true)
                {
                    System.out.print("Type in row (A-J): ");
                    String userInputRow = reader.next();
                    userInputRow = userInputRow.toUpperCase();
                    row = convertLetterToInt(userInputRow);
                    
                    System.out.print("Type in column (1-10): ");
                    col = reader.nextInt();
                    col = convertUserColToProCol(col);
                    
                    System.out.print("Type in direction (0-H, 1-V): ");
                    dir = reader.nextInt();
                    
                    //System.out.println("DEBUG: " + row + col + dir);
                    
                    if (col >= 0 && col <= 9 && row != -1 && dir != -1) // Check valid input
                    {
                        if (!hasErrors(row, col, dir, p, normCounter)) // Check if errors will produce (out of bounds)
                        {
                            break;
                        }
                    }
    
                    System.out.println("Invalid square!");
                }
            
                //System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
                p.ships[normCounter].setSquare(row, col);
                p.ships[normCounter].setDirection(dir);
                p.playerOcean.addShip(p.ships[normCounter]);
                p.playerOcean.printShips();
                System.out.println();
                System.out.println("You have " + p.numOfShipsLeft() + " remaining ships to place.");
                
                normCounter++;
                counter++;
            }
        }
    }

    
    
    private static boolean hasErrors(int row, int col, int dir, Player p, int count)
    {
        //System.out.println("DEBUG: count arg is " + count);
        
        int length = p.ships[count].getLength();
        
        // Check if off Ocean - Horizontal
        if (dir == 0)
        {
            int checker = length + col;
            //System.out.println("DEBUG: checker is " + checker);
            if (checker > 10)
            {
                System.out.println("SHIP DOES NOT FIT");
                return true;
            }
        }
        
        // Check if off Ocean - Vertical
        if (dir == 1) // VERTICAL
        {
            int checker = length + row;
            //System.out.println("DEBUG: checker is " + checker);
            if (checker > 10)
            {
                System.out.println("SHIP DOES NOT FIT");
                return true;
            }
        }
            
        // Check if overlapping with another ship
        if (dir == 0) // Hortizontal
        {
            // For each square a ship occupies, check if ship is already there
            for (int i = col; i < col+length; i++)
            {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                if(p.playerOcean.hasShip(row, i))
                {
                    System.out.println("THERE IS ALREADY A SHIP AT THAT LOCATION");
                    return true;
                }
            }
        }
        else if (dir == 1) // Vertical
        {
            // For each square a ship occupies, check if ship is already there
            for (int i = row; i < row+length; i++)
            {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                if(p.playerOcean.hasShip(i, col))
                {
                    System.out.println("THERE IS ALREADY A SHIP AT THAT LOCATION");
                    return true;
                }
            }
        }
        
        return false;
    }
    
    

    /*HELPER METHODS*/
    private static int convertLetterToInt(String val)
    {
        int toReturn = -1;
        switch (val)
        {
            case "A":   toReturn = 0;
                        break;
            case "B":   toReturn = 1;
                        break;
            case "C":   toReturn = 2;
                        break;
            case "D":   toReturn = 3;
                        break;
            case "E":   toReturn = 4;
                        break;
            case "F":   toReturn = 5;
                        break;
            case "G":   toReturn = 6;
                        break;
            case "H":   toReturn = 7;
                        break;
            case "I":   toReturn = 8;
                        break;
            case "J":   toReturn = 9;
                        break;
            default:    toReturn = -1;
                        break;
        }
        
        return toReturn;
    }
    
    private static String convertIntToLetter(int val)
    {
        String toReturn = "Z";
        switch (val)
        {
            case 0:   toReturn = "A";
                        break;
            case 1:   toReturn = "B";
                        break;
            case 2:   toReturn = "C";
                        break;
            case 3:   toReturn = "D";
                        break;
            case 4:   toReturn = "E";
                        break;
            case 5:   toReturn = "F";
                        break;
            case 6:   toReturn = "G";
                        break;
            case 7:   toReturn = "H";
                        break;
            case 8:   toReturn = "I";
                        break;
            case 9:   toReturn = "J";
                        break;
            default:    toReturn = "Z";
                        break;
        }
        
        return toReturn;
    }
    
    private static int convertUserColToProCol(int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 1:   toReturn = 0;
                        break;
            case 2:   toReturn = 1;
                        break;
            case 3:   toReturn = 2;
                        break;
            case 4:   toReturn = 3;
                        break;
            case 5:   toReturn = 4;
                        break;
            case 6:   toReturn = 5;
                        break;
            case 7:   toReturn = 6;
                        break;
            case 8:   toReturn = 7;
                        break;
            case 9:   toReturn = 8;
                        break;
            case 10:   toReturn = 9;
                        break;
            default:    toReturn = -1;
                        break;
        }
        
        return toReturn;
    }
}
    