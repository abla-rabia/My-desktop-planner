package maranich_nkdeb.TP_POO.data;

public enum Couleur {
    Rouge("#FF0000"),
    Vert("#00FF00"),
    Bleu("#0C0C9B"),
    Violet("#650981"),
    Jaune("#D2D611"),
    Cyan("#0ED0DE"),
    Marron("#473107"),
    Rose("#DB13AD"),
    Noir("#000000");

    private String codeHexadecimal;

    Couleur(String codeHexadecimal) {
        this.codeHexadecimal = codeHexadecimal;
    }

    public String getCodeHexadecimal() {
        return codeHexadecimal;
    }

}
