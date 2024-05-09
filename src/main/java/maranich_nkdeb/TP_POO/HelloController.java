package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.App;

import java.io.IOException;

public class HelloController {
    private App application;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    protected void commencer(ActionEvent event) throws IOException {
        App.chargerUtilisateurs();
        Parent root= FXMLLoader.load(getClass().getResource("startPage.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void seConnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pseudo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seConnecter2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}