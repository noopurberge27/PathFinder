package multichoice.main;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import multichoice.algorithm.AStarSearchAlgorithm;
import multichoice.util.InvalidInputException;
import multichoice.util.LoggerUtil;
import org.apache.log4j.Logger;

/**
 * @Description PathFinder.java - class having main function
 * 
 * @author Noopur Berge
 *
 */
public class PathFinder 
{
    private static final Logger LOG = Logger.getLogger("main.PathFinder");

    public static void main(String args[])
    {
        //for setting up logging
        LoggerUtil.setupLogging();
        try
        {
            List<String> arguments = Arrays.asList(args);
            String inputFilePath = null;
            String outputFilePath = null;
            for (int i = 0; i < arguments.size(); i++)
            {
                String argument = arguments.get(i);
                if (argument.equalsIgnoreCase("-h") || argument.equalsIgnoreCase("--help"))
                {
                    printCommandUsage();
                }
                else if (argument.equalsIgnoreCase("-i") || argument.equalsIgnoreCase("--input-file"))
                {
                    inputFilePath = arguments.get(i + 1);
                }
                else if (argument.equalsIgnoreCase("-o") || argument.equalsIgnoreCase("--output-file"))
                {
                    outputFilePath = arguments.get(i + 1);
                }
            }

            if (inputFilePath != null)
            {
                AStarSearchAlgorithm service =  new AStarSearchAlgorithm();
                service.searchPath(inputFilePath, outputFilePath);
            }
            else
            {
                printCommandUsage();
            }
        }
        catch (FileNotFoundException fnfe)
        {
            LOG.error("Cannot find the input file - "+fnfe);
        }
        catch (InvalidInputException iie)
        {
            LOG.error("There was an InvalidInputException reported - "+iie);
        }
    }
	
    /**
     * print command usage
     */
    private static void printCommandUsage() 
    {
        LOG.debug("Usage: PathFinder [options]");
        LOG.debug("   options:");
        LOG.debug("   --------");
        LOG.debug("      --input-file | -i input file path {mandatory}");
        LOG.debug("      --output-file | -o output file path {optional}, ");
        LOG.debug("      --help | -h prints command usage");
    }
}