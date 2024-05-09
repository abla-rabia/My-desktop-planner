package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import maranich_nkdeb.TP_POO.data.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class AjouterTache2Controller implements Initializable {
    private Planning planning=AccueilController.getUser().getPlanningActuel();
    private Tache tache;
    @FXML
    TextField nom;
    @FXML
    TextField duree;
    @FXML
    ChoiceBox<Priorité> priorite=new ChoiceBox();
    @FXML
    DatePicker datelim=new DatePicker();
    @FXML
    ChoiceBox<Categorie> categ=new ChoiceBox<>();
    @FXML
    RadioButton oui;
    @FXML
    RadioButton non;
    @FXML
    RadioButton nonBQ;
    @FXML
    RadioButton ouiBQ;
    ArrayList<Tache> taches=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorite.getItems().addAll(Priorité.values());
        categ.getItems().addAll(Categorie.values());
    }

    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }
    @FXML
    public void onAjoutClicked(ActionEvent event) throws IOException {
        //phase d'ajout de la tache
        String name=nom.getText();
        long dur=Long.parseLong(duree.getText());
        Priorité priorité=priorite.getValue();
        LocalDate datlimite=datelim.getValue();
        Categorie cat=categ.getValue();
        Boolean decomposable=oui.isSelected();
        Boolean bloqué=ouiBQ.isSelected();
        tache = null;
        if (!decomposable){
            tache=new Tache_simple(name,dur,priorité,datlimite,cat);
        }
        else
            tache=new Tache_decomposable(name,dur,priorité,datlimite,cat);
        taches.add(tache);



    }
    @FXML
    public void plannifier(ActionEvent event){
        Collections.sort(taches);
        String name=nom.getText();
        long dur=Long.parseLong(duree.getText());
        Priorité priorité=priorite.getValue();
        LocalDate datlimite=datelim.getValue();
        Categorie cat=categ.getValue();
        Boolean decomposable=oui.isSelected();
        Boolean bloqué=ouiBQ.isSelected();
        tache = null;
        if (!decomposable){
            tache=new Tache_simple(name,dur,priorité,datlimite,cat);
        }
        else
            tache=new Tache_decomposable(name,dur,priorité,datlimite,cat);
        taches.add(tache);

        for (Tache tache:taches){
            Creneau cr=planning.plannifier(tache,planning);
            if (cr!=null) {

                cr.setStatut(Statut.OCCUPE);

                System.out.println("elle a été plannifié dans le créneau " + cr.toString());
                tache.setCreneau(cr);
                planning.getTaches().add(tache);
            } else if (decomposable) {
                ArrayList<Creneau> crs=planning.plannifierdecomp(tache,planning);
                if (crs!=null){
            /*Creneau cr2;
            if (d-tache.getDurée()>planning.getPeriode().getDuree_crn_min()){
                cr2=last.seDécomposer(tache);
                jour.ajouterCreneau(cr2.getHeure_debut(),cr2.getHeure_fin());
            }*/
                    Tache soustache;
                    int cmp=1;
                    for (Creneau crr : crs){
                        soustache=new Tache_simple(tache.getNom()+cmp,crr.getDuree(),tache.getPriorité(),tache.getDate_lim(),tache.getCategorie() );
                        cmp++;
                        Tache_decomposable decomposableTache = (Tache_decomposable) tache;
                        decomposableTache.seDécomposer(soustache);
                        crr.setStatut(Statut.OCCUPE);
                        crr.setBloqué(false);
                        soustache.setCreneau(crr);
                        planning.getTaches().add(soustache);
                    }
                }

            } else{
                System.out.println("La systeme n'a pas trouve un créneau qui convient, la tache a été sauvegardee dans la liste des taches non programmees avec l'etat unscheduled !");
                AccueilController.getUser().ajoutertachenonprog(tache);
            }

        }
    }
}
