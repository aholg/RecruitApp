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
import javax.persistence.ManyToOne;

/**
 *
 * @author Andreas
 */
@Entity
public class Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String groupname;
    
    @ManyToOne
    private Account account;
    
    public Groups(String username){
        super();
        this.username = username;
        this.groupname = "Applicant";
    }

    public Groups(){
        super();
    }


    public String getUsername() {
        return username;
    }

    public String getRole() {
        return groupname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.groupname = role;
    }

    public Account getAccount(){
        return account;
    }
    
     public void setAccount(Account account){
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the username fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Groups[ id=" + username + " ]";
    }
    
}
