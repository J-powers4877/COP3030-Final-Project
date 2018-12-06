package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class ControllerNR
{
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

    @FXML
    void displayFM(ActionEvent event)
    {
        String flightNumber = tfFN.getText();
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/flightmaps/" + flightNumber + ".txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String sTemp = null;
            while ((sTemp = bufferedReader.readLine()) != null)
            {
                taFM.appendText(sTemp + "\n");
            }

        } catch (IOException e)
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

    }

    @FXML
    void confirmReservation(ActionEvent event)
    {

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
