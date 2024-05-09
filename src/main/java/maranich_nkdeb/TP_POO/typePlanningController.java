package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.*;

import java.io.IOException;
import java.util.ArrayList;

public class typePlanningController {
    private Planning planning=AccueilController.getUser().getPlanningActuel();
    private Tache tache;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public void auto(ActionEvent event) {
        boolean decomposable =tache instanceof Tache_decomposable;
        Creneau cr=planning.plannifier(tache,planning);
        if (cr!=null) {

            cr.setStatut(Statut.OCCUPE);
            if (tache.isBloquerLeCreneau())
                cr.setBloqué(true);
            else
                cr.setBloqué(false);
            System.out.println("elle a été plannifié dans le créneau " + cr.toString());
            tache.setCreneau(cr);
            planning.getTaches().add(tache);
            AccueilController.getUser().getTachesNonProgrammées().remove(tache);
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
                    crr.setBloqué(soustache.isBloquerLeCreneau());
                    soustache.setCreneau(crr);
                    planning.getTaches().add(soustache);
                }
            }
            AccueilController.getUser().getTachesNonProgrammées().remove(tache);

        } else{
            if (!AccueilController.getUser().getCalendrier().getCreneausHorsPeriode().isEmpty()) {
                cr = AccueilController.getUser().getCalendrier().getCreneausHorsPeriode().get(1);
                cr.setStatut(Statut.OCCUPE);
                if (tache.isBloquerLeCreneau())
                    cr.setBloqué(true);
                else
                    cr.setBloqué(false);
                System.out.println("elle a été plannifié dans le créneau " + cr.toString());
                tache.setCreneau(cr);
                planning.getTaches().add(tache);
                AccueilController.getUser().getTachesNonProgrammées().remove(tache);
            }
            else {
                System.out.println("le systeme n'a pas pu plannifier la tâche pour le momment ...");
                AccueilController.getUser().ajoutertachenonprog(tache);
            }
        }
    }

    public void manuel(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("periodique.fxml"));
        Parent root2 = loader2.load();
        periodique periodique=loader2.getController();
        periodique.setTache(tache);
        Scene scene2 = new Scene(root2);
        Stage substage = new Stage(); // Créez une nouvelle instance de Stage pour la sous-fenêtre
        substage.setScene(scene2);
        substage.initModality(Modality.APPLICATION_MODAL); // Définissez la modélité de la sous-fenêtre
        substage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Définissez la fenêtre principale comme propriétaire de la sous-fenêtre
        substage.showAndWait();
    }
}
