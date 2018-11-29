import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Arrays;

public class Calculations 
{
    /**
    Creates an array to hold all of the dates, the first column of the multi-dimensional array
    Uses a try and catch block
    ie. Date [] dateArray = new Date[r.findRowNumber] 
    */
    public Date [] createDateArray(int numRows, String array [][])
    {
        Date [] dateArray = new Date[numRows];
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            for(int i=0; i<numRows; i++)
            {
                String dateInString = array[i][0];
                Date date = formatter.parse(dateInString);
                dateArray[i] = date;
            }
        }
        catch (Exception e)
        {
            System.out.println("Message: " + e);
        }
        return dateArray;
    }

    /**
    Create a second array to hold data from the six other columns
    ie. float [] [] dataArray = new float[r.findRowNumber][7]
    */
    public float [][] createDataArray(int numRows, int numColumns, String array [][])
    {
        float [] [] dataArray = new float[numRows][numColumns-1];
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numColumns-1; j++)
            {
                dataArray[i][j] = Float.valueOf(array[i][j+1]);
            }
        }
        return dataArray;
    }

    /**
    Prints the above arrays 
    */
    public void printArray(int numRows, Date dateArray[], float dataArray[][], int numColumns)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Date Array: ");
        for(int i=0; i<numRows; i++)
        {
            System.out.println(df.format(dateArray[i]));
        }
        System.out.println("Data Array: ");
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numColumns-1; j++)
            {
                System.out.printf("%-10.3f", dataArray[i][j]);
                //System.out.print(dataArray[i][j] + "\t");

            }
            System.out.println();
        }
    }

    /**
    Highest opening/closing value : 2 
    Lowest opening/closing value : 2 
    Top 10 dates with highest closing value - highest to lowest : 1 
    Top 10 dates with lowest closing value - lowest to highest : 1 
    Add column difference for all rows: Opening - Closing 
    - This needs to be written to a file stored in c:\temp\directory
    - Make sure format is good, column headers required
    - Results of the calculations should be output to the console
    */
    public void stats()
    {

    }
}