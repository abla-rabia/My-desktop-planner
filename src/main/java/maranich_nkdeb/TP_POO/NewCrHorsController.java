package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import maranich_nkdeb.TP_POO.data.Creneau;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NewCrHorsController implements Initializable {
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
    @FXML
    DatePicker jour;

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
    public  void ajouterClicked(ActionEvent event) {
        LocalTime heure_deb = LocalTime.of(current_value2, current_value);
        LocalTime heure_fin = LocalTime.of(current_valuee2, current_valuee);
        LocalDate jr=jour.getValue();
        Creneau cr=new Creneau(jr,heure_deb,heure_fin);
        AccueilController.getUser().getCalendrier().getCreneausHorsPeriode().add(cr);

    }
}
