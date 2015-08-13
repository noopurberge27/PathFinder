package multichoice.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import multichoice.data.TraversalPoint;
import org.apache.log4j.Logger;


/**
 * @Description - writing output to file
 * 
 * @author Noopur Berge
 *
 */
public class FileOutputWriter implements IOutputWriter 
{
    private static final Logger LOG = Logger.getLogger("io.FileOutputWriter");
    private String outputFilePath;
    private List<TraversalPoint> costEffectivePath;
    private TraversalPoint[][] matrixData;
    private static final String DEFAULT_OUTPUT = "output.txt";

    public FileOutputWriter(String outputFilePath, TraversalPoint[][] matrixData, List<TraversalPoint> costEffectivePath)
    {
        this.outputFilePath = outputFilePath;
        this.matrixData = matrixData;
        this.costEffectivePath = costEffectivePath;
    }
	
    @Override
    public void commitOutputToFile()
    {
        for (TraversalPoint node: costEffectivePath)
        {
            node.setLabel("$");
            matrixData[node.getXPosition()][node.getYPosition()] =  node;
        }
        StringBuffer buffer = new StringBuffer();
        int rowNumbers = matrixData[0].length;
        int colNumber = matrixData.length;
        for (int r = 0; r < rowNumbers; r++)
        {
            for (int c = 0; c < colNumber; c++)
            {
                buffer.append(matrixData[c][r].getLabel());
            }
            buffer.append(System.getProperty("line.separator"));
        }
        try
        {
            if (outputFilePath == null)
            {
                outputFilePath = DEFAULT_OUTPUT;
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outputFilePath)));
            bufferedWriter.write(buffer.toString(),0, buffer.toString().length());
            bufferedWriter.close();
        }
        catch (IOException exception)
        {
            LOG.error("Error while writing the map to the file - "+exception);
        }
    }
    
    public String getAbsoluteOutputFilePath()
    {
        return new File(outputFilePath).getAbsolutePath();
    }
}
