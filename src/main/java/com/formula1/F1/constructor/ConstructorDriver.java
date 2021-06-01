package com.formula1.F1.constructor;

public class ConstructorDriver {
    private String driverId;
    private String number;
    private String code;
    private String url;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String nationality;

    public void setId(String driverId) {
        this.driverId = driverId;
    }

    public void setNumber(String number) {
        if(number == null)
            this.number = " ";
        this.number = number;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUrl(String wiki) {
        this.url = wiki;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getId() {
        return driverId;
    }

    public String getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }
}
