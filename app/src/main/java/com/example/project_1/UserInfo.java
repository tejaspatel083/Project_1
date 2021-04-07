package com.example.project_1;

public class UserInfo {

    private String name,email,birthdate,city,gender;

    public UserInfo() {
    }

    public UserInfo(String name, String email, String birthdate, String city, String gender) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.city = city;
        this.gender = gender;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
