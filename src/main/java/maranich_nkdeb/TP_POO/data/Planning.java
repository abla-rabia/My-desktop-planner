package maranich_nkdeb.TP_POO.data;

import maranich_nkdeb.TP_POO.AccueilController;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Planning implements Serializable {
    private ArrayList<Tache> taches;
    private ArrayList<Projet> projets;
    private ArrayList<Badges> badges;
    private int nb_encouragement;
    private Periode periode;
    private static int nb=0;
    private String name;
    public ArrayList<Integer> statsBadges(){
        System.out.println("Statistique sur le nombre et type de Badges gagnes dans une planning : ");
        long nbBadgetotal=badges.stream().count();
        int nbBadgesGood=0;
        int nbBadgesExcellent=0;
        int nbBadgesVeryGood=0;
        for (Badges b:badges){
            if (b==Badges.GOOD)
                nbBadgesGood++;
            else if (b==Badges.EXCELLENT) {
                nbBadgesExcellent++;
            } else if (b==Badges.VERY_GOOD) {
                nbBadgesVeryGood++;
            }
        }
        ArrayList<Integer> stats = new ArrayList<>();
        stats.add(nbBadgesGood);
        stats.add(nbBadgesVeryGood);
        stats.add(nbBadgesExcellent);
        return stats;
    }
    public double rendementjournalier(){
        ArrayList<Tache> total=getTaches(LocalDate.now());
        int nbCompl=0;
        for (Tache t : total){
            if(t.getEtat()==Etat.COMPLETED)
                nbCompl++;
    }
        double rendement = (double)(nbCompl)/(double)AccueilController.getUser().getNb_taches_parJour();
        return rendement;
    }
    public int nbfoisencouragement(){
        return AccueilController.getUser().getNb_encouragement();
    }
    public ArrayList<Integer> etatDeRealisationdeTachesJournee(){
        ArrayList<Tache> total=getTaches(LocalDate.now());
        System.out.println("Statistique sur l'etat de realisation de taches dans la journee : ");
        int nbNotRealized=0;
        int nbCompleted=0;
        int nbCancelled=0;
        int nbIN_PROGRESS=0;
        int nbDelayed=0;

        for (Tache t : total){
            if(t.getEtat()==Etat.CANCELLED)
                nbCancelled++;
            else if (t.getEtat()==Etat.COMPLETED) {
                nbCompleted++;
            }
            else if (t.getEtat()==Etat.DELAYED)
                nbDelayed++;
            else if (t.getEtat()==Etat.IN_PROGRESS) {
                nbIN_PROGRESS++;
            }
            else
                nbNotRealized++;
        }
        ArrayList<Integer> stats = new ArrayList<>();
        stats.add(nbCompleted);
        stats.add(nbCancelled);
        stats.add(nbIN_PROGRESS);
        stats.add(nbDelayed);
        stats.add(nbNotRealized);
        return stats;
    }

    public Planning(Periode periode) {
        this.periode = periode;
        taches=new ArrayList<>();
        projets=new ArrayList<>();
        badges=new ArrayList<>();
        nb_encouragement=0;
        nb++;
        name="Planning"+nb;

    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }
    public ArrayList<Tache> getTaches(LocalDate jour) {
        ArrayList<Tache> tches=new ArrayList<>();
        for (Tache j : taches){
            if (j.getCreneau().getJour().equals(jour)){

                tches.add(j);
            }
            System.out.println(j.getCreneau().getJour());
        }
        return tches;

    }

    public ArrayList<Projet> getProjets() {
        return projets;
    }
    public ArrayList<Badges> getBadges() {
        return badges;
    }

    public int getNb_encouragement() {
        return nb_encouragement;
    }

    public Periode getPeriode() {
        return periode;
    }

    public static int getNb() {
        return nb;
    }

    public String getName() {
        return name;
    }

    public Creneau plannifier(Tache tache,Planning planning) {
        Creneau cr=null;
        Jour jour=null;
        boolean found=false;
        for ( Jour i : periode.getJoursPeriode()){
            System.out.println(i.getDate());
            if (i.getDate().isBefore(tache.getDate_lim() )|| (i.getDate().equals(tache.getDate_lim()) )){
                for (Creneau c : i.getCreneaux()){
                    if (c.getStatut()==Statut.LIBRE && tache.getDurée()<=c.getDuree() && !c.getBloqué()){
                        cr=c;
                        jour=i;
                        found=true;
                        break;
                    }
                }
            }
            if (found){
                break;
            }
        }
        Creneau cr2;//pour l'ajouter
        if (cr!=null ){
            if ((cr.getDuree()-tache.getDurée())>planning.getPeriode().getDuree_crn_min()){
                cr2=cr.seDécomposer(tache);
                jour.ajouterCreneau(cr2.getHeure_debut(),cr2.getHeure_fin());
            }
        }
        return (cr);
    }

    public ArrayList<Creneau> plannifierdecomp(Tache tache, Planning planning) {
        ArrayList<Creneau> listedesCreneaux=new ArrayList<>();
        Boolean arret=false;
        Creneau last = null;
        Jour jour=null;
        long d=0;
        for (Jour j : planning.getPeriode().getJoursPeriode()){
            if (j.getDate().isBefore(tache.getDate_lim()) ){
                for (Creneau c : j.getCreneaux()){
                    if (c.getStatut()==Statut.LIBRE){
                        d+=c.getDuree();
                        listedesCreneaux.add(c);
                        if (d>=tache.getDurée()){
                            jour=j;
                            last=c;
                            arret=true;
                            break;
                        }
                    }
                }
            }
        }
        if (!arret){
            System.out.println("on n'a pas pu plannifier votre tâche pour le moment , elle a ete sauvegarde dans la listes des taches non programmees .");
            AccueilController.getUser().ajoutertachenonprog(tache);
            tache.setEtat(Etat.UNSCHEDULED);
        }
        return listedesCreneaux;
    }

}
