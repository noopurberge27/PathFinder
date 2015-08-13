package multichoice.util;

/**
 * @Description - defining enums for constants
 * 
 * @author Noopur Berge
 *
 */
public enum TraversalPointEnums 
{
    WATER('~', -1),
    START_POINT('@', 1),
    TARGET_POINT('X',1),
    FLAT_LAND('.',1),
    FOREST('*',2),
    MOUNTAIN('^', 3);

    private char symbol;
    private int cost;
    private String label;

    private TraversalPointEnums(char symbol, int cost)
    {
        this.symbol = symbol;
        this.cost = cost;
        this.label = String.valueOf(symbol);
    }

    public char getSymbol() 
    {
        return symbol;
    }

    public void setSymbol(char symbol) 
    {
        this.symbol = symbol;
    }

    public int getCost() 
    {
        return cost;
    }

    public void setCost(int cost) 
    {
        this.cost = cost;
    }

    public String getLabel() 
    {
        return label;
    }

    public void setLabel(String label) 
    {
        this.label = label;
    }
}
