package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class ControllerDPSM
{
    @FXML
    private TextArea taSM;

    @FXML
    private TextField tfFNum;

    @SuppressWarnings("Duplicates")
    @FXML
    void findSeatMap(ActionEvent event)
    {
        taSM.clear();
        String flightNumber = tfFNum.getText();
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/flightmaps/" + flightNumber + ".txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String sTemp = null;
            while ((sTemp = bufferedReader.readLine()) != null)
            {
                taSM.appendText(sTemp + "\n");
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
