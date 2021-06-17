/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.net.URL;

import javafx.stage.Stage;

/**
 *
 * @author Linzan Liu
 */
public class JavaFXApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       URL location = getClass().getResource("icons/FXML.fxml");
        Parent root = (Parent)FXMLLoader.load(location);
//        Image image = new Image("/resources/resources/icons/badge.png");
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Always on time delivery");
//        primaryStage.getIcons().add(image);
        primaryStage.show();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
