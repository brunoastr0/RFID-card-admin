package app.classes;

import java.time.LocalDate;

public class student {
    private int id;
  
    private String name;
    private String email;
    private String morada;
    private Escola curso;
    private String Code;
    private LocalDate ddn;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public student(String name, String email, String phone, String morada, Escola curso, String code, LocalDate ddn) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.morada = morada;
        this.curso = curso;
        Code = code;
        this.ddn = ddn;
       

    }

    public student(String name, String email, Escola curso, String code) {
        this.name = name;
        this.email = email;
        this.curso = curso;
        Code = code;
        

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Escola getCurso() {
        return curso;
    }

    public void setCurso(Escola curso) {
        this.curso = curso;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public LocalDate getDdn() {
        return ddn;
    }

    public void setDdn(LocalDate ddn) {
        this.ddn = ddn;
    }

}
