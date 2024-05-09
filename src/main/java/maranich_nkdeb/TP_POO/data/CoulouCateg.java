package maranich_nkdeb.TP_POO.data;

public class CoulouCateg {
    Categorie categorie;
    Couleur couleur;

    public CoulouCateg(Categorie categorie, Couleur couleur) {
        this.categorie = categorie;
        this.couleur = couleur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
}
