package main;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Anime;
import models.Episode;
import utilities.WebUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIMainController implements Initializable {

    @FXML
    public TableView<Episode> mainAnimeTable;
    @FXML
    public TableColumn<Episode, String> episodeNumber;
    @FXML
    public TableColumn<Episode, String> episodeMagnetLink;
    @FXML
    public TableColumn<Episode, String> episodeDownloaded;

    @FXML
    private ListView<Anime> showListView;

    @FXML
    private TextField searchBox;

    @FXML
    private Label animeTitle;

    private HorribleSubWrapper horribleSubWrapper;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.horribleSubWrapper = GUIMain.horribleSubWrapper;
        ObservableList<Anime> animeObservableList = FXCollections.observableList(horribleSubWrapper.getCurrentAiringList());
        System.out.println(animeObservableList.size());
        showListView.setItems(animeObservableList);

        //set main show view columns cellfactory to pull correct items

            //gets epiode number to display in table.
            episodeNumber.setCellValueFactory(new PropertyValueFactory<>("episodeNumber"));

            //gets magnet link in quality held in hashmap. Can add choicebox to select quality.
            episodeMagnetLink.setCellValueFactory(ep -> new ReadOnlyStringWrapper(ep.getValue().getEpisodeMagnetLink().get("720p")));

            //test - set the download column to just title for now.
            episodeDownloaded.setCellValueFactory(downloaded -> new ReadOnlyStringWrapper(downloaded.getValue().getEpisodeDownloaded()? "Yes" : "No"));

        //add search bar listener to change list.
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                showListView.setItems(FXCollections.observableList(horribleSubWrapper.searchAnime(newValue)));
        });

        //add double click listener on listview to change main anime/episode view.
        showListView.setOnMouseClicked(mouseClick -> {
            if(mouseClick.getClickCount() == 1){
                Anime currentAnimeSelected = showListView.getSelectionModel().getSelectedItem();
                WebUtils.fetchAnimeID(currentAnimeSelected);
                WebUtils.fetchAnimeEpisodes(currentAnimeSelected);
                animeTitle.setText(currentAnimeSelected.getAnimeTitle());
                mainAnimeTable.setItems(FXCollections.observableArrayList(currentAnimeSelected.getAnimeEpisodeList()));

            }
        });
    }


}
