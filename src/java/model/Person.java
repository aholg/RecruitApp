/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Anton
 */
public class Person implements PersonDTO{
    private int personID;
    private String name;
    private String ssn;
    private String email;
    private String password;
    private String username;
    private String role;
    
    Person(int personID,String name,String ssn,
            String email,String password,String username,String role){
        this.personID=personID;
        this.name=name;
        this.ssn=ssn;
        this.email=email;
        this.password=password;
        this.username=username;
        this.role=role;
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
