package multichoice.data;

import java.util.ArrayList;
import java.util.List;
import multichoice.util.InvalidInputException;
import multichoice.util.TraversalPointEnums;

/**
 * @description - Storing input data
 * 
 * @author Noopur Berge
 */
public class MatrixDataProvider
{
    private TraversalPoint startNode;
    private TraversalPoint targetNode;	
    private List<String> lines;
    private int xcapacity;
    private int ycapacity;
    
    public MatrixDataProvider()
    {
        lines = new ArrayList<String>();
        startNode = null;
        targetNode = null;
    }
    
    public TraversalPoint getStartNode() 
    {
        return startNode;
    }

    public void setStartNode(TraversalPoint startNode) 
    {
        this.startNode = startNode;
    }

    public TraversalPoint getTargetNode() 
    {
        return targetNode;
    }

    public void setTargetNode(TraversalPoint targetNode) 
    {
        this.targetNode = targetNode;
    }

    public void doFinalValidation() throws InvalidInputException
    {
        if (startNode == null)
        {
            throw new InvalidInputException("Starting point not found - cannot proceed further");
        }
        if (targetNode == null)
        {
            throw new InvalidInputException("Target point not found - cannot proceed further");
        }    
    }
    
    public void createMapNodes(String line) throws InvalidInputException
    {
        if (line.contains(TraversalPointEnums.START_POINT.getLabel()))
        {
            if (startNode != null)
            {
                throw new InvalidInputException("Multiple starting points were detected - this is an invalid scenario");
            } 
            else 
            {
                startNode = new TraversalPoint(TraversalPointEnums.START_POINT.getLabel(), line.indexOf(TraversalPointEnums.START_POINT.getLabel()), lines.size());
            }
        }
        if (line.contains(TraversalPointEnums.TARGET_POINT.getLabel()))
        {
            if (targetNode != null)
            {
                throw new InvalidInputException("Multiple target nodes were detected - this is an invalid scenario");
            }
            else
            {
                targetNode = new TraversalPoint(TraversalPointEnums.TARGET_POINT.getLabel(), line.indexOf(TraversalPointEnums.TARGET_POINT.getLabel()), lines.size());
            }
        }
        lines.add(line);
    }

    public int getXRange()
    {
        return xcapacity;
    }
    
    public int getYRange()
    {
        return ycapacity;
    }
    
    public TraversalPoint[][] generateMapMatrix() 
    {
        int k = 0;
        xcapacity = lines.get(0).length();
        ycapacity = lines.size();
        TraversalPoint[][] dataMatrix = new TraversalPoint[xcapacity][ycapacity];
        for (String line: lines)
        {
            char[] mapChars = line.toCharArray();
            for (int c = 0 ; c < mapChars.length ; c++)
            {
                TraversalPoint node = new TraversalPoint(String.valueOf(mapChars[c]), c, k);
                for (TraversalPointEnums nodeType: TraversalPointEnums.values())
                {
                    if (nodeType.getLabel().equalsIgnoreCase(node.getLabel()))
                    {
                        node.setCost(nodeType.getCost());
                    }
                }
                dataMatrix[c][k] = node;
            }
            k++;
        }
        dataMatrix[startNode.getXPosition()][startNode.getYPosition()] = startNode;
        dataMatrix[targetNode.getXPosition()][targetNode.getYPosition()] = targetNode;
        return dataMatrix;
    }
}
