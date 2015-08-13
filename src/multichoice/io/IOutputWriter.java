package multichoice.io;

/**
 * 
 * @author Noopur Berge
 */
public interface IOutputWriter
{
    public void commitOutputToFile();
    
    public String getAbsoluteOutputFilePath();
}