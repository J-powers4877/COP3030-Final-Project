package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FMCreation
{
    private String sFlightNumber;

    public FMCreation(String sFlightNumber)
    {
        setsFlightNumber(sFlightNumber);
        outputFM();
    }

    private void outputFM()
    {
        List<String> lines = Arrays.asList(
                "1   A B   C D E   F G",
                "2   A B   C D E   F G",
                "3   A B   C D E   F G",
                "4   A B   C D E   F G",
                "5   A B   C D E   F G",
                "6   A B   C D E   F G",
                "7   A B   C D E   F G",
                "8   A B   C D E   F G",
                "9   A B   C D E   F G",
                "10  A B   C D E   F G"
        );
        Path file = Paths.get("src/resources/flightmaps/" + sFlightNumber + ".txt");
        try
        {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void setsFlightNumber(String sFlightNumber)
    {
        this.sFlightNumber = sFlightNumber;
    }

    private String getsFlightNumber()
    {
        return sFlightNumber;
    }
}
