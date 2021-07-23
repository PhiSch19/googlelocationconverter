package at.phisch.googlelocationconverter.ui;

import at.phisch.googlelocationconverter.dto.Location;
import at.phisch.googlelocationconverter.logic.LocationReader;
import at.phisch.googlelocationconverter.logic.NominatimOSMClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import tornadofx.control.DateTimePicker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label currentFileLabel;
    @FXML
    public Button startButton;
    @FXML
    public ListView<Location> locationListView;
    @FXML
    public WebView locationWebView;
    @FXML
    public DateTimePicker fromPicker;
    @FXML
    public DateTimePicker toPicker;

    private StringProperty currentFilePath = new SimpleStringProperty();
    private File file;
    private List<Location> locations;

    public void changeFilePressed(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("json", "*.json"));
        file = fileChooser.showOpenDialog(null);
        if(file != null) {
            currentFilePath.set(file.getAbsolutePath());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentFileLabel.textProperty().bind(currentFilePath);
        locationListView.setCellFactory(param -> new LocationListCell());
        locationListView.getSelectionModel().selectedItemProperty().addListener(cl -> {
            Location selectedItem = locationListView.getSelectionModel().getSelectedItem();
            if(selectedItem != null) {
                locationWebView.getEngine().load(NominatimOSMClient.createLink(selectedItem));
            }
        });
    }

    public static class LocationListCell extends ListCell<Location> {

        private VBox box;
        private Label timestamp = new Label();
        private Label details = new Label();

        public LocationListCell(){
            super();
            box = new VBox(timestamp, details);
        }

        @Override
        protected void updateItem(Location location, boolean b) {
            super.updateItem(location, b);
            setGraphic(null);
            if(location != null) {
                timestamp.setText(location.getTimestamp().toString());
                details.setText(location.getDisplayInformation());
                setGraphic(box);
            }
        }
    }

    public void startPressed(ActionEvent actionEvent) throws IOException, InterruptedException {
        locations = new LocationReader(file).read();
        for (Location location : locations) {
            NominatimOSMClient.fillIntoLocation(location);
        }
        locations.forEach(System.out::println);
        locationListView.setItems(FXCollections.observableList(locations));
    }
}
