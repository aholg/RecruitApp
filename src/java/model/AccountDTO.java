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
public interface AccountDTO {
    
    int getPersonID();
    String getName();
    String getSSN();
    String getEmail();
    String getPassword();
    String getUsername();
    String getRole();
   
    void setName();
    void setSSN();
    void setEmail();
    void setPassword();
    void setUsername();
    void setRole();
}
