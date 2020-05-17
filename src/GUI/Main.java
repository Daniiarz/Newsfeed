package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Main mInstance;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Person.Refresh();
        Parent root = FXMLLoader.load(getClass().getResource("views/LoginView.fxml"));
        primaryStage.setTitle("Newsfeed");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static Main getInstance() {
        return mInstance;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
