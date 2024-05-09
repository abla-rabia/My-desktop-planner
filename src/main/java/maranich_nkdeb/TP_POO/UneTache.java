package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.Tache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UneTache implements Initializable {
    private Tache tache;
    @FXML
    Label nom=new Label("");
    @FXML
    Label duree=new Label("");
    @FXML
    Label datelim =new Label("");
    @FXML
    Label categorie =new Label("");
    @FXML
    Label priorite =new Label("");
    @FXML
    Label etat =new Label("");
    @FXML
    Label nomTache =new Label("");
    public void setTache(Tache tache) {
        this.tache = tache;
        nom.setText(tache.getNom());
        nomTache.setText(tache.getNom());
        datelim.setText(tache.getDate_lim().toString());
        duree.setText(String.valueOf(tache.getDurée()));
        categorie.setText(tache.getCategorie().toString());
        priorite.setText(tache.getPriorité().toString());
        etat.setText(tache.getEtat().toString());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void modifierTache(ActionEvent event) {
    }

    public void changerEtat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EtatTache.fxml"));
        Parent root = loader.load();
        EtatTache etatTache=loader.getController();
        etatTache.setTache(tache);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
