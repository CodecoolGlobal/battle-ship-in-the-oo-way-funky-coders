public class Player
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int NUM_OF_SHIPS = 5;
    
    public Ship[] ships;
    public Ocean playerOcean;
    public Ocean oppOcean;
    
    public Player()
    {
        if (NUM_OF_SHIPS != 5) // Num of ships must be 5
        {
            throw new IllegalArgumentException("ERROR! Num of ships must be 5");
        }
        
        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            Ship tempShip = new Ship(SHIP_LENGTHS[i]);
            ships[i] = tempShip;
        }
        
        playerOcean = new Ocean();
        oppOcean = new Ocean();
    }
    
    public void addShips()
    {
        for (Ship s: ships)
        {
            playerOcean.addShip(s);
        }
    }
    
    public int numOfShipsLeft()
    {
        int counter = 5;
        for (Ship s: ships)
        {
            if (s.isSquareSet() && s.isDirectionSet())
                counter--;
        }
        
        return counter;
        
    }
    
    public void chooseShipSquare(Ship s, int row, int col, int direction)
    {
        s.setSquare(row, col);
        s.setDirection(direction);
        playerOcean.addShip(s);
    }
}