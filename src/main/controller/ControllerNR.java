package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class ControllerNR implements Initializable
{
    private ArrayList<String> sReservations = new ArrayList<>();
    private ArrayList<String> sFM = new ArrayList<>();

    private String sNewReservation;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/reservations.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String sTemp;
            while ((sTemp = bufferedReader.readLine()) != null)
            {
                sReservations.add(sTemp);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField tfPLN;

    @FXML
    private TextArea taFM;

    @FXML
    private TextField tfPFN;

    @FXML
    private TextField tfRN;

    @FXML
    private TextField tfPID;

    @FXML
    private TextField tfSN;

    @FXML
    private TextField tfFN;

    @FXML
    private TextArea taRes;

    private String formatText()
    {
        String sPassName = tfPFN.getText() + " " + tfPLN.getText();
        String sRowSeat = tfRN.getText() + tfSN.getText();

        sNewReservation = String.format("%-16s%-20s%-10s%-6s", tfPID.getText(),sPassName, sRowSeat, tfFN.getText());

        return sNewReservation;
    }

    @FXML
    void displayFM(ActionEvent event)
    {
        taFM.clear();
        sFM.clear();
        String flightNumber = tfFN.getText();
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/flightmaps/" + flightNumber + ".txt");
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try
            {
                String sTemp;
                while ((sTemp = bufferedReader.readLine()) != null)
                {
                    taFM.appendText(sTemp + "\n");
                    sFM.add(sTemp);
                }
                inputStream.close();
                bufferedReader.close();

            } catch (IOException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Flight Number Error");
                alert.setHeaderText("There was an error with the Flight Number. " + e);
                alert.setContentText("Error... Please enter an actual flight number.");
                alert.showAndWait();
            }
        } catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Flight Number Error");
            alert.setHeaderText("There was an error with the Flight Number." + e);
            alert.setContentText("Error... Please enter an actual flight number.");
            alert.showAndWait();
        }
    }

    @FXML
    void generateReservation(ActionEvent event)
    {
        int iRow = Integer.parseInt(tfRN.getText());
        String sTempRow = sFM.get(iRow-1);

        if (sTempRow.contains(tfSN.getText()))
        {
            taRes.setText("ID              Name            SeatNumber    Flight#\n");
            taRes.appendText(formatText());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seat Letter Error");
            alert.setHeaderText("There was an error with the seat.");
            alert.setContentText("The seat you chose was already filled. Choose a different one.");
            alert.showAndWait();
        }
    }

    @FXML
    void confirmReservation(ActionEvent event)
    {
        int iRow = Integer.parseInt(tfRN.getText());
        String sTempRow = sFM.get(iRow-1);
        String sNewTempRow;

        if (sTempRow.contains(tfSN.getText()))
        {
            sNewTempRow = sTempRow.replace(tfSN.getCharacters(),"X");
            sFM.set(iRow-1,sNewTempRow);

            Path file = Paths.get("src/resources/flightmaps/" + tfFN.getText() + ".txt");
            try
            {
                Files.write(file, sFM, Charset.forName("UTF-8"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seat Letter Error");
            alert.setHeaderText("There was an error with the seat.");
            alert.setContentText("The seat you chose was already filled. \nChoose a different one and generate the reservation again.");
            alert.showAndWait();
        }

        sReservations.add(formatText());
        taRes.setText("ID              Name            SeatNumber    Flight#\n");

        IntStream.range(1, sReservations.size()).forEach(i -> taRes.appendText(sReservations.get(i) + "\n"));

        Path file = Paths.get("src/resources/reservations.txt");
        try
        {
            Files.write(file, sReservations, Charset.forName("UTF-8"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
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
