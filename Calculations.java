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
    public void stats(int numRows, int numColumns, Date dateArray [], float dataArray[][])
    {
        int open = 0;
        int close = 3;
        float highOpen = dataArray[0][open];
        float lowOpen = dataArray[0][open];
        float highClose;
        float lowClose;
        Date [] high = new Date[10];
        float temp;
        Date [] low = new Date[10];
        float sorted [] []= new float[numRows][2]; 
        float [] diff = new float[numRows];

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

        for(int i=0; i<10; i++)
        {
            high[i] = dateArray[(int)sorted[i][1]];
            low[i] = dateArray[(int)sorted[numRows-i-1][1]];
            System.out.println(df.format(high[i]) + "   " + df.format(low[i]));
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
        for(int i=0; i<numRows; i++)
        {
            System.out.println(sorted[i][0] + "\t" + sorted[i][1]);
        }
    }
}