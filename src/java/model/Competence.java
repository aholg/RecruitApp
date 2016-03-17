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
import javax.validation.constraints.Size;

/**
 *
 * This entity class is responsible for representing the competence table in
 * the application. Holds related columns to the table.
 */
@Entity
public class Competence implements CompetenceDTO, Serializable {
    private static final long serialVersionUID = 3L;
    @Size(min=1)
    String name;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int competenceId;
    
    /**
     *  Creates a new competence object
     */
    public Competence(){
        
    }
    
    /**
     * Creates a new competence object with a given competence name.
     * @param name
     */
    public Competence(String name){
        this.name=name;
    }

    /**
     * Get method for the name of this competence.
     * @return  A string containing the name of the competence.
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Get method for the id of this competence.
     * @return  An int containing the id of this entity.
     */
    @Override
    public int getCompetenceId(){
        return competenceId;
    }
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) competenceId;
        return hash;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  A set method for setting the id of this entity.
     * @param competenceId  An int containing the id to be set.
     */
    @Override
    public void setCompetenceId(int competenceId) {
        this.competenceId = competenceId;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if (this.competenceId != other.competenceId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + competenceId + " ]";
    }
}
