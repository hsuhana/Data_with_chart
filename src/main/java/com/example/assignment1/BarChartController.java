package com.example.assignment1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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

    public ObservableList<Country> loadData(){

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

        ObservableList<Country> countries = loadData();
        populateBarChart(countries);
    }

    private void populateBarChart(ObservableList<Country> countries){
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for(Country country : countries){
            series.getData().add(new XYChart.Data<>(country.getTable_country(), country.getTable_visitors()));
        }
        barChart.getData().addAll(series);

        String[] segmentColors = {
                "#457782",
                "#B5EBD7",
                "#968868",
                "#EBDBB5",
                "#B4E1EB",
                "#A4E3AE",
                "#EBCEB5",
                "#85A4AB",
                "#967E68",
                "#689685"
        };

        // Apply custom colors after the PieChart has been rendered
        Platform.runLater(() -> {
            for (int i = 0; i < series.getData().size(); i++) {
                XYChart.Data<String, Integer> data = series.getData().get(i);
                String color = segmentColors[i % segmentColors.length]; // Use modulus to loop through colors
                Node node = data.getNode();
                if (node != null) {
                    node.setStyle("-fx-bar-fill: " + color + ";");
                }
            }
        });
    }
}
