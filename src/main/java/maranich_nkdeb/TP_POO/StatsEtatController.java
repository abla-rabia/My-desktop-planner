package maranich_nkdeb.TP_POO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatsEtatController implements Initializable {
    @FXML
    private Label completed;
    @FXML
    private Label cancelled;
    @FXML
    private Label inprogress;
    @FXML
    private Label notrealized;
    @FXML
    private Label delayed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Integer> etats=AccueilController.getUser().getPlanningActuel().etatDeRealisationdeTachesJournee();
        completed.setText(etats.get(0).toString());
        cancelled.setText(etats.get(1).toString());
        inprogress.setText(etats.get(2).toString());
        delayed.setText(etats.get(3).toString());
        notrealized.setText(etats.get(4).toString());
    }
}
