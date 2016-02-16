/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Anton
 */
@Entity
class Competence implements CompetenceDTO, Serializable {
    private static final long serialVersionUID = 3L;
    String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int competenceID;
    
    public Competence(){
        
    }
   public Competence(String name){
        this.name=name;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public int getCompetenceID(){
        return competenceID;
    }
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) competenceID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if (this.competenceID != other.competenceID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + competenceID + " ]";
    }
}
