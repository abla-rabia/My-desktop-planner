package maranich_nkdeb.TP_POO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatsencouragementController implements Initializable {
    @FXML
    private Label encouragement;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encouragement.setText(String.valueOf(AccueilController.getUser().getPlanningActuel().nbfoisencouragement()));
    }
}
