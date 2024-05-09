package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.App;

import java.io.IOException;

public class SideBarController {

    public  void navigateToAccueil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public  void navigateToCalendrier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendrier.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void navigateToStatistiques(ActionEvent event) throws IOException {
        if (AccueilController.getUser().getPlanningActuel()!=null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats0.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
            System.out.println("Le planning actuel n'existe pas , veuillez creer un .");
    }
    public  void seDéconnecter(ActionEvent event) throws IOException {
        App.sauvegarderUtilisateurs();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("welcomePage.fxml"));
        Parent root = loader2.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
    public  void navigateToPlanning(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Planning.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public  void navigateToParametre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("parametre.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}

