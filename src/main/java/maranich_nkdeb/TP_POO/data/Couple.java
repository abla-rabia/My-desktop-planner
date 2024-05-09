package maranich_nkdeb.TP_POO.data;

import java.io.Serializable;

public class Couple implements Serializable {
    Jour jour;
    Creneau creneau;

    public Couple(Jour jour, Creneau creneau) {
        this.jour = jour;
        this.creneau = creneau;
    }

    public Jour getJour() {
        return jour;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }

    @Override
    public String toString() {
        return creneau.toString();
    }
}
