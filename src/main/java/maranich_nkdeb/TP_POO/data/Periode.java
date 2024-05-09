package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Periode implements Serializable {
    private LocalDate date_debut;
    private LocalDate date_fin;
    private LinkedList<Jour> joursPeriode ;

    public void setDate_fin(LocalDate date_fin) {

        LocalDate currentDate = this.date_fin;
        while (!currentDate.isAfter(date_fin)) {
            Jour jour = new Jour(currentDate);
            joursPeriode.add(jour);
            currentDate = currentDate.plusDays(1);
        }
        this.date_fin = date_fin;
    }

    private int duree_crn_min;
    private static int nb=0;
    private String name;

    public Periode(LocalDate date_debut, LocalDate date_fin)  {
        this.date_debut = date_debut;
        this.date_fin = date_fin;

        joursPeriode=new LinkedList<Jour>();
        LocalDate currentDate = date_debut;
        while (!currentDate.isAfter(date_fin)) {
            Jour jour = new Jour(currentDate);
            joursPeriode.add(jour);
            currentDate = currentDate.plusDays(1);
        }
        nb++;
        name="Periode "+nb;
    }

    public Periode(LocalDate date_debut, LocalDate date_fin, int duree_crn_min) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.duree_crn_min = duree_crn_min;
        joursPeriode=new LinkedList<Jour>();
        LocalDate currentDate = date_debut;
        while (!currentDate.isAfter(date_fin)) {
            Jour jour = new Jour(currentDate);
            joursPeriode.add(jour);
            currentDate = currentDate.plusDays(1);
        }
        nb++;
        name="Periode "+nb;
    }
    public void ajouterJour(LocalDate date){
        Jour jour=new Jour(date);
        joursPeriode.add(jour);
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public LinkedList<Jour> getJoursPeriode() {
        return joursPeriode;
    }

    public int getDuree_crn_min() {
        return duree_crn_min;
    }

    public String getName() {
        return name;
    }
    public String toString(){
        return date_debut.toString();
    }



}
