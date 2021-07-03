package app.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SavePersonController {

    public void readCsv() throws IOException {
        try (Scanner scanner = new Scanner(new File("src/controller/student.csv"))) {

             
        //Set the delimiter used in file
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext()) 
        {
            System.out.print(scanner.next() + " ");
        }
         
        //Do not forget to close the scanner  
        scanner.close();
        }
    }

       

    

    public void writeCsv(String user_name, String user_email, String phone_student, String code_student) throws IOException {
        List<List<String>> rows = Arrays.asList(Arrays.asList(user_name,user_email,phone_student,code_student));



        FileWriter csvWriter = new FileWriter("src/controller/student.csv");
        csvWriter.append("Name");
        csvWriter.append(",");
        csvWriter.append("email");
        csvWriter.append(",");
        csvWriter.append("phone");
        csvWriter.append(",");
        csvWriter.append("code");
        csvWriter.append("\n");

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


    public void saveStudent() throws IOException {
        String user_student = user_name_student_input.getText();
        String email_student = email_student_input.getText();
        String phone_student = phone_student_input.getText();
        String code_student = student_code_input.getText();
        //writeCsv(user_student,email_student,phone_student,code_student);
        readCsv();

    }

}
