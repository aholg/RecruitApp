/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ApplyHandler;
import java.util.Date;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;



/**
 *
 * @author angie
 */
public class ApplicationManager {
    
    private ApplyHandler applyHandler;
    private String descriptionText;
    
    private String firstName;
    private String lastName;
    private Date startDate;
    private Date endDate;
    private String experience;
    private int yearsOfExperience;
    private String email;
    private String ssn;
    private String competence;
    
    
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    public void setDescriptionText(String descriptionText){
        this.descriptionText = descriptionText;
    }
    
     public String getDescriptionText(){
        return descriptionText;
    }
     
    public void saveApplication(){
        
    }
    
    public void setExperience(String experience){
        this.experience = experience;
    }
    
    public String getExperience(){
        return experience;
    }
    
    public void setStartDate(Date startDate){
        
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
     public void setEndDate(Date endDate){
        this.endDate=endDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public int getyearsOfExperience(){
        return yearsOfExperience;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
     public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
      public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setSSN(String ssn){
        this.ssn = ssn;
    }
    
    public String getSSN(){
        return ssn;
    }
    
    public void setCompetence(String competence){
        this.competence = competence;
    }
    
    public String getCompetence(){
        return competence;
    }
}
