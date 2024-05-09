package maranich_nkdeb.TP_POO;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.Calendrier;
import maranich_nkdeb.TP_POO.data.Periode;
import maranich_nkdeb.TP_POO.data.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PeriodesController implements Initializable {
    private Calendrier calendrier;
    private User user = AccueilController.getUser();

    @FXML
    private TableView<Periode> tabPeriodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user != null)
            calendrier = user.getCalendrier();

        TableColumn<Periode, String> name = new TableColumn<>("Periode");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Periode, LocalDate> dateDebutColumn = new TableColumn<>("Date de début");
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("date_debut"));

        TableColumn<Periode, LocalDate> dateFinColumn = new TableColumn<>("Date de fin");
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        TableColumn<Periode, Integer> dureeMin = new TableColumn<>("Durée min du créneau");
        dureeMin.setCellValueFactory(new PropertyValueFactory<>("duree_crn_min"));

        TableColumn<Periode, Void> detailsColumn = new TableColumn<>("Détails");
        detailsColumn.setCellFactory(param -> {
            TableCell<Periode, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Détails");

                {
                    detailsButton.setOnAction(event -> {
                        Periode periode = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabPeriodes.getScene().getWindow();
                        showDetails(periode, currentStage);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(detailsButton);
                    }
                }
            };
            return cell;
        });
        tabPeriodes.getColumns().addAll(name, dateDebutColumn, dateFinColumn, dureeMin, detailsColumn);
        actualiserListePeriodes();
    }

    private void actualiserListePeriodes() {
        ArrayList<Periode> periodes = calendrier.getPeriodes();
        tabPeriodes.setItems(FXCollections.observableArrayList(periodes));
    }

    private void showDetails(Periode periode, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("periodeActuelle.fxml"));
            Parent root = loader.load();
            PeriodeController periodeController = loader.getController();
            periodeController.setPeriode(periode);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            periodeController.actualiserListeJours();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
