/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

/**
 *
 * This entity class is responsible for holding column data related to the account.
 */
@Entity
public class Account implements AccountDTO, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    
    @Size(min=1)
    private String password;
    @Size(min=1)
    private String groupname;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    Person person;
    
    /**
     * Creates a new account object.
     */
    public Account(){
        
    }
    
    /**
     * Creates a new account object with given constructor parameters.
     * @param person    Person object to be used by the object.
     * @param password  Password to be used by the object.
     * @param username  Username to be used by the object.
     */
    public Account(Person person, String password, String username) {
        this.person = person;
        this.password = password;
        this.groupname = "applicant";
        this.username = username;

    }

    /**
     * Get method for getting the password of the account.
     * @return  A string containing the password of the account.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Set method for setting the password to be used by the account.
     * @param password  A string containing the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Get method to get the username of the account.
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }
    
    /**
     * Set method to set the username of the account.
     * @param username  String containing the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get method for getting the role of the account, either Applicant or Recruiter.
     * @return A string containing the role.
     */
    @Override
    public String getRole() {
        return groupname;
    }
    
    /**
     * Set method for setting the role of the account.
     * @param role  String containing the role of the account.
     */
    public void setRole(String role) {
        this.groupname = role;
    }
    
    /**
     * Method for setting the person to be used by the account.
     * @param person    Person object to be set.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Get method to get the person.
     * @return  A person object.
     */
    public Person getPerson() {
        return person;
    }
    
@Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Account[ username=" + username + " ]";
    }
   
}
