package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAF implements Initializable
{
    @FXML
    private TextArea taAllFlights;

    @SuppressWarnings("Duplicates")
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/flights.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String sTemp = null;
            while ((sTemp = bufferedReader.readLine()) != null)
            {
                taAllFlights.appendText(sTemp + "\n");
            }

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
