package maranich_nkdeb.TP_POO;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.Projet;
import maranich_nkdeb.TP_POO.data.Tache;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UnProjet implements Initializable, Serializable {
    private Projet projet;

    public void setProjet(Projet projet) {
        this.projet = projet;
        nom.setText(projet.getNom());
        description.setText(projet.getDescription());
    }

    @FXML
    Label nom=new Label("");
    @FXML
    Label description=new Label("");
    @FXML
    private TableView<Tache> tabTaches;

    public void ajouterTache(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newTacheProjet.fxml"));
        Parent root = loader.load();
        AjouterTachepController ajouterTachepController=loader.getController();
        ajouterTachepController.setProjet(projet);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Tache, String> name = new TableColumn<>("Tâches");
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Tache, Void> detailsColumn = new TableColumn<>("Détails");
        detailsColumn.setCellFactory(param -> {
            TableCell<Tache, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Détails");

                {
                    detailsButton.setOnAction(event -> {
                        Tache tache = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabTaches.getScene().getWindow();
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
        tabTaches.getColumns().addAll(name, detailsColumn);


    }
    public void actualiserListeTaches() {
        ArrayList<Tache> taches = projet.getS_taches();
        tabTaches.setItems(FXCollections.observableArrayList(taches));
    }
    private void showDetails(Tache tache, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tache.fxml"));
            Parent root = loader.load();
            UneTache uneTache=loader.getController();
            uneTache.setTache(tache);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            //periodeController.actualiserListeJours();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
