package app.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import app.classes.Cargo;
import app.classes.Escola;
import app.classes.Funcionario;
import app.classes.student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SavePersonController implements Initializable {
    private String lastStudetnID;
    private String lastEmployeeID;

    public void readCsv() throws IOException {
        try (Scanner scanner = new Scanner(new File("src/app/controller/resources/csvFile/student.csv"))) {

            // Set the delimiter used in file
            scanner.useDelimiter(",");

            // Get all tokens and store them in some data structure
            // I am just printing them
            while (scanner.hasNext()) {
                System.out.print(scanner.next() + " ");
            }

            // Do not forget to close the scanner
            scanner.close();
        }
    }

    public String readData(String csv) throws IOException {
        String count = "0";
        
        String file = "src/app/controller/resources/csvFile/"+csv+".csv";
        List<String[]> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {

            count = content.get(content.size() - 1)[0];
        } catch (Exception e) {
            count = "0";
            e.printStackTrace();
        } finally {
            if (count.equals(""))
                count = "0";

        }

        return count;

    }

    @FXML
    private TextField user_name_student_input, email_student_input, phone_student_input, student_code_input,
            morada_student_input;

    @FXML
    private TextField user_name_input;

    @FXML
    private TextField user_email_input;

    @FXML
    private TextField user_phone_input;

    @FXML
    private TextField user_code_input;

    @FXML
    private TextField user_address_input;

    @FXML
    private DatePicker user_ddn_input;

    @FXML
    private DatePicker ddn;

    @FXML
    private ComboBox curso;
    @FXML
    private ComboBox cargo;

    String[] cursoList = { "LEIT", "LEE", "LCB", "LMM", "LEC", "LEM" };
    String[] cargoList = {"PROFESSOR","FAXINEIRO","GUARDA"};

    public void saveStudent() throws IOException {

        String user_student = user_name_student_input.getText();
        String email_student = email_student_input.getText();
        String phone_student = phone_student_input.getText();
        String code_student = student_code_input.getText();
        String morada_student = morada_student_input.getText();
        LocalDate ddn_student = (ddn.getValue());

        Escola curso_student = Escola.valueOf(curso.getValue().toString());
        lastStudetnID = "0";
        lastStudetnID = readData("student");

        student student = new student(user_student, email_student, phone_student, morada_student, curso_student,
                ddn_student, code_student);
        int id = Integer.valueOf(lastStudetnID);
        student.setId(id + 1);

        student.saveStudent();
    }

    public void saveEmployee() throws IOException {

        String user_employee = user_name_input.getText();
        String email_employee = user_email_input.getText();
        String phone_employee = user_phone_input.getText();
        String code_employee = user_code_input.getText();
        String morada_employee = user_address_input.getText();
        LocalDate ddn_employee = (user_ddn_input.getValue());

         Cargo cargo_employee = Cargo.valueOf(cargo.getValue().toString());
        lastEmployeeID = "0";
        lastEmployeeID = readData("employee");

        Funcionario employee = new Funcionario(user_employee, email_employee, phone_employee, morada_employee,
                code_employee, cargo_employee, ddn_employee, 1000);
        int id = Integer.valueOf(lastEmployeeID);
        employee.setId(id + 1);

        employee.saveEmployee();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] filename = location.toString().split("/");

        if (filename[filename.length - 1].toString().equals("school_card_student.fxml")) {

            curso.setValue("SELECIONE");
            curso.getItems().addAll(cursoList);
        }
        if (filename[filename.length - 1].toString().equals("employee_card.fxml")) {

            cargo.setValue("SELECIONE");
            cargo.getItems().addAll(cargoList);
        }

    }

}
