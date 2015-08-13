package multichoice.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import multichoice.data.MatrixDataProvider;
import multichoice.data.TraversalPoint;
import multichoice.util.InvalidInputException;
import org.apache.log4j.Logger;

/**
 * @Description - Reading the input from file and feeding the 2D matrix
 * 
 * @author Noopur Berge
 *
 */
public class FileInputReader implements IInputReader 
{
    private static final Logger LOG = Logger.getLogger("io.FileInputReader");
    private FileReader fileReader;
    private TraversalPoint startNode;
    private TraversalPoint targetNode;

    public FileInputReader(FileReader fileReader)
    {
        this.fileReader = fileReader;
    }

    public MatrixDataProvider fetchMatrixData() throws InvalidInputException
    {
        MatrixDataProvider dataProvider = new MatrixDataProvider();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        try
        {
            while (( line = bufferedReader.readLine()) != null)
            {
                dataProvider.createMapNodes(line);
            }
        } 
        catch (IOException exception)
        {
            LOG.error("An error occured while reading the file" + exception);
        }
        finally
        {
            try
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
            }
            catch (IOException exc)
            {
                LOG.error("An error occured while closing the bufferedReader" + exc);
            }
        }
        dataProvider.doFinalValidation();
        return dataProvider;
    }
}
