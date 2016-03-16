/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Anton
 */
@Entity
public class CompetenceProfile implements CompetenceProfileDTO, Serializable {

    private static final long serialVersionUID = 3L;
    int experience;
    /*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "competenceProfile")
    @JoinColumn(name="COMPETENCEID",referencedColumnName="ID")
    List<Competence> competences;*/
    @OneToOne(cascade = CascadeType.MERGE)
    Person person;
    @OneToOne(cascade = CascadeType.REFRESH)
    Competence competence;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    public CompetenceProfile() {

    }

    public CompetenceProfile(int experience, Competence competence,Person person) {
        //this.id =id;
        this.experience = experience;
        this.competence = competence;
        this.person=person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public Competence getCompetence() {
        return competence;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void setCompetence(Competence competences) {
        this.competence = competence;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceProfile)) {
            return false;
        }
        CompetenceProfile other = (CompetenceProfile) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + id + " ]";
    }
}
