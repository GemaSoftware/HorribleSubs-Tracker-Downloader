package main;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Anime;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIMainController implements Initializable {

    @FXML
    private ListView<Anime> showListView;

    @FXML
    private TextField searchBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Anime> animeObservableList = FXCollections.observableList(GUIMain.horribleSubWrapper.getCurrentAiringList());
        System.out.println("ADded anime to observable list");
        System.out.println(animeObservableList.size());
        showListView.setItems(animeObservableList);


        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                showListView.setItems(FXCollections.observableList(GUIMain.horribleSubWrapper.searchAnime(newValue)));
        });
    }


}
