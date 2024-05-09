package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.Tache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class periodicite implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private Tache tache;

    public void setTache(Tache tache) {
        this.tache = tache;
    }
    @FXML
    private TextField per;

    public void valider(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newCreneauTache2.fxml"));
        Parent root = loader.load();
        NewCrtch2Controller newCrtchController=loader.getController();
        newCrtchController.setTache(tache);
        int dur=Integer.parseInt(per.getText());
        newCrtchController.setFreq(dur);
        newCrtchController.setTache(tache);
        Scene scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
