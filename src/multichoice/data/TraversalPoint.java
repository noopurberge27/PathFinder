package multichoice.data;

import java.util.List;

/**
 * @Description - object which will hold the node location and its score
 * 
 * @author Noopur Berge
 */
public class TraversalPoint 
{
    private int xpos;
    private int ypos;
    private int cost;
    private List<TraversalPoint> path;
    private TraversalPoint parent;
    // lower fScore means higher the probability of point being closer to target
    private double fScore;
    // cumulative cost of path traveled till this point
    private double gScore;
    private String label;
    
    public TraversalPoint(int xpos, int ypos)
    {
        this.xpos = xpos;
        this.ypos = ypos;
    }
    
    public TraversalPoint(String label, int xpos, int ypos)
    {
        this.xpos = xpos;
        this.ypos = ypos;
        this.label = label;
    }
   
    public double getFScore() 
    {
        return fScore;
    }

    public void setFScore(double fScore) 
    {
        this.fScore = fScore;
    }

    public double getGScore() 
    {
        return gScore;
    }

    public void setGScore(double gScore) 
    {
        this.gScore = gScore;
    }

    public TraversalPoint getParent() 
    {
        return parent;
    }

    public String getLabel() 
    {
        return label;
    }

    public void setLabel(String label) 
    {
        this.label = label;
    }

    public void setParent(TraversalPoint parent) 
    {
        this.parent = parent;
    }
    
    public int getXPosition() 
    {
        return xpos;
    }
    
    public void setXPosition(int xpos) 
    {
        this.xpos = xpos;
    }
    
    public int getYPosition() 
    {
        return ypos;
    }
    
    public void setYPosition(int ypos) 
    {
        this.ypos = ypos;
    }
    
    public List<TraversalPoint> getPath() 
    {
        return path;
    }
    
    public void setPath(List<TraversalPoint> path) 
    {
        this.path = path;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public void setCost(int cost) 
    {
        this.cost = cost;
    }

    @Override
    public String toString() 
    {
        return this.getClass() + "[xpos=" + xpos + ", ypos=" + ypos + "]";
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (null == obj)
        {
            return false;
        }
        if (obj instanceof TraversalPoint)
        {
            TraversalPoint tpo = (TraversalPoint) obj;
            if (this.xpos == tpo.xpos && this.ypos == tpo.ypos)
            {
                return true;
            }
        }
        return false;
    }
}