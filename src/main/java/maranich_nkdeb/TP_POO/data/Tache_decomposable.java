package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Tache_decomposable extends Tache implements Décomposable, Serializable {
    private ArrayList<Tache> taches=null;


    public Tache_decomposable(String nom, long durée, Priorité priorité, LocalDate date_lim, Categorie categorie) {
        super(nom, durée, priorité, date_lim, categorie);
    }
    public Tache_decomposable(String nom, long durée, Priorité priorité, LocalDate date_lim, Categorie categorie,boolean bloquerLeCreneau) {
        super(nom, durée, priorité,bloquerLeCreneau, date_lim, categorie);
    }

    @Override
    public Creneau seDécomposer(Tache tache) {
        if (taches==null)
            taches=new ArrayList<Tache>();

        taches.add(tache);
        return null;
    }
}
