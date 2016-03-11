/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Anton
 */
@Entity
public class Application implements ApplicationDTO, Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    CompetenceProfile profile;
    String description;
    @OneToOne(cascade = CascadeType.MERGE)
    Person person;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ApplicationId;

    public Application() {

    }

    public Application(Person person, CompetenceProfile profile, String description) {
        this.person = person;
        this.profile = profile;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public CompetenceProfileDTO getProfile() {
        return profile;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void setApplicationId(int ApplicationId) {
        this.ApplicationId = ApplicationId;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public int getApplicationId() {
        return ApplicationId;
    }

    @Override
    public void setProfile(CompetenceProfile profile) {
        this.profile = profile;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ApplicationId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if (this.ApplicationId != other.ApplicationId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + ApplicationId + " ]";
    }

}
