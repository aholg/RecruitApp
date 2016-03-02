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
import model.AccException;
import model.Account;
import model.AccountDTO;
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
    
    public void createAccount(String username, String password) throws AccException {
        AccountDTO tempacc = em.find(Account.class, username);
        
        if (tempacc != null) {
            if (tempacc.getUsername().equalsIgnoreCase(username)) {
                throw new AccException("This username is already taken");
            }  
        }
        Person person = new Person();
        Account account = new Account(person, password, username);
        
        //person.setRole("Applicant");
        //account.setRole("Recruiter");
        account.setRole("Applicant");
        em.persist(account);
        em.persist(person);
    }
    
    public boolean checkRole(String username){
        Account acc=em.find(Account.class, username);
       
        return acc.getRole().equals("Recruiter");
    }
    public boolean checkUserAvailability(String username){
        return em.find(Person.class, username)==null;
    }
    
}