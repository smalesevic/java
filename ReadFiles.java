import java.util.StringTokenizer;
import static java.nio.file.AccessMode.*; // Required for checkAccess() method
import java.nio.file.*;
import java.io.IOException;
import java.io.File; // For files.exists()
import java.io.*;
/**
Class ReadFiles == Contains several static methods to be tested in DataAnalyzer 
@author Sasha Malesevic
@since 2018-11-19
*/
public class ReadFiles
{
    /**
    fileExists will check whether or not the file exists
    @param path a Path object is passed to create a new File object so that exists() and isDirectory() can be used
    */

    public boolean fileExists(Path path)
    {
        File file = new File(path.toString());
        if(file.exists() && !file.isDirectory())
            return true;
        else
            return false;
    }

    /**
    Finds number of rows in a CSV file
    Uses a try and catch block 
    @param reader BufferedReader object to allow use of readLine() 
    @param line String object is passed because line = reader.readLine() is already stated in DataAnalyzer
                prior to calling findNumRows; a line has already been read and is ready to use
    */

    public int findNumRows(BufferedReader reader, String line)
    {
        try
        {
            int numRows = 0;

            while(line != null)
            {
                line = reader.readLine();
                numRows++;
            }
            return numRows; 
        }
        catch (Exception e)
        {
            System.out.println("Message: " + e);
            return -1;
        }

    }

    /** 
    Takes a CSV file and converts data to multi dimensional array 
    Uses try and catch block
    Make use of StringTokenizer to divide each line into components
    @param reader BufferedReader object to allow use of readLine()
    @param array[][] Empty multidimensional array is passed so the array can be used outside of the method
    @param numRows Used to determine when to stop for loop 
    @param numColumns Used to determine when to stop for loop  
    */

    
    public void CSVToArray(BufferedReader reader, String array[][], int numRows, int numColumns)
    {   
        try
        {
            String line = "";
            line = reader.readLine();
            StringTokenizer st = new StringTokenizer(line, ","); 
        
            for (int i=0; i<numRows; i++)
            {
                for (int j=0; j<numColumns; j++)
                {
                    array[i][j] = st.nextToken();
                }
                if (i<numRows-1)
                {
                    line = reader.readLine();
                    st = new StringTokenizer(line, ",");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }
    }

    /**
    printArray will print the entire array
    @param array[][] Multidimensional array is passed to print the contents of the arraY
    @param numRows Used to determine when to stop for loop 
    @param numColumns Used to determine when to stop for loop 
    */
 
    public void printArray(String array[][], int numRows, int numColumns)
    {
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numColumns; j++)
            {
                //System.out.print(array[i][j] + '\t' + '\t');
                System.out.print(String.format("%-12s", array[i][j]));
            }
            System.out.println();
        }
    }

    /**
    printArray will print the entire array
    @param array[][] Multidimensional array is passed to print the contents of the array
    */

    public String[][] getArray(String array[][])
    {
        return array;
    }

}