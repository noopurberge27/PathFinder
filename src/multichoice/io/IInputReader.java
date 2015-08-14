package multichoice.io;

import multichoice.data.MatrixDataProvider;
import multichoice.util.InvalidInputException;

/**
 * 
 * @author Noopur Berge
 */
public interface IInputReader 
{
    public MatrixDataProvider fetchMatrixData() throws InvalidInputException;
}