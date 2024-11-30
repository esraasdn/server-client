
import java.io.*;
import java.net.*;

public class client {

    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public static void main(String[] args) {
        try {
            // Créer un socket pour se connecter au serveur
            Socket socket = new Socket("localhost", 19000);//port 19000

            // Flux d'entrée pour lire les messages du serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Flux de sortie pour envoyer les messages au serveur
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Flux d'entrée pour lire les entrées de l'utilisateur
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;

            // Lire et envoyer les messages dans la même boucle
            while ((message = userInput.readLine()) != null) {
                out.println(message);  // Envoie le message au serveur

                // Lire la réponse du serveur
                String serverMessage = in.readLine();  // Lire le message du serveur
                System.out.println("Message du serveur : " + serverMessage);  // Afficher le message du serveur

                // Si le message est "bye", fermer la connexion
                if (message.equals("bye")) {
                    break;  // Quitter la boucle et fermer la connexion
                }
            }

            socket.close();  // Fermer la connexion au serveur

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
