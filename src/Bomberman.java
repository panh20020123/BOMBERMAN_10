package src;

import java.util.Objects;

import com.sun.javafx.application.LauncherImpl;

import src.gui.scenes.loading.loadingController;
import src.gui.scenes.menu.menuController;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Bomberman extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader
                .load(Objects.requireNonNull(getClass().getResource("./gui/scenes/menu/menu.fxml")));
        Scene scene = new Scene(root);
        // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
