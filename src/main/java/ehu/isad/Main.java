package ehu.isad;

import ehu.isad.controllers.ui.TxanponakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent root;
    private Stage stage;
    private TxanponakKud txanponakKud;



    @Override
    public void start(Stage primaryStage) throws Exception {
        loadUI(primaryStage);
        stageSetup();
        stage.show();
    }

    private void loadUI(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Txanponak.fxml"));
        root = (Parent) loader.load();
        txanponakKud = loader.getController();
    }

    private void stageSetup(){
        stage.setScene(new Scene(root));
        stage.setTitle("Txanponak");
        stage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}