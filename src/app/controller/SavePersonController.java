package app.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import app.classes.Escola;
import app.classes.student;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SavePersonController {
    private String lastId;
    

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

    public String readData() throws IOException { 
        String count = "";
        lastId = "0";
        String file = "src/app/controller/resources/csvFile/student.csv";
        List<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }try{
            count = content.get(content.size()-1)[0];
        }catch(Exception e){
            count = "0";
            
        }
        
        return count;
       
    }

    public void writeCsv(int id,String user_name, String user_email, String phone_student, String code_student,LocalDate ddn,String morada)
            throws IOException {
        List<List<String>> rows = Arrays.asList(Arrays.asList(String.valueOf(id),user_name, user_email, phone_student, code_student,ddn.toString(),morada));

        FileWriter csvWriter = new FileWriter("src/app/controller/resources/csvFile/student.csv", true);


        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");   
        }

        csvWriter.flush();
        csvWriter.close();
    }

    @FXML
    private TextField user_name_student_input, email_student_input, phone_student_input, student_code_input,
            morada_student_input;

    @FXML
    private DatePicker ddn;

    @FXML
    private ComboBox<TextField> curso;



   
    public void saveStudent() throws IOException {
        

        String user_student = user_name_student_input.getText();
        String email_student = email_student_input.getText();
        String phone_student = phone_student_input.getText();
        String code_student = student_code_input.getText();
        String morada_student = morada_student_input.getText();
        LocalDate ddn_student = (ddn.getValue());

        //Escola curso_student = Escola.valueOf(curso.getPromptText());
        Escola curso_student = Escola.LEIT;

        lastId = readData();
        
        student student = new student(user_student, email_student, phone_student, morada_student, curso_student, code_student, ddn_student);
        student.setId(Integer.valueOf(lastId)+1);
        
        writeCsv(student.getId(),student.getName(), student.getEmail(), student.getPhone(), student.getCode(),student.getDdn(),student.getMorada());

    }

}
