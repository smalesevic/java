import java.util.StringTokenizer;
import java.io.*;
import java.nio.file.*;
import java.util.*;
/**
Class DataAnalyzer == Calls all the methods from ReadFiles 
- Checks to see if the first line is empty, and if it is empty skips the first line
    - This was added because I wasn't certain whether or not our code would be tested with other files
    where the first line including the headers is removed; uncertain whether the empty line would remain as a placeholder 
    or not 
- Uses two seperate try/catch blocks since reader needs to reposition to beginning of the file

@author Sasha Malesevic
@since 2018-11-19
*/
public class DataAnalyzer
{
    public static void main(String[] args)
    {
        Path file = Paths.get("/temp/newXOM2.csv");
        //Path file = Paths.get("C:\\temp\\newXOM.csv");
        String line = "";
        int numColumns = 0; 
        int numRows = 0;
        boolean isEmpty = false;
        Calculations calc = new Calculations();

        if (ReadFiles.fileExists(file))
            System.out.println("File exists.");
        else
            System.out.println("File doesn't exist.");
        
        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            line = reader.readLine();

            if((line != null && (line.trim().equals("")) || line.trim().equals("\n") || line.trim().equals(",")) || line.startsWith(","))
            {
                System.out.println("The first line is empty, we will skip this one.");
                line = reader.readLine();
                isEmpty = true;
            }

            StringTokenizer st = new StringTokenizer(line, ","); 
            numColumns = st.countTokens();
            System.out.println("Number of columns: " + numColumns);

            numRows = ReadFiles.findNumRows(reader, line);
            System.out.println("Number of rows: " + numRows);

            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }

        String array[][] = new String[numRows][numColumns];

        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            if(isEmpty)
                line = reader.readLine();
            
            ReadFiles.CSVToArray(reader, array, numRows, numColumns);

            //calc.createDateArray(numRows, array);
            calc.printArray(numRows, calc.createDateArray(numRows, array));
            ReadFiles.printArray(ReadFiles.getArray(array), numRows, numColumns);
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }
    }
}