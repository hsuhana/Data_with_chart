package com.example.assignment1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
//
//    @FXML
//    private CategoryAxis barChart_country;
//
//    @FXML
//    private NumberAxis barChart_visitors;

    //toggle to different charts
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPieChart (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pieChart.fxml"));
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

    //DatabaseConnector dbConnector = new DatabaseConnector();

    public ObservableList<Country> loadData(){
        //Connection connection = dbConnector.connect();
        ObservableList<Country> countries = FXCollections.observableArrayList();
        try {
            Connection connection = DatabaseConnector.connect();
            //PreparedStatement statement = connection.prepareStatement("SELECT * FROM top_10_most_visited_countries");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM top_10_most_visited_countries");

            while (resultSet.next()){
                countries.add(new Country(resultSet.getString("country_name"), resultSet.getInt("visitor_number")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

//      ObservableList<Country> countries = loadData();
//      XYChart.Series<String, Integer> series = new XYChart.Series<>();
//      for (Country country : countries) {
//          series.getData().add(new XYChart.Data<>(country.getTable_country(), country.getTable_visitors()));
//      }
//      barChart.getData().addAll(series);
        ObservableList<Country> countries = loadData();
        populateBarChart(countries);
    }

    private void populateBarChart(ObservableList<Country> countries){

    }
}
