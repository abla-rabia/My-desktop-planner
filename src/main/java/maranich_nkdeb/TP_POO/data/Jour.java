package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Jour implements Comparable<Jour> , Serializable {
    private LocalDate date;
    private List<Creneau> creneaux;
    private double rendement;

    public double getRendement() {
        return rendement;
    }

    public void setRendement(double rendement) {
        this.rendement = rendement;
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }

    public Jour(LocalDate date) {
        this.date = date;
        creneaux=new ArrayList<Creneau>();
    }
    public void ajouterCreneau(LocalTime heure_debut, LocalTime heure_fin){
        Creneau cr=new Creneau(heure_debut,heure_fin);
        cr.setJour(date);
        creneaux.add(cr);

    }
    public Creneau getCreneau(LocalTime heure_debut){
        Creneau cr_rech=null;
        for (Creneau cr : creneaux) {
            if (cr.getHeure_debut().equals(heure_debut)) {
                cr_rech = cr;
                break;
            }
        }
        return (cr_rech);
    }


    private void afficherliste_cr() {
        for(Creneau cr : creneaux){
            System.out.println("Creneaux : "+cr.toString());
        }
    }
    //methodes de modifications pour chaque champ du créneau . . . .

    public int compareTo(Jour autreJour) {
        return this.date.compareTo(autreJour.getDate());
    }

    public LocalDate getDate() {
        return date;
    }
}
