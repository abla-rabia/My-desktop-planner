package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.LocalDate;

public class Tache_simple extends Tache implements Serializable {
    private boolean periodique;
    private int periode;

    public Tache_simple(String nom, long durée, Priorité priorité, LocalDate date_lim, Categorie categorie) {
        super(nom, durée, priorité, date_lim, categorie);
        this.periodique = false;

    }
    public Tache_simple(String nom, long durée, Priorité priorité, LocalDate date_lim, Categorie categorie,boolean bloquerLeCreneau) {
        super(nom, durée, priorité,bloquerLeCreneau, date_lim, categorie);
        this.periodique = false;
    }
}
