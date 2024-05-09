package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.LocalDate;

abstract public class Tache implements Serializable,Comparable<Tache> {
    private String nom;
    private long durée;
    private Priorité priorité;
    private boolean bloquerLeCreneau;
    private LocalDate date_lim;
    private Categorie categorie;
    private Creneau creneau;
    private Etat etat;

    public Tache(String nom, long durée, Priorité priorité, LocalDate date_lim, Categorie categorie) {
        this.nom = nom;
        this.durée = durée;
        this.priorité = priorité;
        this.date_lim = date_lim;
        this.categorie = categorie;
        this.etat=Etat.NOTREALIZED;
    }

    public Tache(String nom, long durée, Priorité priorité, boolean bloquerLeCreneau, LocalDate date_lim, Categorie categorie) {
        this.nom = nom;
        this.durée = durée;
        this.priorité = priorité;
        this.bloquerLeCreneau = bloquerLeCreneau;
        this.date_lim = date_lim;
        this.categorie = categorie;
        this.etat=Etat.NOTREALIZED;
    }

    public boolean isBloquerLeCreneau() {
        return bloquerLeCreneau;
    }

    public void setBloquerLeCreneau(boolean bloquerLeCreneau) {
        this.bloquerLeCreneau = bloquerLeCreneau;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getDurée() {
        return durée;
    }

    public void setDurée(long durée) {
        this.durée = durée;
    }

    public Priorité getPriorité() {
        return priorité;
    }

    public void setPriorité(Priorité priorité) {
        this.priorité = priorité;
    }

    public LocalDate getDate_lim() {
        return date_lim;
    }

    public void setDate_lim(LocalDate date_lim) {
        this.date_lim = date_lim;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
    @Override
    public int compareTo(Tache tache) {

        return Integer.compare(this.priorité.ordinal(), tache.getPriorité().ordinal());
    }

}
