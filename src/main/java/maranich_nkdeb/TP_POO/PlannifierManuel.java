package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import maranich_nkdeb.TP_POO.data.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlannifierManuel implements Initializable {
    ArrayList<Couple> creneaux=new ArrayList<>();

    public ArrayList<Couple> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(ArrayList<Couple> creneaux,boolean blq) {
        this.creneaux = creneaux;
        bloqué=blq;
        listCreneaux.getItems().addAll(creneaux);
    }
    @FXML
    private ChoiceBox<Couple> listCreneaux = new ChoiceBox<>();

    private Creneau creneau_choisi;
    private boolean bloqué;
    private Tache tache;
    private Planning planning;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listCreneaux.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            creneau_choisi = newValue.getCreneau();
            jour=newValue.getJour();
        });
    }
    private Jour jour=null;

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    @FXML
    public  void plannifierClicked(ActionEvent event) {
        Creneau cr2;
        if ((creneau_choisi.getDuree()-tache.getDurée())>planning.getPeriode().getDuree_crn_min()){
            cr2=creneau_choisi.seDécomposer(tache);
            jour.ajouterCreneau(cr2.getHeure_debut(),cr2.getHeure_fin());
        }
        creneau_choisi.setStatut(Statut.OCCUPE);
        if (bloqué)
            creneau_choisi.setBloqué(true);
        else
            creneau_choisi.setBloqué(false);
        System.out.println("elle a été plannifié dans le créneau " + creneau_choisi.toString());
        tache.setCreneau(creneau_choisi);
        planning.getTaches().add(tache);

    }
}
