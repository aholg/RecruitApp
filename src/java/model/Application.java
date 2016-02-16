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
public class Application implements ApplicationDTO, Serializable{
    CompetenceProfileDTO profile;
    String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int personID;
   public Application(){
       
   }
    public Application(int personID,CompetenceProfileDTO profile,String description){
       this.personID=personID;
       this.profile=profile;
       this.description=description;
   }
   @Override
   public String getDescription(){
       return description;
   }

    @Override
    public CompetenceProfileDTO getProfile() {
        return profile;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if (this.personID != other.personID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + personID + " ]";
    }
   
}
