package test.java.multichoice.algorithm;

import java.io.FileNotFoundException;
import multichoice.algorithm.AStarSearchAlgorithm;
import multichoice.util.InvalidInputException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Noopur Berge
 */
public class AStarSearchAlgorithmTest 
{
    public AStarSearchAlgorithmTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
    }
    
    @After
    public void tearDown() 
    {
    }

    /**
     * Test of searchPath method, of class AStarSearchAlgorithm.
     */
    @org.junit.Test
    public void testSearchPathWithInvalidFileInput() 
    {
        boolean testFailed = false;
        try
        {
            String mapPath = "";
            String outputPath = "";
            AStarSearchAlgorithm instance = new AStarSearchAlgorithm();
            instance.searchPath(mapPath, outputPath);
            testFailed = true;
        }
        catch (InvalidInputException iie)
        {
            testFailed = false;
        }
        catch (FileNotFoundException fnfe)
        {
            testFailed = false;
        }
        assertEquals(testFailed, false);
    }
}
