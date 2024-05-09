package maranich_nkdeb.TP_POO;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import maranich_nkdeb.TP_POO.data.User;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable{
    @FXML
    private Label pseudo = new Label(user != null ? "Bienvenue " + user.getPseudo() + "!" : "");;

    private static User user;
    @FXML
    ImageView planner;
    Image pll=new Image(getClass().getResourceAsStream("planner.png"));


    public void setUser(User user) {
        this.user = user;
        pseudo.setText("Bienvenue " + user.getPseudo() + "!" );
    }
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        planner.setImage(pll);
        if(user!=null){
            pseudo.setText("Bienvenue " + user.getPseudo() + "!" );}
    }

    public static User getUser() {
        return user;
    }
}
