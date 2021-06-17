//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package LinZanPart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class JavaFXApplication extends Application {
//    public static String simulationType;
//    public static String filePath;

    public JavaFXApplication() {
    }

    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("/FXML.fxml");
        System.out.println(location);
        Parent root = (Parent) FXMLLoader.load(location);
        Image image = new Image("/icons/badge.png");



        Group group = new Group();
        group.getChildren().add(root);
        Scene scene = new Scene(group);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Always on time delivery");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
