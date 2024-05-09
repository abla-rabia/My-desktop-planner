package maranich_nkdeb.TP_POO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatsRendController implements Initializable {
    @FXML
    private Label rendement;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rendement.setText(String.valueOf(AccueilController.getUser().getPlanningActuel().rendementjournalier()));
    }
}
