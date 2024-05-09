package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Projet implements Serializable {
    private String nom;
    private ArrayList<Tache> s_taches;
    private String description;

    public Projet(String nom, String description) {
        this.nom = nom;
        this.description = description;
        s_taches=new ArrayList<>();
    }

    public ArrayList<Tache> getS_taches() {
        return s_taches;
    }

    public void setS_taches(ArrayList<Tache> s_taches) {
        this.s_taches = s_taches;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
