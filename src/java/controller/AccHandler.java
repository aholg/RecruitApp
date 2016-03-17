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
import model.Competence;
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
    
    /**
     * The method creates an account and adds it to the database if the username is not 
     * already taken.
     * 
     * A person with a specified role is also added to the database.
     *
     * @param username the username the username wants for the account.
     * @param password the password the user wants for the account.
     * @throws AccException thrown if username already is taken.
     * @throws NullPointerException thrown if username or password chosen is null.
     */
    public void createAccount(String username, String password) throws AccException,NullPointerException {
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
        
        Competence competence1=new Competence("Korvgrillning");
        Competence competence2=new Competence("Karuselldrift");
        em.persist(competence1);
        em.persist(competence2);
        //throw new NullPointerException("An error was encountered during registration");
    }
    
    /**
     * This method returns true if user is recruiter and false if user is Applicant.
     *
     * @param username the username of an registered user.
     * @return true or false depending on the role.
     */
    public boolean checkRole(String username){
        Account acc=em.find(Account.class, username);
       
        return acc.getRole().equals("Recruiter");
    }

    /**
     * Checks if a username is available in the database.
     * @param username  String containing username to be checked if available.
     * @return  True if available, otherwise false.
     */
    public boolean checkUserAvailability(String username){
        return em.find(Person.class, username)==null;
    }
    
    /**
     * Get method for retrieving an account from given username.
     * @param username  String containing the username.
     * @return      An account object that was associated with the given username.
     * @throws AccException     Throws account exception if no account was found.
     */
    public Account getAcc(String username) throws AccException{
        System.out.println(username);
        Account acc=em.find(Account.class, username);
        if(acc!=null){
            return acc;
        }else{
            throw new AccException("Account was not found");
        }
       
    
    }
}