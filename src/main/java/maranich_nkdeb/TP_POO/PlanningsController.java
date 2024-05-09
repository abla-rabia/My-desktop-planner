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
import maranich_nkdeb.TP_POO.data.Periode;
import maranich_nkdeb.TP_POO.data.Planning;
import maranich_nkdeb.TP_POO.data.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlanningsController implements Initializable {
    private User user = AccueilController.getUser();

    @FXML
    private TableView<Planning> tabPlannings;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<Planning, String> name = new TableColumn<>("Planning");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Planning, Periode> periode = new TableColumn<>("Periode");
        periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
        TableColumn<Planning, Void> detailsColumn = new TableColumn<>("Détails");
        detailsColumn.setCellFactory(param -> {
            TableCell<Planning, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Détails");

                {
                    detailsButton.setOnAction(event -> {
                        Planning planning = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabPlannings.getScene().getWindow();
                        showDetails(planning, currentStage);
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
        tabPlannings.getColumns().addAll(name,periode, detailsColumn);
        actualiserListePlannings();
    }

    private void actualiserListePlannings() {
        ArrayList<Planning> plannings = user.getPlannings();
        tabPlannings.setItems(FXCollections.observableArrayList(plannings));
    }

    private void showDetails(Planning planning, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DET.fxml"));
            Parent root = loader.load();
            detController detController=loader.getController();
            detController.setPlanningAct(planning);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            //periodeController.actualiserListeJours();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
