package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import maranich_nkdeb.TP_POO.data.Planning;
import maranich_nkdeb.TP_POO.data.Projet;

public class newProjetController {
    private Planning planning=AccueilController.getUser().getPlanningActuel();
    @FXML
    TextField nom;
    @FXML
    TextField description;
    @FXML
    public void valider(ActionEvent event) {
        String name=nom.getText();
        String descrp=description.getText();
        Projet projet =new Projet(name,descrp);
        planning.getProjets().add(projet);
    }
    @FXML
    public void onAjoutClicked(ActionEvent event) {
    }
}
