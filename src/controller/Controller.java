package controller;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ArrayList<String> student;

    @FXML
    private PieChart pie_chart;

    @FXML
    private LineChart lineChart;

    XYChart.Series series = new XYChart.Series();

    @FXML
    Button button_create, button_stats, button_settings, sign_up, progress;

    @FXML
    private ToggleButton card_employeeButton, card_studentButton;
    

    ToggleGroup groupToggle = new ToggleGroup();

    @FXML
    public void choiceBtn() {
        card_employeeButton.setToggleGroup(groupToggle);
        card_studentButton.setToggleGroup(groupToggle);

        Toggle selectedBtn = groupToggle.getSelectedToggle();

        if (selectedBtn == card_employeeButton) {
            try {
                employee_screen();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (selectedBtn == card_studentButton) {
            try {
                create_student_window();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void viewPerson() throws IOException {
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("../screen_pos_user.fxml"));

        Parent rootStats = loaderStats.load();
        rootStats.getStylesheets().add(getClass().getResource("../stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) sign_up.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void stats() throws IOException {
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("../school_card1.fxml"));

        Parent rootStats = loaderStats.load();
        rootStats.getStylesheets().add(getClass().getResource("../stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) button_create.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void create_window() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../create_screen.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("../stylesheet_school_card.css").toExternalForm());
        Stage window = (Stage) button_create.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    public void create_student_window() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../school_card_student.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("../stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) progress.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    public void employee_screen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../employee_card.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("../stylesheet_school_card.css").toExternalForm());
        Stage window = (Stage) progress.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] filename = location.toString().split("/");

        if (filename[filename.length - 1].toString().equals("school_card1.fxml")) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Active users", 50), new PieChart.Data("unactive user", 50),
                    new PieChart.Data("unactive user", 50));

            pie_chart.setData(pieChartData);
            pie_chart.setTitle("User Activity");

            series.setName(("data values"));

            series.getData().add(new XYChart.Data(1, 23));
            series.getData().add(new XYChart.Data(2, 14));
            series.getData().add(new XYChart.Data(3, 15));
            series.getData().add(new XYChart.Data(4, 24));
            series.getData().add(new XYChart.Data(5, 34));
            series.getData().add(new XYChart.Data(6, 36));
            series.getData().add(new XYChart.Data(7, 22));
            series.getData().add(new XYChart.Data(8, 45));
            series.getData().add(new XYChart.Data(9, 43));
            series.getData().add(new XYChart.Data(10, 17));
            series.getData().add(new XYChart.Data(11, 29));
            series.getData().add(new XYChart.Data(12, 25));

            // lineChart.getData().add(series);
            lineChart.setTitle("Chart line");}

            if (filename[filename.length - 1].toString().equals("create_screen.fxml")) {

                card_employeeButton.setToggleGroup(groupToggle);
                card_studentButton.setToggleGroup(groupToggle);

                Toggle selectedBtn = groupToggle.getSelectedToggle();

                card_studentButton.setId("card_studentButton");
                card_employeeButton.setId("card_employeeButton");
                progress.setId("progress");

                card_studentButton.getStyleClass().add("buttonSelected");
               
                
            }
        
    }

}
