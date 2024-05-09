package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import maranich_nkdeb.TP_POO.data.Categorie;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametresController implements Initializable {
    @FXML
    TextField nbTachesParJour;
    @FXML
    TextField addCateg;
@FXML
    public void modifierClicked(ActionEvent event) {
        int nbb= Integer.parseInt(nbTachesParJour.getText());
        AccueilController.getUser().setNb_taches_parJour(nbb);
        String categ=addCateg.getText();
        AccueilController.getUser().getCategoriesPerso().add(categ);
        System.out.println("Categorie ajoutee avec succes ! ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nbTachesParJour.setText(String.valueOf(AccueilController.getUser().getNb_taches_parJour()));
    }
}
