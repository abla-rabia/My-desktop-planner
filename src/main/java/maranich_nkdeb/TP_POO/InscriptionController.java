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

public class InscriptionController {
    @FXML
    private TextField textFieldPseudo;
    @FXML
    public void onInscriptionButtonClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pseudo.fxml"));
        String pseudo = textFieldPseudo.getText();
        User utilisateur=new User(pseudo);
        // Accéder à l'instance de VotreApplication et effectuer les opérations nécessaires sur les utilisateurs
        App.addUser(utilisateur);
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

        // ...
    }
}

