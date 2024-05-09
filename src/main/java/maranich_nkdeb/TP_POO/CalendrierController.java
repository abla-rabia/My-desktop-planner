package maranich_nkdeb.TP_POO;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maranich_nkdeb.TP_POO.data.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendrierController implements Initializable {
    private Calendrier calendrier;
    private User user=AccueilController.getUser();


    @FXML
    public  void newperOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newPeriode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void listOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listPeriode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void crnHorsPeriode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crnhorsperiode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    DatePicker datedeb;
    @FXML
    DatePicker datefin;
    @FXML
    TextField duree;
    @FXML
    public  void ajouterPeriode(ActionEvent event) throws DatedebutException {
        LocalDate date_deb = datedeb.getValue();

        LocalDate date_fin = datefin.getValue();
        if (date_deb.isBefore(LocalDate.now())) {
            throw new DatedebutException("La date de début ne peut pas être antérieure à la date d'aujourd'hui!!");
        }
        if (!(duree.getText().isEmpty())){
            String durée = duree.getText();
            try {
                int number = Integer.parseInt(durée);
                calendrier.ajouterPeriode(date_deb,date_fin,number);
            } catch (NumberFormatException e) {
                // La chaîne de caractères ne peut pas être convertie en entier, gérer cette situation
            }}
        else{
            calendrier.ajouterPeriode(date_deb,date_fin);
            System.out.println("hello ablus");
        }
        System.out.println(calendrier.getPeriodes());
    }
    @FXML
    private DatePicker call=new DatePicker();
    @FXML
    private TableView<Tache> tabTches =new TableView<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user != null) {
            calendrier = user.getCalendrier();
        }
        call.setValue(LocalDate.now());
        call.setOnAction(event -> {
            LocalDate selectedDate = call.getValue();
            actualiserListeTaches(selectedDate);
        });
        TableColumn<Tache, String> name = new TableColumn<>("Tâches");
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Tache, String> etat = new TableColumn<>("Etat");
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tabTches.getColumns().addAll(name, etat);
    }
    private void actualiserListeTaches(LocalDate jour) {

        ArrayList<Tache> taches ;
        if (AccueilController.getUser().getPlanningActuel()!=null) {
            taches = AccueilController.getUser().getPlanningActuel().getTaches(jour);
            tabTches.setItems(FXCollections.observableArrayList(taches));
            System.out.println(taches.toString());
        }
    }
}
