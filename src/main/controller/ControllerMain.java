package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMain
{

    @FXML
    private Button exitButton;

    @FXML
    void loadNF(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/NF.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void loadNR(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/NR.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void loadDPSM(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/DPSM.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void loadAF(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/AF.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void loadLAPAF(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/APAF.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void loadLAPSF(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/APSF.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void exitProgram(ActionEvent event)
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
