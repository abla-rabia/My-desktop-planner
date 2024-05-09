package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import maranich_nkdeb.TP_POO.data.Calendrier;
import maranich_nkdeb.TP_POO.data.Periode;

import java.net.URL;
import java.util.ResourceBundle;

public class newPlanningController implements Initializable {
    Calendrier calendrier = AccueilController.getUser().getCalendrier();

    @FXML
    private ChoiceBox<Periode> listPeriodes = new ChoiceBox<>();

    private Periode periodeChoisie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listPeriodes.getItems().addAll(calendrier.getPeriodes());
        listPeriodes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            periodeChoisie = newValue;
        });
    }


    @FXML
    public  void ajouterClicked(ActionEvent event) {

        AccueilController.getUser().ajouterPlanning(periodeChoisie);
        System.out.println(AccueilController.getUser().getPlannings());
    }
}
