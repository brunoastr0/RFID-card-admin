package app.controller;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ArrayList<String> student;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private PieChart pie_chart;

    @FXML
    private LineChart lineChart;

    XYChart.Series series = new XYChart.Series();

    

    @FXML
    Button button_create, button_stats, button_settings, sign_up, progress;

    @FXML
    private ToggleButton card_employeeButton, card_studentButton;

    public SavePersonController savePersonController;

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
                e.printStackTrace();
            }
        } else if (selectedBtn == card_studentButton) {
            try {
                create_student_window();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void viewPerson() throws IOException {
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("../fxml/screen_pos_user.fxml"));

        Parent rootStats = loaderStats.load();
        rootStats.getStylesheets().add(getClass().getResource("../public/css/stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) sign_up.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void stats() throws IOException {
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));

        Parent rootStats = loaderStats.load();
        rootStats.getStylesheets().add(getClass().getResource("../public/css/stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) button_create.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void create_window() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/create_screen.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("../public/css/stylesheet_school_card.css").toExternalForm());
        Stage window = (Stage) button_create.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    public void create_student_window() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchor.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/school_card_student.fxml"));
        try{
            dialog.getDialogPane().setContent(loader.load());

        }catch(IOException e){
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            savePersonController = loader.getController();
            savePersonController.saveStudent();
        }
    



    }

  

    public void employee_screen() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchor.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/employee_card.fxml"));
        try{
            dialog.getDialogPane().setContent(loader.load());

        }catch(IOException e){
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();

        /*if(result.isPresent() && result.get() == ButtonType.OK){
            savePersonController = loader.getController();
            savePersonController.saveStudent();
        }*/
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


                card_employeeButton.getStyleClass().add("buttonSelected");

                
               
                
            }
        
    }

}
