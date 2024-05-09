package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AjouterTacheController implements Initializable {
    private Planning planning=AccueilController.getUser().getPlanningActuel();
    private Tache tache;
    @FXML
    TextField nom;
    @FXML
    TextField duree;
    @FXML
    TextField periodicite;
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
    @FXML
    public  void ajouterTache(ActionEvent event){
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
        if (datlimite.isAfter(AccueilController.getUser().getPlanningActuel().getPeriode().getDate_fin()))
            AccueilController.getUser().getPlanningActuel().getPeriode().setDate_fin(datlimite);
        System.out.println(tache.toString());
        //phase de plannification automatique
        Creneau cr=planning.plannifier(tache,planning);
        if (cr!=null) {

            cr.setStatut(Statut.OCCUPE);
            if (bloqué)
                cr.setBloqué(true);
            else
                cr.setBloqué(false);
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
                        crr.setBloqué(bloqué);
                        soustache.setCreneau(crr);
                        planning.getTaches().add(soustache);
                    }
            }

        } else{
            System.out.println("La systeme n'a pas trouve un créneau qui convient, la tache a été sauvegardee dans la liste des taches non programmees avec l'etat unscheduled !");
            AccueilController.getUser().ajoutertachenonprog(tache);
        }

    }
    @FXML
    public void onAjoutClicked(ActionEvent event) throws IOException {
        ArrayList<Tache> taches=new ArrayList<>();
        //phase d'ajout de la tache
        String name=nom.getText();
        long dur=Long.parseLong(duree.getText());
        Priorité priorité=priorite.getValue();
        LocalDate datlimite=datelim.getValue();
        Categorie cat=categ.getValue();
        Boolean decomposable=oui.isSelected();
        Boolean bloqué=ouiBQ.isSelected();
        tache = null;
        if (datlimite.isAfter(AccueilController.getUser().getPlanningActuel().getPeriode().getDate_fin()))
            AccueilController.getUser().getPlanningActuel().getPeriode().setDate_fin(datlimite);
        if (!decomposable){
            tache=new Tache_simple(name,dur,priorité,datlimite,cat);
        }
        else
            tache=new Tache_decomposable(name,dur,priorité,datlimite,cat);
        taches.add(tache);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("newwTache.fxml"));
            Parent root = loader.load();
            AjouterTache2Controller ajouterTache2Controller=loader.getController();
            ajouterTache2Controller.setTaches(taches);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorite.getItems().addAll(Priorité.values());
        categ.getItems().addAll(Categorie.values());
    }
    @FXML
    public void plannifierManuel(ActionEvent event) throws IOException {
        String name=nom.getText();
        long dur=Long.parseLong(duree.getText());
        Priorité priorité=priorite.getValue();
        LocalDate datlimite=datelim.getValue();
        Categorie cat=categ.getValue();
        Boolean decomposable=oui.isSelected();
        Boolean bloqué=ouiBQ.isSelected();
        tache=new Tache_simple(name,dur,priorité,datlimite,cat);
        ArrayList<Couple> crs=new ArrayList<>();
        if (datlimite.isAfter(AccueilController.getUser().getPlanningActuel().getPeriode().getDate_fin()))
            AccueilController.getUser().getPlanningActuel().getPeriode().setDate_fin(datlimite);

        for (Jour j:planning.getPeriode().getJoursPeriode()){
            if (j.getDate().isBefore(tache.getDate_lim()) ){
                for (Creneau cr :j.getCreneaux()){
                    if(cr.getDuree()>=tache.getDurée() && cr.getStatut()==Statut.LIBRE){
                        crs.add(new Couple(j,cr));
                    }
                }
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("plannifierManuel.fxml"));
        Parent root = loader.load();
        PlannifierManuel plannifierManuel=loader.getController();
        plannifierManuel.setCreneaux(crs,bloqué);
        plannifierManuel.setTache(tache);
        plannifierManuel.setPlanning(planning);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void creerLaTache(ActionEvent event){
        String name=nom.getText();
        long dur=Long.parseLong(duree.getText());
        Priorité priorité=priorite.getValue();
        LocalDate datlimite=datelim.getValue();
        Categorie cat=categ.getValue();
        Boolean decomposable=oui.isSelected();
        Boolean bloqué=ouiBQ.isSelected();
        tache = null;
        if (!decomposable){
            tache=new Tache_simple(name,dur,priorité,datlimite,cat,bloqué);
        }
        else
            tache=new Tache_decomposable(name,dur,priorité,datlimite,cat,bloqué);
        AccueilController.getUser().ajoutertachenonprog(tache);
    }
}
