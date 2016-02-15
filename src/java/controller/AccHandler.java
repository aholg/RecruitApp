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
import model.Person;

/**
 *
 * @author Andreas
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class AccHandler {
    @PersistenceContext(unitName = "RecruitAppPU")
    private EntityManager em;
    
    public boolean checkRole(String username){
        Person person=em.find(Person.class, username);
       
        return person.getRole().equals("Recruiter");
    }
    public boolean checkUserAvailability(String username){
        return em.find(Person.class, username)==null;
    }
}
