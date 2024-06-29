package com.selaviu.library.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "FirstName should not be empty")
    @Size(min = 2, max = 30, message = "FirstName should be beetwen 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "FirstName should not be empty")
    @Size(min = 2, max = 30, message = "FirstName should be beetwen 2 and 30 characters")
    private String lastName;

    private int yearOfBirth;

    public Person(){}


    public Person(int id, String firstName, String lastName, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
