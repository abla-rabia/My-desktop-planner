package maranich_nkdeb.TP_POO;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.Tache;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TachesnonController implements Initializable {
    @FXML
    private TableView<Tache> tabtachesnonprgm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Tache, String> name = new TableColumn<>("Tache");
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Tache, Void> detailsColumn = new TableColumn<>("Planifier");
        detailsColumn.setCellFactory(param -> {
            TableCell<Tache, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Planifier");

                {
                    detailsButton.setOnAction(event -> {
                        Tache tache = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabtachesnonprgm.getScene().getWindow();
                        showDetails(tache, currentStage);
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
        tabtachesnonprgm.getColumns().addAll(name, detailsColumn);
        actualiserListeTaches();
    }

    private void actualiserListeTaches() {
        ArrayList<Tache> taches = AccueilController.getUser().getTachesNonProgrammées();
        tabtachesnonprgm.setItems(FXCollections.observableArrayList(taches));
    }

    private void showDetails(Tache tache, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("typePlanning.fxml"));
            Parent root = loader.load();
            //PeriodeController periodeController = loader.getController();
            //periodeController.setPeriode(periode);
            typePlanningController typePlanningController=loader.getController();
            typePlanningController.setTache(tache);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            //periodeController.actualiserListeJours();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
