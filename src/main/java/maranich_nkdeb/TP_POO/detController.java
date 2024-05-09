package maranich_nkdeb.TP_POO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class detController implements Initializable {
    User user=AccueilController.getUser();
    Planning planningAct;

    public void setPlanningAct(Planning planningAct) {
        this.planningAct = planningAct;
    }

    @FXML
    private TableView<Tache> tabTaches;
    @FXML
    private TableView<Projet> tabProjets;
    @FXML
    private TableView<Badges> tabBadges;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Tache, String> name = new TableColumn<>("Tâches");
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Tache, String> etat = new TableColumn<>("Etat");
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
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
        tabTaches.getColumns().addAll(name,etat ,detailsColumn);
        actualiserListeTaches();
        TableColumn<Projet, String> namep = new TableColumn<>("Projet");
        namep.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Projet, Void> detailsColumnp = new TableColumn<>("Détails");
        detailsColumnp.setCellFactory(param -> {
            TableCell<Projet, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Détails");

                {
                    detailsButton.setOnAction(event -> {
                        Projet projet = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabProjets.getScene().getWindow();
                        showDetails2(projet, currentStage);
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
        tabProjets.getColumns().addAll(namep, detailsColumnp);
        actualiserListeProjets();
        TableColumn<Badges, String> badge = new TableColumn<>("Badge");
        actualiserListeBadges();
    }
    private void actualiserListeBadges() {
        ArrayList<Badges> badges = planningAct.getBadges();
        tabBadges.setItems(FXCollections.observableArrayList(badges));
        System.out.println(badges.toString());
    }

    private void actualiserListeProjets() {
        ArrayList<Projet> projets = planningAct.getProjets();
        tabProjets.setItems(FXCollections.observableArrayList(projets));
    }
    private void actualiserListeTaches() {
        ArrayList<Tache> taches = planningAct.getTaches();
        tabTaches.setItems(FXCollections.observableArrayList(taches));
    }

    private void showDetails2(Projet projet, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("unProjet.fxml"));
            Parent root = loader.load();
            UnProjet unProjet=loader.getController();
            unProjet.setProjet(projet);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            unProjet.actualiserListeTaches();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showDetails(Tache tache, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tache.fxml"));
            Parent root = loader.load();
            UneTache uneTache=loader.getController();
            uneTache.setTache(tache);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
