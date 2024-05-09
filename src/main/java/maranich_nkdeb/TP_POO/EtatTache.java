package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import maranich_nkdeb.TP_POO.data.Badges;
import maranich_nkdeb.TP_POO.data.Etat;
import maranich_nkdeb.TP_POO.data.Tache;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EtatTache implements Initializable {
    private Tache tache=null;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    @FXML
    private ChoiceBox<Etat> tabEtat = new ChoiceBox<>();

    private Etat etatChoisie;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Etat> etats = new ArrayList<>(Arrays.asList(Etat.values()));
        etats.remove(Etat.UNSCHEDULED);
        tabEtat.getItems().addAll(etats);
        tabEtat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            etatChoisie = newValue;
        });
    }
    @FXML
    public  void validerClicked(ActionEvent event) {
        tache.setEtat(etatChoisie);
        if (AccueilController.getUser().getJourbadge()==null)
            AccueilController.getUser().setJourbadge(LocalDate.now());
        if (!AccueilController.getUser().getJourbadge().isEqual(LocalDate.now())) {
            AccueilController.getUser().setJourbadge(LocalDate.now());
            AccueilController.getUser().setNbTacheJour(0);
            AccueilController.getUser().setNb_badgesExcelent(0);
            AccueilController.getUser().setNb_badgesGOOD(0);
            AccueilController.getUser().setNb_badgesVeryGOOD(0);
            AccueilController.getUser().setNb_encouragement(0);
        }
        if (tache.getCreneau().getJour().isEqual(LocalDate.now())) {
                    if (tache.getEtat().equals(Etat.COMPLETED)) {
                        System.out.println("hello");
                        AccueilController.getUser().setNbTacheJour(AccueilController.getUser().getNbTacheJour()+1);
                        System.out.println(AccueilController.getUser().getNbTacheJour());
                        if (AccueilController.getUser().getNbTacheJour()%AccueilController.getUser().getNb_taches_parJour() == 0){
                            System.out.println("Félicitation ! vous avez maintenant "+AccueilController.getUser().getNb_taches_parJour()+" tâches completes de la journée ! ");
                            AccueilController.getUser().setNb_encouragement(AccueilController.getUser().getNb_encouragement()+1);
                            if (AccueilController.getUser().getNb_encouragement()%5==0){
                                System.out.println("Félicitation ! Vous avez obtenu un badge GOOD ! ");
                                AccueilController.getUser().getPlanningActuel().getBadges().add(Badges.GOOD);
                                AccueilController.getUser().setNb_badgesGOOD(AccueilController.getUser().getNb_badgesGOOD()+1);
                                if (AccueilController.getUser().getNb_badgesGOOD()%3==0){
                                    System.out.println("Félicitation ! Vous avez obtenu un badge Very GOOD ! ");
                                    AccueilController.getUser().getPlanningActuel().getBadges().add(Badges.VERY_GOOD);
                                    AccueilController.getUser().setNb_badgesVeryGOOD(AccueilController.getUser().getNb_badgesVeryGOOD()+1);
                                    if (AccueilController.getUser().getNb_badgesVeryGOOD()%3==0){
                                        System.out.println("Félicitation ! Vous avez obtenu un badge Excellent ! ");
                                        AccueilController.getUser().getPlanningActuel().getBadges().add(Badges.EXCELLENT);
                                        AccueilController.getUser().setNb_badgesExcelent(AccueilController.getUser().getNb_badgesExcelent()+1);
                                    }
                                }
                            }

                        }
                    }
                }
    }
}
