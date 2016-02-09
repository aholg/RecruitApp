/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Anton
 */
@Entity
public class Person implements PersonDTO, Serializable {

    private String name;
    private String ssn;
    private String email;
    private String password;
    private String username;
    private String role;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personID;

    Person(int personID, String name, String ssn,
            String email, String password, String username, String role) {

        this.name = name;
        this.ssn = ssn;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;

        this.personID = personID;
    }

    @Override
    public int getPersonID() {
        return personID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSSN() {
        return ssn;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getRole() {
        return role;
    }

}
