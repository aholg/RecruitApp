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
public class CompetenceProfile implements CompetenceProfileDTO, Serializable {
    private static final long serialVersionUID = 3L;
    int experience;
    List<Competence> competences;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int personID;
    

    public CompetenceProfile(int personID, int experience) {
        this.personID = personID;
        this.experience = experience;
        competences = new ArrayList();
    }

    @Override
    public List<Competence> getCompetences() {
        return competences;
    }

    @Override
    public int getPersonID() {
        return personID;
    }

    @Override
    public int getYearsOfExperience() {
        return experience;
    }

    @Override
    public void addCompetence(Competence competence) {
        competences.add(competence);
    }

 

}
