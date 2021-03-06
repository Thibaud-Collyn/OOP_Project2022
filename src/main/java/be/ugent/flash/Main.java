package be.ugent.flash;

import be.ugent.flash.beheersinterface.StartScreenController;
import be.ugent.flash.viewer.ViewerManager;
import be.ugent.flash.viewer.viewer_fxml.AbstractController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

/**
 * Hoofdprogramma.
 */
public class Main extends Application {

    // Dit is een basis waarmee je je hoofdprogramma kan opbouwen. Maak gerust aanpassingen
    // die beter passen bij je eigen ontwerp.
    //
    // Je moet hoe dan ook de methode start nog aanpassen.

    private String dbName; // null wanneer we de beheersinterface willen starten

    /**
     * Toont een foutbericht en stopt het programma
     */
    private void error(String bericht) {
        // Uit cursusbroncode (ArgsMain)
        System.err.println("*ERROR* -- " + bericht);
        Platform.exit(); // Opgelet! Platform.exit() is ook nodig als 'launch' niet wordt opgeroepen!
    }

    @Override
    public void init() {
        List<String> argList = getParameters().getRaw();
        int size = argList.size();
        if (size == 0) {
            this.dbName = null;
        } else if (size == 1) {
            this.dbName = argList.get(0);
        } else {
            error("maximum twee opdrachtlijnparameters toegelaten");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (dbName == null) {
            // start de beheersinterface
            URL url = StartScreenController.class.getResource("/StartScreen.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Beheersinterface");
            stage.show();
            stage.setResizable(false);
        } else {
            // start de viewer
            URL url = AbstractController.class.getResource("/MCS.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            ViewerManager viewerManager = new ViewerManager("jdbc:sqlite:"+dbName, stage);
            stage.setTitle("Viewer");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            stage.setResizable(false);
            viewerManager.start();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
