import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Arrays;
/**
Class Calculations == Creates date array, data array and performs calculations on data array
@author Sasha Malesevic
@since 2018-11-28
*/
public class Calculations 
{
    /**
    Creates an array to hold all of the dates, the first column of the multi-dimensional array
    Uses a try and catch block
    ie. Date [] dateArray = new Date[r.findRowNumber] 
    @param numRows used to initialize size of dateArray
    @param array to copy information about the Date into dateArray
    */
    public Date [] createDateArray(int numRows, String array [][])
    {
        Date [] dateArray = new Date[numRows];
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
    @param numRows used to initialize size of dataArray
    @param numColumns used to initialize size of dataArray
    @param array to copy information into dataArray
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
    @param numRows used to stop for loop
    @param dateArray required to print
    @param dataArray required to print
    @param numColumns used to stop for loop 
    */
    public void printArray(int numRows, Date dateArray[], float dataArray[][], int numColumns)
    {
        String [] strArray = {"Open", "High", "Low", "Close", "Adj close", "Volume"};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Date Array: ");
        for(int i=0; i<numRows; i++)
        {
            System.out.println(df.format(dateArray[i]));
        }
        System.out.println("Data Array: ");

        for(int i=0; i<numColumns-1; i++)
        {
            System.out.printf("%-12s", strArray[i]);
        }
        System.out.println();
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numColumns-1; j++)
            {
                System.out.printf("%-12.3f", dataArray[i][j]);
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
    @param numRows used to stop for loop
    @param numColumns used to stop for loop
    @param dateArray used for Top 10 dates arrays
    @param dataArray used for calculations and important values
    @param diff array holding the column difference for all rows

    NOTE: This is where the Lambda expression is used 
    */
    public void stats(int numRows, int numColumns, Date dateArray [], float dataArray[][], float diff[])
    {
        int open = 0; // column index
        int close = 3; // column index
        float highOpen = dataArray[0][open];
        float lowOpen = dataArray[0][open];
        float highClose;
        float lowClose;
        Date [] high = new Date[10];
        float temp;
        Date [] low = new Date[10];
        float sorted [] []= new float[numRows][2]; 

        /**
        First I sorted the closing values, I made it a two dimensional array to keep track of the original location
        of each closing date, this is later used as an index when determining 10 highest/lowest closing dates.
        */
        for(int i=0; i<numRows; i++)
        {
            sorted[i][0] = dataArray[i][close];
            sorted[i][1] = (float)i;
            diff[i] = dataArray[i][open] - dataArray[i][close];
            if(highOpen < dataArray[i][open])
            {
                highOpen = dataArray[i][open];
            }
            if(lowOpen > dataArray[i][open])
            {
                lowOpen = dataArray[i][open];
            }
        }

        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numRows-1; j++)
            {
                // highest to lowest
                if(sorted[j][0] < sorted[j+1][0])
                {
                    temp = sorted[j][0];
                    sorted[j][0] =  sorted[j+1][0];
                    sorted[j+1][0] = temp;

                    temp = sorted[j][1];
                    sorted[j][1] = sorted[j+1][1];
                    sorted[j+1][1] = temp;
                }
            }
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Top 10 dates with highest closing value (Descending): ");
        for(int i=0; i<10; i++)
        {
            high[i] = dateArray[(int)sorted[i][1]];
            System.out.println(df.format(high[i]));
        }
        System.out.println("Top 10 dates with lowest closing value (Ascending): ");
        for(int i=0; i<10; i++)
        {
            low[i] = dateArray[(int)sorted[numRows-i-1][1]];
            System.out.println(df.format(low[i]));
        }

        System.out.println("The column difference for every date is: ");
        for(int i=0; i<numRows; i++)
        {
            System.out.println(diff[i]);
        }
        highClose = sorted[0][0];
        lowClose = sorted[numRows-1][0];
         
        ImportantVals vals = (float highO, float highCl, float lowO, float lowCl) -> {
            System.out.println("My first Lambda expression: ");
            System.out.println("The highest opening value was: " + highO);
            System.out.println("The lowest opening value was: " + lowO);
            System.out.println("The highest closing value was: " + highCl);                
            System.out.println("The lowest closing value was: " + lowCl);
        };
        vals.printVals(highOpen, highClose, lowOpen, lowClose);
    }
}