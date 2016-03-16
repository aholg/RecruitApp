/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 *
 * @author Anton
 */
@Entity
public class Competence implements CompetenceDTO, Serializable {
    private static final long serialVersionUID = 3L;
    @Size(min=1)
    String name;
   /* @JoinColumn(name = "ID")
    @ManyToOne(fetch=FetchType.LAZY)
    private CompetenceProfile competenceProfile;*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int competenceId;
    
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
    public int getCompetenceId(){
        return competenceId;
    }
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) competenceId;
        return hash;
    }
/*
    @Override
    public void setCompetenceProfile(CompetenceProfile competenceProfile) {
        this.competenceProfile = competenceProfile;
    }

    @Override
    public CompetenceProfile getCompetenceProfile() {
        return competenceProfile;
    }
*/
    @Override
    public void setName(String name) {
        this.name = name;
    }


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
