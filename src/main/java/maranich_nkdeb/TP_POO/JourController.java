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
import maranich_nkdeb.TP_POO.data.Creneau;
import maranich_nkdeb.TP_POO.data.Jour;
import maranich_nkdeb.TP_POO.data.Periode;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class JourController implements Initializable {
    Jour jour;
    Periode periode;
    private int dureecrnmin;

    public int getDureecrnmin() {
        return dureecrnmin;
    }

    public void setDureecrnmin(int dureecrnmin) {
        this.dureecrnmin = dureecrnmin;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    @FXML
    Label title=new Label("");
    public Jour getJour() {
        return jour;
    }
    @FXML
    TableView tabCreneaux;
    @FXML
    public void setJour(Jour jour) {
        this.jour = jour;
        title.setText("Liste des créneaux du "+jour.getDate().toString());
        actualiserListeCreneaux();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<Creneau, LocalTime> heure_deb = new TableColumn<>("Heure début");
        heure_deb.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        TableColumn<Creneau, LocalTime> heure_fin = new TableColumn<>("Heure fin");
        heure_fin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
        TableColumn<Creneau, Void> detailsColumn = new TableColumn<>("Détails");
        detailsColumn.setCellFactory(param -> {
            TableCell<Creneau, Void> cell = new TableCell<>() {
                private final Button detailsButton = new Button("Détails");

                {
                    detailsButton.setOnAction(event -> {
                        Creneau creneau = getTableView().getItems().get(getIndex());
                        Stage currentStage = (Stage) tabCreneaux.getScene().getWindow();
                        showDetails(creneau, currentStage);
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
        tabCreneaux.getColumns().addAll(heure_deb,heure_fin,detailsColumn);


    }
    public void actualiserListeCreneaux() {
        List<Creneau> jours=jour.getCreneaux();
        tabCreneaux.setItems(FXCollections.observableArrayList(jours));
    }
    private void showDetails(Creneau creneau, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Créneau.fxml"));
            Parent root = loader.load();
            CreneauController creneauController = loader.getController();
            creneauController.setCreneau(creneau);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onAddCreneau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newCréneau.fxml"));
        Parent root = loader.load();
        NewCrController newCrController =loader.getController();
        newCrController.setJour(jour);
        newCrController.setPeriode(periode);
        newCrController.setDureecrnmin(dureecrnmin);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
