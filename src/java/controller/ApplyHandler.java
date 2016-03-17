/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Competence;
import model.CompetenceProfile;
import model.Person;

/**
 *
 * This class is used for creating an application for a user.
 */
//all other transactions gets suspended
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ApplyHandler {

    @PersistenceContext(unitName = "RecruitAppPU")
    private EntityManager em;
    
    /**
     * Creates a new application to be stored in the database.
     * @param person    Person object to be associated with the application.
     * @param yearsOfExperience An int containing the years of experience of the person.
     * @param competences   An array that contains indexes of competences.
     */
    public void saveApplication(Person person, int yearsOfExperience, String[] competences) {
      
        em.merge(person);
        if(competences[0].equals("1")){
           Competence competence= em.find(Competence.class, 2);
           CompetenceProfile profile = new CompetenceProfile(yearsOfExperience, competence,person);
           em.persist(profile);
       
        }
        if(competences[1].equals("2")){
           Competence competence= em.find(Competence.class, 2);
           CompetenceProfile profile = new CompetenceProfile(yearsOfExperience, competence,person);
           em.persist(profile);
       
        }
        if(competences[0].equals("2")){
           Competence competence= em.find(Competence.class, 2);
           CompetenceProfile profile = new CompetenceProfile(yearsOfExperience, competence,person);
           em.persist(profile);
      
        }

    }
}
