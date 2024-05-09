package maranich_nkdeb.TP_POO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import maranich_nkdeb.TP_POO.data.Creneau;

import java.net.URL;
import java.util.ResourceBundle;

public class CreneauController implements Initializable {
    private Creneau creneau;
    @FXML
    Label hdebut=new Label("");
    @FXML
    Label hfin=new Label("");
    @FXML
    Label statut =new Label("");
    @FXML
    Label bq =new Label("");
    @FXML
    Label dr =new Label("");
    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
        hdebut.setText(creneau.getHeure_debut().toString());
        hfin.setText(creneau.getHeure_fin().toString());
        statut.setText(creneau.getStatut().toString());
        bq.setText(creneau.getBloqué().toString());
        dr.setText(String.valueOf((creneau.getDuree())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }
}
