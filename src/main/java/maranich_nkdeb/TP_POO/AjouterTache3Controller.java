package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import maranich_nkdeb.TP_POO.data.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterTache3Controller implements Initializable {
    private Planning planning=AccueilController.getUser().getPlanningActuel();
    private Tache tache;
    @FXML
    TextField nom;
    @FXML
    TextField duree;
    @FXML
    TextField periodicite;
    @FXML
    ChoiceBox<Priorité> priorite=new ChoiceBox();
    @FXML
    DatePicker datelim=new DatePicker();
    @FXML
    ChoiceBox<Categorie> categ=new ChoiceBox<>();
    @FXML
    RadioButton oui;
    @FXML
    RadioButton non;
    @FXML
    RadioButton nonBQ;
    @FXML
    RadioButton ouiBQ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorite.getItems().addAll(Priorité.values());
        categ.getItems().addAll(Categorie.values());
    }
    @FXML
    public void creerLaTache(ActionEvent event){
        String name=nom.getText();
        long dur=Long.parseLong(duree.getText());
        Priorité priorité=priorite.getValue();
        LocalDate datlimite=datelim.getValue();
        Categorie cat=categ.getValue();
        Boolean decomposable=oui.isSelected();
        Boolean bloqué=ouiBQ.isSelected();
        tache = null;
        if (!decomposable){
            tache=new Tache_simple(name,dur,priorité,datlimite,cat,bloqué);
        }
        else
            tache=new Tache_decomposable(name,dur,priorité,datlimite,cat,bloqué);
        tache.setBloquerLeCreneau(bloqué);
        AccueilController.getUser().ajoutertachenonprog(tache);
    }
}
