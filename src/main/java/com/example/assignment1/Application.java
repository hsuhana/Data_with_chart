package com.example.assignment1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("barChart.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }

    @Override
    public void start(Stage stage){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
            Scene scene = new Scene(root);

            //import css
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Image icon = new Image("https://cdn-icons-png.freepik.com/512/826/826070.png?ga=GA1.1.1852331970.1708719654&"); //It must be inside src folder
            //Image icon = new Image(getClass().getResource("/travel.png").toExternalForm());
            //Image icon = new Image(getClass().getResourceAsStream("/travel.jpg"));


            stage.getIcons().add(icon);
            stage.setTitle("The Top 10 Most Visited Countries in 2022");
            //stage.setWidth(420);
            //stage.setHeight(420);
            //stage.setResizable(false);
            //stage.setX(50);
            //stage.setY(50);
            //stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        //launch method is a static method that belong to the Application class
        Application.launch(args);
    }
}