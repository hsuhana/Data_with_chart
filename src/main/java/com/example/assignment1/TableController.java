package com.example.assignment1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableController implements Initializable {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @FXML
    private TableView<Country> table;

    @FXML
    private TableColumn<Country, String> table_country;

    @FXML
    private TableColumn<Country, Integer> table_rank;

    @FXML
    private TableColumn<Country, Integer> table_visitors;

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

    public void switchToPieChart (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("pieChart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void switchToTable (ActionEvent event) throws IOException{
//        root = FXMLLoader.load(getClass().getResource("table.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

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


    @Override
    public void initialize(URL url, ResourceBundle rb){
        table_rank.setCellValueFactory(new PropertyValueFactory<>("table_rank"));
        table_country.setCellValueFactory(new PropertyValueFactory<>("table_country"));
        table_visitors.setCellValueFactory(new PropertyValueFactory<>("table_visitors"));

        table.setItems(loadData());
        table.getColumns().addAll(table_rank, table_country, table_visitors);
    }
}






