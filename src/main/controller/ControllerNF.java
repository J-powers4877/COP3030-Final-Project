package main.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.FMCreation;
import main.FMFileCreation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerNF implements Initializable
{
    private String sMeridiem[] = {"AM", "PM"};
    private ArrayList<String> sFlights = new ArrayList<String>();

    private String sNewFlight;

    private String sDTHour;
    private String sATHour;
    private String sDTMin;
    private String sATMin;

    private int iDTHour;
    private int iATHour;
    private int iDTMin;
    private int iATMin;
    @FXML
    private TextArea taFlightPreview;
    @FXML
    private Spinner<Integer> spDTHour;
    @FXML
    private Spinner<Integer> spDTMin;
    @FXML
    private Spinner<Integer> spATHour;
    @FXML
    private Spinner<Integer> spATMin;
    @FXML
    private ChoiceBox<String> cbDTMeridiem;
    @FXML
    private ChoiceBox<String> cbATMeridiem;
    @FXML
    private TextField tfAS;
    @FXML
    private TextField tfDesCity;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField tfFN;
    @FXML
    private TextField tfDepCity;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbATMeridiem.setItems(FXCollections.observableArrayList(sMeridiem));
        cbDTMeridiem.setItems(FXCollections.observableArrayList(sMeridiem));

        InputStream inputStream = this.getClass().getResourceAsStream("/resources/flights.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String sTemp;
            while ((sTemp = bufferedReader.readLine()) != null)
            {
                sFlights.add(sTemp);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String formatText()
    {
        if((cbDTMeridiem.getValue()).equals("PM"))
        {
            iDTHour = spDTHour.getValue() + 12;
            sDTHour = iDTHour + "";
            iDTMin = spDTMin.getValue();
            sDTMin = String.format("%02d",iDTMin);
        }
        else if((cbDTMeridiem.getValue()).equals("AM"))
        {
            iDTHour = spDTHour.getValue();
            sDTHour = String.format("%02d",iDTHour);
            iDTMin = spDTMin.getValue();
            sDTMin = String.format("%02d",iDTMin);
        }

        if((cbATMeridiem.getValue()).equals("PM"))
        {
            iATHour = spATHour.getValue() + 12;
            sATHour = iATHour + "";
            iATMin = spATMin.getValue();
            sATMin = String.format("%02d",iATMin);
        }
        else if((cbATMeridiem.getValue()).equals("AM"))
        {
            iATHour = spATHour.getValue();
            sATHour = String.format("%02d",iATHour);
            iATMin = spATMin.getValue();
            sATMin = String.format("%02d",iATMin);
        }

        String sDT = sDTHour + ":" + sDTMin;
        String sAT = sATHour + ":" + sATMin;

        sNewFlight = String.format
                ("%-10s%-11s%-8s%-9s%-16s%-18s%-10s\n",
                        tfFN.getText(),
                        dpDate.getValue().format(DateTimeFormatter.ofPattern("ddMMMyy")),
                        sDT,
                        sAT,
                        tfDepCity.getText().toUpperCase(),
                        tfDesCity.getText().toUpperCase(),
                        tfAS.getText()
                );

        return sNewFlight;
    }

    private String formatNoSpace()
    {
        if((cbDTMeridiem.getValue()).equals("PM"))
        {
            iDTHour = spDTHour.getValue() + 12;
            sDTHour = iDTHour + "";
            iDTMin = spDTMin.getValue();
            sDTMin = String.format("%02d",iDTMin);
        }
        else if((cbDTMeridiem.getValue()).equals("AM"))
        {
            iDTHour = spDTHour.getValue();
            sDTHour = String.format("%02d",iDTHour);
            iDTMin = spDTMin.getValue();
            sDTMin = String.format("%02d",iDTMin);
        }

        if((cbATMeridiem.getValue()).equals("PM"))
        {
            iATHour = spATHour.getValue() + 12;
            sATHour = iATHour + "";
            iATMin = spATMin.getValue();
            sATMin = String.format("%02d",iATMin);
        }
        else if((cbATMeridiem.getValue()).equals("AM"))
        {
            iATHour = spATHour.getValue();
            sATHour = String.format("%02d",iATHour);
            iATMin = spATMin.getValue();
            sATMin = String.format("%02d",iATMin);
        }

        String sDT = sDTHour + ":" + sDTMin;
        String sAT = sATHour + ":" + sATMin;

        sNewFlight = String.format
                ("%-10s%-11s%-8s%-9s%-16s%-18s%-10s",
                        tfFN.getText(),
                        dpDate.getValue().format(DateTimeFormatter.ofPattern("ddMMMyy")),
                        sDT,
                        sAT,
                        tfDepCity.getText().toUpperCase(),
                        tfDesCity.getText().toUpperCase(),
                        tfAS.getText()
                );

        return sNewFlight;
    }


    @FXML
    void previewFlight(ActionEvent event)
    {
        taFlightPreview.clear();
        taFlightPreview.setText("Flight#   FDate      DTime   ATime    DepartCity      DestCity     AvailableSeats\n");

        taFlightPreview.appendText(formatText());

    }

    @FXML
    void createFlight(ActionEvent event)
    {
        taFlightPreview.clear();
        sFlights.add(formatNoSpace());
        taFlightPreview.setText("Flight#   FDate      DTime   ATime    DepartCity      DestCity     AvailableSeats\n");

        for(int i = 1; i < sFlights.size(); i++)
        {
            taFlightPreview.appendText(sFlights.get(i) + "\n");
        }

        Path file = Paths.get("src/resources/flights.txt");
        try
        {
            Files.write(file, sFlights, Charset.forName("UTF-8"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        FMFileCreation fmFileCreation = new FMFileCreation(tfFN.getText());
        FMCreation fmCreation = new FMCreation(tfFN.getText());
    }

    @SuppressWarnings("Duplicates")
    @FXML
    void mainMenu(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/Main.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}
