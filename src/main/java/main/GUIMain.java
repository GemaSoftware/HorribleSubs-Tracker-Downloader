package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIMain extends Application {

    static HorribleSubWrapper horribleSubWrapper;


    public GUIMain(){
        //Init the horrible subs wrapper.
        this.horribleSubWrapper = new HorribleSubWrapper();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/horiblesubs.fxml"));
        Scene scene = new Scene(root, 900, 550);
        stage.setTitle("HorribleSubs-Tracker");
        stage.setScene(scene);
        stage.show();

    }

    //Future - Add possible JavaFX GUI to supplement program. Not needed yet.

}
