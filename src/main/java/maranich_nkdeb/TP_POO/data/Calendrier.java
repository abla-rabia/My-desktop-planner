package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Calendrier implements Serializable {
    private ArrayList<Periode> periodes;
    private ArrayList<Creneau> creneausHorsPeriode=null;

    public ArrayList<Creneau> getCreneausHorsPeriode() {
        if (creneausHorsPeriode==null)
            creneausHorsPeriode=new ArrayList<>();
        return creneausHorsPeriode;
    }

    public void setCreneausHorsPeriode(ArrayList<Creneau> creneausHorsPeriode) {
        this.creneausHorsPeriode = creneausHorsPeriode;
    }

    public Calendrier() {
        periodes=new ArrayList<Periode>();
    }
    public void ajouterPeriode(LocalDate date_deb, LocalDate date_fin){
        Periode periode=new Periode(date_deb,date_fin);
        periodes.add(periode);
        creneausHorsPeriode=new ArrayList<>();
    }
    public void ajouterPeriode(LocalDate date_deb, LocalDate date_fin,int duree_min){
        Periode periode=new Periode(date_deb,date_fin,duree_min);
        periodes.add(periode);
    }

    public ArrayList<Periode> getPeriodes() {
        return periodes;
    }
}
