package maranich_nkdeb.TP_POO.data;

import java.io.*;
import java.util.HashMap;

public class App {
    static private HashMap <String, User> map_users=new HashMap<String,User>();
    public App() {
        map_users=new HashMap<String, User>();
    }
    static public void addUser(User utilisateur){
        map_users.put(utilisateur.getPseudo(),utilisateur);
    }

    public static HashMap<String, User> getMap_users() {
        return map_users;
    }
    public static boolean authentifierUtilisateur(String pseudo) {
        if(map_users.containsKey(pseudo))
            return true;
        return false; // L'authentification a échoué
    }
    public static void sauvegarderUtilisateurs() {
        try (FileOutputStream fos = new FileOutputStream("FichierUsers.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(map_users);
            System.out.println("Utilisateurs sauvegardés avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }
    public static void chargerUtilisateurs() {
        try (FileInputStream fis = new FileInputStream("FichierUsers.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            map_users = (HashMap<String, User>) ois.readObject();
            System.out.println("Utilisateurs chargés avec succès.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
    }
}
