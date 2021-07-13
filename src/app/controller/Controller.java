package app.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ArrayList<String> student;

    @FXML
    private Pane mainPane, personPane, paneInScroll;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private ScrollPane mainScroll;

    @FXML
    private PieChart pie_chart;

    @FXML
    private LineChart<?, ?> lineChart;

    XYChart.Series series = new XYChart.Series();

    @FXML
    Button button_create, button_stats, button_settings, sign_up, progress, newCard, peopleButton, cardButton;

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
        rootStats.getStylesheets()
                .add(getClass().getResource("../public/css/stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) sign_up.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void peopleScreen() throws IOException {
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("../fxml/person.fxml"));

        Parent rootStats = loaderStats.load();
        rootStats.getStylesheets()
                .add(getClass().getResource("../public/css/stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) peopleButton.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void stats() throws IOException {
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));

        Parent rootStats = loaderStats.load();
        rootStats.getStylesheets()
                .add(getClass().getResource("../public/css/stylesheet_school_card.css").toExternalForm());

        Stage window = (Stage) cardButton.getScene().getWindow();

        window.setScene(new Scene(rootStats));

    }

    @FXML
    public void create_window() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/create_screen.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    public void create_student_window() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchor.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/school_card_student.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());

        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            savePersonController = loader.getController();
            savePersonController.saveStudent();
        }

    }

    public void employee_screen() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchor.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/employee_card.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());

        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            savePersonController = loader.getController();
            savePersonController.saveEmployee();
        }
    }

    public int countData(String csv) throws IOException {
        int count = 0;

        String file = "src/app/controller/resources/csvFile/" + csv + ".csv";
        List<String[]> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;

    }

    public void getallName(List<String[]> content, String csv) throws IOException {

        String file = "src/app/controller/resources/csvFile/" + csv + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void getName() {
        String nome = searchField.getText();
        System.out.println(nome);
        searchName(nome);
    }

    public void searchName(String nome) {
        List<String[]> content = new ArrayList();
        try {
            getallName(content, "employee");
            getallName(content, "student");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int contStudent = 0;
        int contEmployee = 0;
        int total = 0;
        try {
            contStudent = countData("student");
            contEmployee = countData("employee");
            total = contStudent + contEmployee;
        } catch (IOException e) {

            e.printStackTrace();
        }
        if (!nome.equals("")) {

            for (int i = 0; i < total; i++) {
                if (content.get(i)[1].equals(nome)) {

                    Button button = new Button();
                    button.getStyleClass().add("buttonOnHoverPerson");
                    button.getStyleClass().add("buttons");

                    button.setLayoutX(9);
                    button.setLayoutY(65 * i + 2);
                    button.setPrefSize(961, 48);
                    /* Name */
                    Label name = new Label();

                    name.setText(content.get(i)[1]);
                    name.setFont(new Font("Arial", 18));
                    name.setLayoutX(46);
                    name.setLayoutY(67 * i + 2);
                    name.setLabelFor(button);

                    /** ID */
                    Label id = new Label();

                    id.setText(content.get(i)[0]);
                    id.setFont(new Font("Arial", 18));
                    id.setLayoutX(300);
                    id.setLayoutY(67 * i + 2);
                    id.setLabelFor(button);

                    /** Role */
                    Label role = new Label();
                    role.setText(content.get(i)[5]);
                    role.setFont(new Font("Arial", 18));
                    role.setLayoutX(455);
                    role.setLayoutY(67 * i + 2);
                    role.setLabelFor(button);

                    /** Balance */
                    Label balance = new Label();
                    balance.setText(content.get(i)[8]);
                    balance.setFont(new Font("Arial", 18));
                    balance.setLayoutX(650);
                    balance.setLayoutY(67 * i + 2);
                    balance.setLabelFor(button);

                    /** location */
                    Label address = new Label();
                    address.setText(content.get(i)[7]);
                    address.setFont(new Font("Arial", 18));
                    address.setLayoutX(850);
                    address.setLayoutY(67 * i + 2);
                    address.setLabelFor(button);

                    button.setOnMouseEntered(e -> {
                        name.setStyle("-fx-text-fill:white;");
                        id.setStyle("-fx-text-fill:white;");
                        role.setStyle("-fx-text-fill:white;");
                        balance.setStyle("-fx-text-fill:white;");
                        address.setStyle("-fx-text-fill:white;");
                    });
                    button.setOnMouseExited(e -> {
                        name.setStyle("-fx-text-fill:black;");
                        id.setStyle("-fx-text-fill:black;");
                        role.setStyle("-fx-text-fill:black;");
                        balance.setStyle("-fx-text-fill:black;");
                        address.setStyle("-fx-text-fill:black;");
                    });

                    personPane.getChildren().addAll(button, name, id, role, balance, address);

                }
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] filename = location.toString().split("/");

        if (filename[filename.length - 1].toString().equals("main.fxml")) {

            int contStudent = 0;
            int contEmployee = 0;
            int total = 0;
            try {
                contStudent = countData("student");
                contEmployee = countData("employee");
                total = contStudent + contEmployee;
            } catch (IOException e) {

                e.printStackTrace();
            }
            Button[] buttonList = new Button[total];

            List<String[]> content = new ArrayList();
            try {
                getallName(content, "employee");
                getallName(content, "student");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < (total < 10 ? total : 9); i++) {
                buttonList[i] = new Button();
                buttonList[i].getStyleClass().add("buttonOnHoverPerson");
                buttonList[i].getStyleClass().add("buttons");

                buttonList[i].setLayoutX(9);
                buttonList[i].setLayoutY(65 * i + 2);
                buttonList[i].setPrefSize(961, 48);
                /* Name */
                Label name = new Label();

                name.setText(content.get(i)[1]);
                name.setFont(new Font("Arial", 18));
                name.setLayoutX(46);
                name.setLayoutY(67 * i + 2);
                name.setLabelFor(buttonList[i]);

                /** ID */
                Label id = new Label();

                id.setText(content.get(i)[0]);
                id.setFont(new Font("Arial", 18));
                id.setLayoutX(300);
                id.setLayoutY(67 * i + 2);
                id.setLabelFor(buttonList[i]);

                /** Role */
                Label role = new Label();
                role.setText(content.get(i)[5]);
                role.setFont(new Font("Arial", 18));
                role.setLayoutX(455);
                role.setLayoutY(67 * i + 2);
                role.setLabelFor(buttonList[i]);

                /** Balance */
                Label balance = new Label();
                balance.setText(content.get(i)[8]);
                balance.setFont(new Font("Arial", 18));
                balance.setLayoutX(650);
                balance.setLayoutY(67 * i + 2);
                balance.setLabelFor(buttonList[i]);

                /** location */
                Label address = new Label();
                address.setText(content.get(i)[7]);
                address.setFont(new Font("Arial", 18));
                address.setLayoutX(850);
                address.setLayoutY(67 * i + 2);
                address.setLabelFor(buttonList[i]);

                buttonList[i].setOnMouseEntered(e -> {
                    name.setStyle("-fx-text-fill:white;");
                    id.setStyle("-fx-text-fill:white;");
                    role.setStyle("-fx-text-fill:white;");
                    balance.setStyle("-fx-text-fill:white;");
                    address.setStyle("-fx-text-fill:white;");
                });
                buttonList[i].setOnMouseExited(e -> {
                    name.setStyle("-fx-text-fill:black;");
                    id.setStyle("-fx-text-fill:black;");
                    role.setStyle("-fx-text-fill:black;");
                    balance.setStyle("-fx-text-fill:black;");
                    address.setStyle("-fx-text-fill:black;");
                });

                personPane.getChildren().addAll(buttonList[i], name, id, role, balance, address);

            }}

            ObservableList<Object> pessoa = FXCollections.observableArrayList();
            if (filename[filename.length - 1].toString().equals("person.fxml")) {

                int contStudent = 0;
                int contEmployee = 0;
                int total = 0;
                try {
                    contStudent = countData("student");
                    contEmployee = countData("employee");
                    total = contStudent + contEmployee;
                } catch (IOException e) {
    
                    e.printStackTrace();
                }
                Button[] buttonList = new Button[total];
    
                List<String[]> content = new ArrayList();
                try {
                    getallName(content, "employee");
                    getallName(content, "student");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < total; i++) {
                    buttonList[i] = new Button();
                    buttonList[i].getStyleClass().add("buttonOnHoverPerson");
                    buttonList[i].getStyleClass().add("buttons");
    
                    buttonList[i].setLayoutX(9);
                    buttonList[i].setLayoutY(65 * i + 2);
                    buttonList[i].setPrefSize(961, 48);
                    
                    /* Name */
                    Label name = new Label();
    
                    name.setText(content.get(i)[1]);
                    name.setFont(new Font("Arial", 18));
                    name.setLayoutX(46);
                    name.setLayoutY(67 * i + 2);
                    name.setLabelFor(buttonList[i]);
                    pessoa.add(name);
                    /** ID */
                    Label id = new Label();
    
                    id.setText(content.get(i)[0]);
                    id.setFont(new Font("Arial", 18));
                    id.setLayoutX(300);
                    id.setLayoutY(67 * i + 2);
                    id.setLabelFor(buttonList[i]);
                    
                    /** Role */
                    Label role = new Label();
                    role.setText(content.get(i)[5]);
                    role.setFont(new Font("Arial", 18));
                    role.setLayoutX(455);
                    role.setLayoutY(67 * i + 2);
                    role.setLabelFor(buttonList[i]);
                    
    
                    /** Balance */
                    Label balance = new Label();
                    balance.setText(content.get(i)[8]);
                    balance.setFont(new Font("Arial", 18));
                    balance.setLayoutX(650);
                    balance.setLayoutY(67 * i + 2);
                    balance.setLabelFor(buttonList[i]);
                    
                    /** location */
                    Label address = new Label();
                    address.setText(content.get(i)[7]);
                    address.setFont(new Font("Arial", 18));
                    address.setLayoutX(850);
                    address.setLayoutY(67 * i + 2);
                    address.setLabelFor(buttonList[i]);
                    
        
                    buttonList[i].setOnMouseEntered(e -> {
                        name.setStyle("-fx-text-fill:white;");
                        id.setStyle("-fx-text-fill:white;");
                        role.setStyle("-fx-text-fill:white;");
                        balance.setStyle("-fx-text-fill:white;");
                        address.setStyle("-fx-text-fill:white;");
                    });
                    buttonList[i].setOnMouseExited(e -> {
                        name.setStyle("-fx-text-fill:black;");
                        id.setStyle("-fx-text-fill:black;");
                        role.setStyle("-fx-text-fill:black;");
                        balance.setStyle("-fx-text-fill:black;");
                        address.setStyle("-fx-text-fill:black;");
                    });
                    
                    
                    paneInScroll.getChildren().addAll(buttonList[i], name, id, role, balance, address);
                    mainScroll.setContent(paneInScroll);

    
                }}

            /*
             * ObservableList<PieChart.Data> pieChartData =
             * FXCollections.observableArrayList( new PieChart.Data("Active Students",
             * contStudent), new PieChart.Data("Active Employee", contEmployee));
             * 
             * pie_chart.setData(pieChartData); pie_chart.setTitle("User Activity");
             * pie_chart.setLabelsVisible(true); pie_chart.setLegendVisible(true);
             * pie_chart.setLabelLineLength(10); //pie_chart.setLegendSide(Side.LEFT);
             */

        

        if (filename[filename.length - 1].toString().equals("create_screen.fxml")) {

            card_employeeButton.setToggleGroup(groupToggle);
            card_studentButton.setToggleGroup(groupToggle);

            card_employeeButton.getStyleClass().add("buttonSelected");

        }

    }

}
