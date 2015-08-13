package test.java.multichoice.data;

import multichoice.data.MatrixDataProvider;
import multichoice.data.TraversalPoint;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noopur Berge
 */
public class MatrixDataProviderTest 
{
    
    public MatrixDataProviderTest() 
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
     * Test of getStartNode method, of class MatrixDataProvider.
     */
    @Test
    public void testGetStartNode() 
    {
        MatrixDataProvider instance = new MatrixDataProvider();
        TraversalPoint expResult = null;
        TraversalPoint result = instance.getStartNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartNode method, of class MatrixDataProvider.
     */
    @Test
    public void testSetStartNode() 
    {
        TraversalPoint expResult = new TraversalPoint(0, 0);
        MatrixDataProvider instance = new MatrixDataProvider();
        instance.setStartNode(expResult);
        TraversalPoint result = instance.getStartNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTargetNode method, of class MatrixDataProvider.
     */
    @Test
    public void testGetTargetNode() 
    {
        MatrixDataProvider instance = new MatrixDataProvider();
        TraversalPoint expResult = null;
        TraversalPoint result = instance.getTargetNode();
        assertEquals(expResult, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of setTargetNode method, of class MatrixDataProvider.
     */
    @Test
    public void testSetTargetNode() 
    {
        TraversalPoint expResult = new TraversalPoint(50, 50);
        MatrixDataProvider instance = new MatrixDataProvider();
        instance.setTargetNode(expResult);
        TraversalPoint result = instance.getTargetNode();
        assertEquals(expResult, result);
    }
}
