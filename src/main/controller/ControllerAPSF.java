package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAPSF implements Initializable
{
    List<String> allFlights = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        allFlights.clear();
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/reservations.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String sTemp = null;
            while ((sTemp = bufferedReader.readLine()) != null)
            {
                allFlights.add(sTemp + "\n");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private TextArea taAPSF;

    @FXML
    private TextField tfFN;

    @FXML
    void findFlight(ActionEvent event)
    {
        taAPSF.setText(allFlights.get(0));
        String flightNumber = tfFN.getText();
        for (int i = 0; i < allFlights.size(); i++)
        {
            if (allFlights.get(i).contains(flightNumber))
            {
                taAPSF.appendText(allFlights.get(i));
            }
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
