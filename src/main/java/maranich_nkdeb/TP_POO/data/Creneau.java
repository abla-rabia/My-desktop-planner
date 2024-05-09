package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Creneau implements Décomposable , Serializable {
    LocalDate jour;

    private LocalTime heure_debut;
    private LocalTime heure_fin;
    Duration duration;

    private long duree;
    private Statut statut;
    private Boolean bloqué;

    public Creneau(LocalTime heure_debut, LocalTime heure_fin) {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        duration = Duration.between(heure_debut, heure_fin);
        duree=duration.toMinutes();
        bloqué=false;
        statut= Statut.LIBRE;
    }

    public Creneau(LocalDate jour, LocalTime heure_debut, LocalTime heure_fin) {
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        duration = Duration.between(heure_debut, heure_fin);
        duree=duration.toMinutes();
        bloqué=false;
        statut= Statut.LIBRE;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public LocalTime getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(LocalTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    public LocalTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(LocalTime heure_fin) {
        this.heure_fin = heure_fin;
    }

    public long getDuree() {
        return duree;
    }

    public void setDuree(long duree) {
        this.duree = duree;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Boolean getBloqué() {
        return bloqué;
    }

    public void setBloqué(Boolean bloqué) {
        this.bloqué = bloqué;
    }
    public void afficherCreneau(){
        System.out.println("Heure début : "+heure_debut+" .");
        System.out.println("Heure fin : "+heure_fin+" .");
        System.out.println("durée : "+duree+" .");
        System.out.println("Statut : "+statut+" .");
        System.out.println("Bloqué : "+bloqué+" .");

    }


    @Override
    public Creneau seDécomposer(Tache tache) {
        Creneau cr2;
        LocalTime newtimefin2=heure_fin;
        LocalTime newtimefin1=heure_fin.minusMinutes(duree-tache.getDurée());
        LocalTime newtimedeb2=newtimefin1;
        cr2=new Creneau(newtimedeb2,newtimefin2);
        heure_fin=newtimefin1;
        duration = Duration.between(heure_debut, heure_fin);
        duree=duration.toMinutes();
        return cr2;
    }
    @Override
    public String toString(){
        return ("Créneau : "+heure_debut+" - "+heure_fin+" .");

    }
}
