import java.util.StringTokenizer;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import static java.nio.file.StandardOpenOption.*;
/**
Class DataAnalyzer == Calls all the methods from ReadFiles and Calculations
- Checks to see if the first line is empty, and if it is empty skips the first line
    - This was added because I wasn't certain whether or not our code would be tested with other files
    where the first line including the headers is removed; uncertain whether the empty line would remain as a placeholder 
    or not 
- Uses two seperate try/catch blocks since reader needs to reposition to beginning of the file
- Writes column difference to a new file
@author Sasha Malesevic
@since 2018-11-19
@version 2.0
*/
public class DataAnalyzer
{
    public static void main(String[] args)
    {
        //Path file = Paths.get("/temp/newXOM2.csv");
        /**
        newXOM.csv -> full file without headings
        newXOM2.csv -> 20 lines of the file for testing
        XOM.csv -> original file
        */
        Path file = Paths.get("C:\\temp\\newXOM.csv"); 
        String line = "";
        int numColumns = 0; 
        int numRows = 0;
        boolean isEmpty = false;
        Calculations calc = new Calculations();
        ReadFiles read = new ReadFiles();

        if (read.fileExists(file))
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

            numRows = read.findNumRows(reader, line);
            System.out.println("Number of rows: " + numRows);

            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }

        String array[][] = new String[numRows][numColumns];
        float diff [] = new float[numRows];

        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            if(isEmpty)
                line = reader.readLine();
            
            read.CSVToArray(reader, array, numRows, numColumns);

            read.printArray(read.getArray(array), numRows, numColumns);
            calc.printArray(numRows, calc.createDateArray(numRows, array), calc.createDataArray(numRows, numColumns, array), numColumns);
            calc.stats(numRows, numColumns, calc.createDateArray(numRows, array), calc.createDataArray(numRows, numColumns, array), diff);
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }

        //Path filePath = Paths.get("/temp/Write.txt");
        Path filePath = Paths.get("C:\\temp\\Write.txt");
        String s = Float.toString(diff[0]);
        try
        {
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(filePath, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            
            for(int i=0; i<numRows; i++)
            {
                s = Float.toString(diff[i]);
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Message: " + e);
        }
    }
}