package app.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Funcionario extends Pessoa{
    private Cargo cargo;
    private int saldo;

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public Funcionario(String name, String email, String phone, String morada, String code, Cargo cargo, LocalDate ddn,int saldo) {
        super(name, email, phone, morada, code, ddn);
        this.cargo = cargo;
        this.saldo = saldo;

        
    }


    public void saveEmployee() throws IOException {

        List<List<String>> rows = Arrays.asList(Arrays.asList(String.valueOf(this.getId()), this.getName(),
                this.getEmail(), this.getPhone(), this.getCode(), String.valueOf(this.getCargo()), String.valueOf(this.getDdn()),
                this.getMorada(), String.valueOf(this.getSaldo())));

        FileWriter csvWriter = new FileWriter("src/app/controller/resources/csvFile/employee.csv", true);

        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    
}
