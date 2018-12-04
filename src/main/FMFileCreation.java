package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FMFileCreation
{
    private String sFlightNumber;

    public FMFileCreation(String sFlightNumber)
    {
        setsFlightNumber(sFlightNumber);
        createFile();
    }

    private void createFile()
    {
        try
        {
            PrintWriter printWriter = new PrintWriter("src/resources/flightmaps/" + sFlightNumber + ".txt");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private String getsFlightNumber()
    {
        return sFlightNumber;
    }

    private void setsFlightNumber(String sFlightNumber)
    {
        this.sFlightNumber = sFlightNumber;
    }
}
