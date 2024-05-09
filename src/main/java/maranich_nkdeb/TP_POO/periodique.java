package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.Tache;

import java.io.IOException;

public class periodique {
    private Tache tache;

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public void ouiPeriodique(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("periodicite.fxml"));
        Parent root = loader.load();
        periodicite periodicite=loader.getController();
        periodicite.setTache(tache);
        Scene scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void nonPeriodique(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newCreneauTache.fxml"));
        Parent root = loader.load();
        NewCrtchController newCrtchController=loader.getController();
        newCrtchController.setTache(tache);
        Scene scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
