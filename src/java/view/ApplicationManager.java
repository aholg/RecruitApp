/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AccHandler;
import controller.ApplyHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.AccException;
import model.Account;
import model.Competence;
import model.Person;

/**
 *
 * @author angie
 */
@Named("ApplicationManager")
@ConversationScoped
public class ApplicationManager implements Serializable {

    @EJB
    private ApplyHandler applyHandler;
    private String description;

    private String firstName;
    private String lastName;
    private String startDate;
    private String endDate;
    private String experience;
    private int yearsOfExperience;
    private String email;
    private String ssn;

    @EJB
    private AccHandler accHandler;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;
    private FacesContext context;
    private HttpServletRequest request;
    private HttpSession session;
    //private List<Competence> competenceList;

    private String[] competence = {"1", "2"};

    public String[] getCompetence() {
        return competence;
    }

    public void setCompetence(String[] competence) {
        this.competence = competence;
    }

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        context.addMessage(null, new FacesMessage(e.getMessage()));
        transactionFailure = e;
    }

    public void setDescription(String descriptionText) {
        this.description = descriptionText;
    }

    public String getDescription() {
        return description;
    }

    public void saveApplication() {
        startConversation();
        Object usernameObject = null;
        try {
            usernameObject = session.getAttribute("username");
        } catch (IllegalStateException e) {

            handleException(e);
        }
        if (usernameObject != null) {
            String username = usernameObject.toString();
            Account acc;
            try {
                acc = accHandler.getAcc(username);
                Person person = acc.getPerson();
                person.setEmail(email);
                person.setSSN(ssn);
                person.setName(firstName);
                handleException(new Exception(competence[0]));
                applyHandler.saveApplication(person, yearsOfExperience, competence, description);
            } catch (AccException ex) {
                handleException(ex);
            }

        }
        stopConversation();
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getyearsOfExperience() {
        return yearsOfExperience;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }

    /*public void setCompetence(String competence){
     if(competenceList==null){
     competenceList=new ArrayList();
     }
     competenceList.add(new Competence(competence));
     //this.competence = competence;
     */
}
