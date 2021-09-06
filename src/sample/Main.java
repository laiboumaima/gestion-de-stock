package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

  @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("front/Main.fxml"));
        primaryStage.setTitle("");
       // primaryStage.initStyle(StageStyle.UNDECORATED);
     // Rectangle2D screenBounds = Screen.getPrimary().getBounds();
      Scene scene = new Scene(root, 1193, 742);
                  primaryStage.getIcons().add(new Image("sample/style/stocklogo.png"));
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args)   {
        launch(args);

       /* String jdbcUrl ="ldbc:mysql://localhost:3306/products";
        String username="root";
        String password = "0000";
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);*/


    }

}
