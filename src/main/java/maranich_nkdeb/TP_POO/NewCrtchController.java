package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import maranich_nkdeb.TP_POO.data.Creneau;
import maranich_nkdeb.TP_POO.data.DatelimException;
import maranich_nkdeb.TP_POO.data.Tache;
import maranich_nkdeb.TP_POO.data.dureeInsuffisanteException;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NewCrtchController implements Initializable {
    private Tache tache;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    @FXML
    DatePicker lejour;
    @FXML
    private Spinner<Integer> time_spinner;
    @FXML
    private Spinner<Integer> time_spinner2;
    @FXML
    private Spinner<Integer> time_spinnerr;
    @FXML
    private Spinner<Integer> time_spinnerr2;
    int current_value;
    int current_valuee;
    int current_value2;
    int current_valuee2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        valueFactory.setValue(0);
        time_spinner.setValueFactory(valueFactory);
        current_value = time_spinner.getValue();

        time_spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            current_value = newValue;
            System.out.println("Value selected for time_spinner: " + current_value);
        });

        SpinnerValueFactory<Integer> valueFactoryy =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        valueFactoryy.setValue(0);
        time_spinnerr.setValueFactory(valueFactoryy);
        current_valuee = time_spinnerr.getValue();

        time_spinnerr.valueProperty().addListener((observable, oldValue, newValue) -> {
            current_valuee = newValue;
            System.out.println("Value selected for time_spinner: " + current_valuee);
        });

        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory2.setValue(0);
        time_spinner2.setValueFactory(valueFactory2);
        current_value2 = time_spinner2.getValue();

        time_spinner2.valueProperty().addListener((observable, oldValue, newValue) -> {
            current_value2 = newValue;
            System.out.println("Value selected for time_spinner2: " + current_value2);
        });
        SpinnerValueFactory<Integer> valueFactoryy2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactoryy2.setValue(0);
        time_spinnerr2.setValueFactory(valueFactoryy2);
        current_valuee2 = time_spinnerr2.getValue();

        time_spinnerr2.valueProperty().addListener((observable, oldValue, newValue) -> {
            current_valuee2 = newValue;
            System.out.println("Value selected for time_spinner2: " + current_valuee2);
        });
    }
    @FXML
    public void programmer(ActionEvent event) throws dureeInsuffisanteException,DatelimException{
        LocalTime heure_deb = LocalTime.of(current_value2, current_value);
        LocalTime heure_fin = LocalTime.of(current_valuee2, current_valuee);
        Duration dru=Duration.between(heure_deb,heure_fin);
        long duree=dru.toMinutes();
        if (duree<tache.getDurée())
            throw new dureeInsuffisanteException("Erreur ! duree Insuffisante ! ");
        else {
            LocalDate jour = lejour.getValue();
            if (jour.isAfter(tache.getDate_lim()))
                throw new DatelimException("Erreur ! le creneau ne respecte pas la date limite ! ");
            else {
                Creneau cr2;
                Creneau cr = new Creneau(jour, heure_deb, heure_fin);
                if (duree-tache.getDurée()>AccueilController.getUser().getPlanningActuel().getPeriode().getDuree_crn_min()){
                    cr2=cr.seDécomposer(tache);
                }
                AccueilController.getUser().getCalendrier().getCreneausHorsPeriode().add(cr);
                tache.setCreneau(cr);
                AccueilController.getUser().getPlanningActuel().getTaches().add(tache);
                AccueilController.getUser().getTachesNonProgrammées().remove(tache);
            }
        }


    }
}
