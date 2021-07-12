package app.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class student extends Pessoa {
    private int saldo;
    private Escola curso;

    public Escola getCurso() {
        return curso;
    }

    public void setCurso(Escola curso) {
        this.curso = curso;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public student(String name, String email, String morada, String code, Escola curso, LocalDate ddn, String phone) {
        super(name, email, morada, code, phone, ddn);
        this.setSaldo(1500);
        this.curso = curso;
    }

    public void saveStudent() throws IOException {

        List<List<String>> rows = Arrays.asList(Arrays.asList(String.valueOf(this.getId()), this.getName(),
                this.getEmail(), this.getPhone(), this.getCode(), String.valueOf(this.getCurso()), String.valueOf(this.getDdn()),
                this.getMorada(), String.valueOf(this.getSaldo())));

        FileWriter csvWriter = new FileWriter("src/app/controller/resources/csvFile/student.csv", true);

        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

}
