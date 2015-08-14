package multichoice.util;

import java.util.Comparator;
import multichoice.data.TraversalPoint;

/**
 * @Description class that represents a Node comparator by F score 
 * 
 * @author Noopur Berge
 *
 */
public class TraversalPointComparator implements Comparator<TraversalPoint> 
{
    @Override
    public int compare(TraversalPoint first, TraversalPoint second) 
    {
        if (first.getFScore() > second.getFScore())
        {
            return 1;
        } 
        else if (first.getFScore() <  second.getFScore())
        {
            return -1;
        } 
        else 
        {
            return 0;
        }
    }
}
