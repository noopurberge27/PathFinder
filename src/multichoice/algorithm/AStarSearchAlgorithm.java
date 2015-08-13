package multichoice.algorithm;

import multichoice.io.IInputReader;
import multichoice.io.IOutputWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import multichoice.util.TraversalPointComparator;
import multichoice.data.MatrixDataProvider;
import multichoice.io.FileInputReader;
import multichoice.io.FileOutputWriter;
import multichoice.util.TraversalPointEnums;
import multichoice.data.TraversalPoint;
import multichoice.util.InvalidInputException;
import org.apache.log4j.Logger;

/**
 * @Description - Search algorithm 
 * 
 * @author Noopur Berge
 */
public class AStarSearchAlgorithm implements ISearchAlgorithm 
{
    private static final Logger LOG = Logger.getLogger("algo.AStarSearchAlgorithm");
    // reading from input file
    private IInputReader fileReader;
    // writing to output file
    private IOutputWriter fileWriter;
    // entire 2D representation of input	
    private TraversalPoint[][] map;

    // to store starting point
    private TraversalPoint startNode;
    // to store target
    private TraversalPoint targetNode;

    // to store maximum x bounds of input
    private int xRange = -1;
    // to store maximum y bounds of input
    private int yRange = -1;
    
    private static final int PRIORITY_QUEUE_INITIAL_CAPACITY = 1000;
    
    public void searchPath(String inputFilePath, String outputFilePath) throws FileNotFoundException, InvalidInputException
    {
        File file = new File(inputFilePath);

        fileReader = new FileInputReader(new FileReader(file));
        MatrixDataProvider inputData = fileReader.fetchMatrixData();

        map = inputData.generateMapMatrix();
        startNode = inputData.getStartNode();
        targetNode = inputData.getTargetNode();
        
        xRange = inputData.getXRange();
        yRange = inputData.getYRange();
        
        List<TraversalPoint> paths = evaluateCostEffectiveRoute();

        if (!paths.isEmpty())
        {
            fileWriter = new FileOutputWriter(outputFilePath , map, paths);
            fileWriter.commitOutputToFile();
            if (LOG.isInfoEnabled())
            {
                LOG.info("########################################################################################");
                LOG.info("Finished processing - output has been written to "+fileWriter.getAbsoluteOutputFilePath());
                LOG.info("########################################################################################");
            }
        }
    }
    
    private List<TraversalPoint> evaluateCostEffectiveRoute() 
    {
        List<TraversalPoint> paths = new ArrayList<TraversalPoint>();
        Queue<TraversalPoint> openNodes = new PriorityQueue<TraversalPoint>(PRIORITY_QUEUE_INITIAL_CAPACITY, new TraversalPointComparator());
        Set<TraversalPoint> closedNodes = new HashSet<TraversalPoint>();
        startNode.setGScore(0);
        startNode.setFScore(startNode.getGScore() + getHeuristicEstimate(startNode));

        openNodes.add(startNode);
        boolean found = false;
        while (!openNodes.isEmpty() && !found) 
        {
            // This will always return the TraversalPoint with the lowest f_score value
            TraversalPoint currentNode = openNodes.poll();          
            closedNodes.add(currentNode);
            List<TraversalPoint> adjacentList = new ArrayList<TraversalPoint>();
            if (currentNode.getLabel().equals(targetNode.getLabel()))
            {
                found = true;
            } 
            else 
            {
                adjacentList = getAdjacentNodes(currentNode);
            }

            for (TraversalPoint adjacentNode : adjacentList) 
            {
                double cost = adjacentNode.getCost();
                // Evaluate tentative g_score
                double tentativeGScore = currentNode.getGScore() + cost ;
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("adjacentNode = "+adjacentNode.getXPosition()+", "+adjacentNode.getYPosition()+" : cost = "+cost+", tempGScore = "+tentativeGScore);
                }
                if (adjacentNode.getLabel().equals(TraversalPointEnums.WATER.getLabel()) || (closedNodes.contains(adjacentNode)))
                {
                    continue;
                }
                else if (!openNodes.contains(adjacentNode) || tentativeGScore < adjacentNode.getGScore())
                {
                    adjacentNode.setParent(currentNode);
                    adjacentNode.setGScore(tentativeGScore);
                    adjacentNode.setFScore(tentativeGScore + getHeuristicEstimate(adjacentNode));
                    if (openNodes.contains(adjacentNode))
                    {
                        openNodes.remove(adjacentNode);
                    }
                    openNodes.add(adjacentNode);
                }
            }
        }
        paths = getPathNodes(targetNode);
        return paths;
    }

    private double getHeuristicEstimate(TraversalPoint node)
    {
        return Math.abs(node.getXPosition() - startNode.getXPosition()) + Math.abs(node.getYPosition() - targetNode.getYPosition());
    }
    
    /**
     * @param targetNode
     * @return
     */
    private List<TraversalPoint> getPathNodes(TraversalPoint targetNode) 
    {
        List<TraversalPoint> paths = new ArrayList<TraversalPoint>();
        for (TraversalPoint node = targetNode; node!=null; node = node.getParent())
        {
            paths.add(node);
        }
        Collections.reverse(paths);
        return paths;
    }

    /**
     * @param parentNode
     * @return
     */
    private List<TraversalPoint> getAdjacentNodes(TraversalPoint parentNode) 
    {
        List<TraversalPoint> adjacentList = new ArrayList<TraversalPoint>();
        int xIndex = parentNode.getXPosition();
        int yIndex = parentNode.getYPosition();
        LOG.debug("xIndex = "+xIndex+", yIndex = "+yIndex);
        int leftIndex = parentNode.getXPosition() - 1;
        int rightIndex = parentNode.getXPosition() + 1;
        int topIndex = parentNode.getYPosition() - 1;
        int bottomIndex = parentNode.getYPosition() + 1;

        if (topIndex >= 0)
        {
            adjacentList.add(map[xIndex][topIndex]);
        }
        
        if (bottomIndex < yRange)
        {
            adjacentList.add(map[xIndex][bottomIndex]);
        }
        
        if (leftIndex >= 0)
        {
            adjacentList.add(map[leftIndex][yIndex]);
            if (topIndex >= 0)
            {
                adjacentList.add(map[leftIndex][topIndex]);
            }
            if (bottomIndex < yRange)
            {
                adjacentList.add(map[leftIndex][bottomIndex]);
            }
        }
        
        if (rightIndex < xRange)
        {
            adjacentList.add(map[rightIndex][yIndex]);
            if (topIndex >= 0)
            {
                adjacentList.add(map[rightIndex][topIndex]);
            }
            if (bottomIndex < yRange)
            {
                adjacentList.add(map[rightIndex][bottomIndex]);
            }
        }
        return adjacentList;
    }
}
