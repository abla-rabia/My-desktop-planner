package maranich_nkdeb.TP_POO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatsBadgeController implements Initializable {
    @FXML
    private Label good;
    @FXML
    private Label verygood;
    @FXML
    private Label excellent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Integer> etats=AccueilController.getUser().getPlanningActuel().statsBadges();
        good.setText(etats.get(0).toString());
        verygood.setText(etats.get(1).toString());
        excellent.setText(etats.get(2).toString());

    }
}
