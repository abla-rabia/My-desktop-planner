package maranich_nkdeb.TP_POO.data;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<String> categoriesPerso=new ArrayList<>();

    public ArrayList<String> getCategoriesPerso() {
        return categoriesPerso;
    }

    public void setCategoriesPerso(ArrayList<String> categoriesPerso) {
        this.categoriesPerso = categoriesPerso;
    }

    //attributs
    private String pseudo;
    private LocalDate jourbadge=null;

    public LocalDate getJourbadge() {
        return jourbadge;
    }

    public void setJourbadge(LocalDate jourbadge) {
        this.jourbadge = jourbadge;
    }

    private Calendrier calendrier;
    private ArrayList<Planning> plannings;
    private ArrayList<Tache> tachesNonProgrammées;
    private int nb_encouragement=0;
    private int nb_badgesGOOD=0;
    private int nb_badgesVeryGOOD=0;
    private int nb_badgesExcelent=0;

    public int getNb_badgesGOOD() {
        return nb_badgesGOOD;
    }

    public void setNb_badgesGOOD(int nb_badgesGOOD) {
        this.nb_badgesGOOD = nb_badgesGOOD;
    }

    public int getNb_badgesVeryGOOD() {
        return nb_badgesVeryGOOD;
    }

    public void setNb_badgesVeryGOOD(int nb_badgesVeryGOOD) {
        this.nb_badgesVeryGOOD = nb_badgesVeryGOOD;
    }

    public int getNb_badgesExcelent() {
        return nb_badgesExcelent;
    }

    public void setNb_badgesExcelent(int nb_badgesExcelent) {
        this.nb_badgesExcelent = nb_badgesExcelent;
    }

    public int getNb_encouragement() {
        return nb_encouragement;
    }
    private int nbTacheJour=0;

    public int getNbTacheJour() {
        return nbTacheJour;
    }

    public void setNbTacheJour(int nbTacheJour) {
        this.nbTacheJour = nbTacheJour;
    }

    public void setNb_encouragement(int nb_encouragement) {
        this.nb_encouragement = nb_encouragement;
    }

    //private ArrayList<Badge> badges;
    private int nb_taches_parJour=3;

    public int getNb_taches_parJour() {
        return nb_taches_parJour;
    }

    public void setNb_taches_parJour(int nb_taches_parJour) {
        this.nb_taches_parJour = nb_taches_parJour;
    }
    //constructeur

    public User(String pseudo) {
        this.pseudo = pseudo;
        this.calendrier=new Calendrier();
        plannings=new ArrayList<>();
        tachesNonProgrammées=new ArrayList<>();

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    //methodes


    public Calendrier getCalendrier() {
        return calendrier;
    }
    public void ajouterPlanning(Periode periode){
        Planning planning =new Planning(periode);
        plannings.add(planning);

    }

    public ArrayList<Planning> getPlannings() {
        return plannings;
    }

    public ArrayList<Tache> getTachesNonProgrammées() {
        return tachesNonProgrammées;
    }

    public Planning getPlanningActuel(){//madertch her exceptions!!!!
        Planning foundObject=null;
        for (Planning obj : plannings) {
            if (LocalDate.now().isBefore(obj.getPeriode().getDate_fin()) && (LocalDate.now().isAfter(obj.getPeriode().getDate_debut() )||LocalDate.now().equals(obj.getPeriode().getDate_debut()  ))) {
                foundObject = obj;
                break;
            }
        }
        return foundObject;
    }
    public void ajoutertachenonprog(Tache tache){

        tachesNonProgrammées.add(tache);

    }



}
