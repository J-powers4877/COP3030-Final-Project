package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMain
{

    @FXML
    private Button exitButton;

    @FXML
    private Button afButton;

    @FXML
    private Button lapafButton;

    @FXML
    private Button nrButton;

    @FXML
    private Button nfButton;

    @FXML
    private Button lapsfButton;

    @FXML
    private Button dpsmButton;

    @FXML
    void loadNF(ActionEvent event)
    {

    }

    @FXML
    void loadNR(ActionEvent event)
    {

    }

    @FXML
    void loadDPSM(ActionEvent event)
    {

    }

    @FXML
    void loadAF(ActionEvent event)
    {

    }

    @FXML
    void loadLAPAF(ActionEvent event)
    {

    }

    @FXML
    void loadLAPSF(ActionEvent event)
    {

    }

    @FXML
    void exitProgram(ActionEvent event)
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}


/*public class ControllerMain {

    @FXML
    private AnchorPane rootPane;

    private Stage mStage;

    public void setStage(Stage mStage)
    {
        this.mStage = mStage;
    }

    @FXML
    void loadSecond(ActionEvent event) throws IOException
    {
        Parent newView = FXMLLoader.load(getClass().getResource("/resources/view/Two.fxml"));
        Scene scene = new Scene(newView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}*/
