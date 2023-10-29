package pl.davidduke.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "The name field cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the name must be from 2 to 100 characters!")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "The surname field cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the surname must be from 2 to 100 characters!")
    @Column(name = "surname")
    private String surName;

    @Min(value = 1900, message = "Year of birth must be greater than 1900!")
    @Column(name = "birthyear")
    private int birthYear;

    public Person() {
        super();
    }

    public Person(String name, String surName, int birthYear) {
        this.name = name;
        this.surName = surName;
        this.birthYear = birthYear;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }git

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}