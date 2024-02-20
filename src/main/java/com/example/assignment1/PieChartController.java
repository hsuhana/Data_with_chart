package com.example.assignment1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PieChartController {
    //toggle to different charts
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToBarChart (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("barChart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTable (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("table.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    DatabaseConnector dbConnector = new DatabaseConnector();

    public ObservableList<Country> loadData(){
        Connection connection = dbConnector.connect();
        ObservableList<Country> country = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM top_10_most_visited_countries");

            while (resultSet.next()){
                country.add(new Country(resultSet.getInt("ranks"), resultSet.getString("country_name"), resultSet.getInt("visitor_number")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return country;
    }
}
