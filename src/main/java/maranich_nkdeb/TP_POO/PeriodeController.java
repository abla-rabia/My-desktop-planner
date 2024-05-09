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
import maranich_nkdeb.TP_POO.data.Jour;
import maranich_nkdeb.TP_POO.data.Periode;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class PeriodeController implements Initializable {
    private Periode periode;
    private String namePeriode;
    @FXML
    private TableView<Jour> tabJours;
    @FXML
    private Label title=new Label("");

    public void setPeriode(Periode periode) {
        this.periode = periode;
        setNamePeriode(periode);
    }

    @FXML
    public void setNamePeriode(Periode periode) {
        this.namePeriode = periode.getName();
        title.setText(namePeriode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Jour, String> date = new TableColumn<>("Jour");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Jour, Void> detailsColumn = new TableColumn<>("Détails");
        detailsColumn.setCellFactory(param -> {
            TableCell<Jour, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Détails");

                {
                    detailsButton.setOnAction(event -> {
                        Jour jour = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabJours.getScene().getWindow();
                        showDetails(jour, currentStage);
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
        tabJours.getColumns().addAll(date,detailsColumn);

    }
    public void actualiserListeJours() {
        LinkedList<Jour> jours=periode.getJoursPeriode();
        tabJours.setItems(FXCollections.observableArrayList(jours));
    }
    private void showDetails(Jour jour, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Jour.fxml"));
            Parent root = loader.load();
            JourController jourController = loader.getController();
            jourController.setJour(jour);
            jourController.setDureecrnmin(periode.getDuree_crn_min());
            jourController.setPeriode(periode);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
