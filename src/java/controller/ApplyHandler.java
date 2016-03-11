/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Application;
import model.Competence;
import model.CompetenceProfile;
import model.Person;


/**
 *
 * @author angie
 */
//all other transactions gets suspended
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ApplyHandler {
    @PersistenceContext(unitName = "RecruitAppPU")
    private EntityManager em;
    
    public void saveApplication(Person person,int yearsOfExperience,List<Competence> competenceList,String text){
       /* for(Competence c:competenceList){
            em.persist(c);
        }*/
        CompetenceProfile profile=new CompetenceProfile(yearsOfExperience,competenceList);
        Application app=new Application(person,profile,text);
       
        
        //em.persist(profile);
        
        em.persist(app);
    }
}
