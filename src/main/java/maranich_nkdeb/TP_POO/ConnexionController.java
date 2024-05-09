package maranich_nkdeb.TP_POO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.App;
import maranich_nkdeb.TP_POO.data.User;

import java.io.IOException;

public class ConnexionController {
    @FXML
    private TextField textFieldPseudo;

    public void onConnexionButtonClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("authentification.fxml"));
        String pseudo = textFieldPseudo.getText();
        if (App.authentifierUtilisateur(pseudo)){
            User utilisateur=App.getMap_users().get(pseudo);
        try {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = loader2.load();

            // Accédez au contrôleur de la page d'accueil si nécessaire
            AccueilController accueilController = loader2.getController();
            accueilController.setUser(utilisateur);
            // Affichez la vue de la page d'accueil sur la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        else
            System.out.println("Utilisateur non trouvé , vérifiez que votre pseudo est correcte ! ");
}

    public void onInscriptionbutton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pseudo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
