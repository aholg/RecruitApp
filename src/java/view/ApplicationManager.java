
package view;

import controller.AccHandler;
import controller.ApplyHandler;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import model.AccException;
import model.Account;
import model.Person;

/**

 This class handles user application for a job position. The class receives the data 
 entered from web pages through a jsf conversation which stays alive during the whole servlet request.
 The main purpose of the class is to receive the user input, collect it together into an application and 
 save it through calling the ApplyHandler in the controller
 */
@Named("ApplicationManager")
@ConversationScoped
public class ApplicationManager implements Serializable {

    @EJB
    private ApplyHandler applyHandler;
    private String description;

    @Size(min=1)
    private String firstName;
     @Size(min=1)
    private String lastName;
    private String startDate;
    private String endDate;
    private String experience;
    private int yearsOfExperience;
    @Pattern(regexp = "[a-z0-9]+@[a-z0-9]+\\.(com|net|se)")
    private String email;
    @Pattern(regexp = "\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])-\\d{4}")
    private String ssn;

    @EJB
    private AccHandler accHandler;
    @Inject
    private Conversation conversation;
    private FacesContext context;
    private HttpServletRequest request;
    private HttpSession session;

    private String[] competence = {"1", "2"};

   

    /**
     * Method to get saved competences. Used by jsf requests during the render response phase.
     * @return An array containing indexes of entered competences, 1 and 2.
     */
    
    public String[] getCompetence() {
        return competence;
    }

    /**
     *  Called by a jsf request to set the values of the entrered competence input from multiple checkbox values.
     * Called during the Update Model Values Phase
     * @param competence Array containing indexes of competences.
     */
    public void setCompetence(String[] competence) {
        this.competence = competence;
    }
    /*
     Checks if conversation is transient, if so it marks it as long running.
     Retrieves the facescontext, request and session of the current servlet request.
    */
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();
    }
    /*
    Stops the conversation by marking it transient if it was currently lng running.
    */
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    /*
    Handles exception. Prints out the received message to web view
    
    @Param Exception e the exception that was thrown.
    */
    private void handleException(Exception e) {
        stopConversation();
        context.addMessage(null, new FacesMessage(e.getMessage()));

    }

    /**
     *  Called by a jsf request to set the values of the entrered description input. Called during the 
     *  Update Model Values Phase.
     * @param descriptionText String of text to save.
     */
    public void setDescription(String descriptionText) {
        this.description = descriptionText;
    }
    
    /**
     * Method to get saved competences. Used by jsf requests during the render response phase.
     * @return A string containing the user description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Method that compiles all user input into an application by calling the applyHandler in the controller.
     *  Also controls that the user is logged in by checking the current session.
     */
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
                applyHandler.saveApplication(person, yearsOfExperience, competence);
            } catch (AccException ex) {
                handleException(ex);
            }

        }
        stopConversation();
    }

    /**
     *  Called by a jsf request to set the values of the entrered input. Called during the 
     *  Update Model Values Phase.
     * @param experience String to be set.
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Method to get saved experience. Used by jsf requests during the render response phase.
     * @return  A string containing the experience of the user.
     */
    public String getExperience() {
        return experience;
    }

    /**
     *  Called by a jsf request to set the values of the entrered  input. Called during the 
     *  Update Model Values Phase. Checks so that it conforms to form "YYYY-MM-DD".
     *  @param startDate String containing start date to be set. 
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Method to get saved start date. Used by jsf requests during the render response phase.
     * @return  String containing start date entered by user.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called during the 
     * Update Model Values Phase. Checks so that it conforms to form "YYYY-MM-DD".
     * @param endDate String containing end date to be set. 
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Method to get saved end date. Used by jsf requests during the render response phase.
     * @return  String containing end date entered by user.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called during the 
     * Update Model Values Phase.
     * @param yearsOfExperience int containing number of years of experience.
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Method to get saved years of experience. Used by jsf requests during the render response phase.
     * @return  An int containing the years of experience entered by the user.
     */
    public int getyearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called during the 
     * Update Model Values Phase.
     * @param firstName String containing first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method to get saved first name. Used by jsf requests during the render response phase.
     * @return  A string containing the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Called by a jsf request to set the values of the entrered input. Called during the 
     * Update Model Values Phase.
     * @param lastName String containing last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to get saved last name. Used by jsf requests during the render response phase.
     * @return  String containing the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Called by a jsf request to set the values of the entrered input. Called during the 
     * Update Model Values Phase.
     * @param email String containing user email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get saved email. Used by jsf requests during the render response phase.
     * @return  String containing the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called during the 
     * Update Model Values Phase. Checks so that it conforms to form "YYYY/MM/DD-NNNN"
     * @param ssn   String containing user social security name.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    /**
     *  Method to get saved social security number. Used by jsf requests during the render response phase.
     * @return  String containing the social security number of the user.
     */
    public String getSsn() {
        return ssn;
    }
}
