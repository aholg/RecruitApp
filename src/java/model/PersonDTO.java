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
public interface PersonDTO {
    
    int getPersonID();
    String getName();
    String getSSN();
    String getEmail();
    String getPassword();
    String getUsername();
    String getRole();
   
   /* void setName(String name);
    void setSSN(String ssn);
    void setEmail(String email);
    void setPassword(String password);
    void setUsername(String username);
    void setRole(String role);*/
}
