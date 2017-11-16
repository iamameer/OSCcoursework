package OSC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Main extends Application {

    //draggable window setting
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        //ui.fxml loading
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        //getting css file for the fxml file
        root.getStylesheets().add("/OSC/style.css");

        primaryStage.setTitle("Disk Scheduling Algorithm");
        primaryStage.setScene(new Scene(root, 849, 416));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/OSC/icon.png"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        //draggable window//
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //accessed from inner class, needs to be declared final
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        //end of draggable window //

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
