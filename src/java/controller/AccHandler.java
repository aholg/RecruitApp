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
import model.Groups;
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
       
        Account account = new Account(username, password);
        Groups grp = new Groups(username);
        //grp.setAccount(account);
        //person.setRole("Applicant");
        //person.setRole("Recruiter");
        //account.setRole("Recruiter");
        em.persist(account);
        em.persist(grp);
      

    }
    
    
    public boolean checkRole(String username){
        Groups grp=em.find(Groups.class, username);
       
        return grp.getRole().equalsIgnoreCase("Applicant");
    }
    
    public boolean checkUserAvailability(String username){
        return em.find(Person.class, username)==null;
    }
    
}
