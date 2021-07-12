package app.classes;

import java.time.LocalDate;

public class Pessoa {
    private int id;
  
    private String name;
    private String email;
    private String morada;
    private String code;
    private LocalDate ddn;
    private String phone;

    public Pessoa( String name, String email, String phone,String morada, String code,
            LocalDate ddn) {
      
        this.name = name;
        this.email = email;
        this.morada = morada;
        this.ddn = ddn;
        this.phone = phone;
        this.code = code;
        
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDdn() {
        return ddn;
    }

    public void setDdn(LocalDate ddn) {
        this.ddn = ddn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
}
