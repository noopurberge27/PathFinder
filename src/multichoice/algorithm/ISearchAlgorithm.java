package multichoice.algorithm;

import java.io.FileNotFoundException;
import multichoice.util.InvalidInputException;

/**
 * 
 * @author Noopur Berge
 */
public interface ISearchAlgorithm
{
    public void searchPath(String inputFile, String outputFile) throws FileNotFoundException, InvalidInputException;
}
