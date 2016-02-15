/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


/**
 *
 * @author angie
 */
import controller.AccHandler;
import model.AccException;
import Session.SessionData;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("AcctManager")
@ConversationScoped
public class AcctManager implements Serializable{
    
    @EJB
    private AccHandler accHandler;
    private Exception AccException;
    @Inject
    private Conversation conversation;
    private AccHandler accountHandler;
    private String registerUsername;
    private String registerPassword;
    private String loginUsername;
    private String loginPassword;
    private Exception transactionFailure;
    private boolean loggedIn = false;
    
    public boolean isUserIsLoggedIn() {
        if(SessionData.getSession() == null){ //Check first if there even exists a session
            return false;
        }
        Object session = SessionData.getSession().getAttribute("username"); // Check if there exists a username
        String name = (String) session;
        return name != null;
    }

   
    
    
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
    
    public String login(){ 
        return "";
    }
    
    public String createPerson(){
        return "";
    }
    
    public void setRegisterUsername(String registerUsername){
        this.registerUsername = registerUsername;
    }
    
    public String getRegisterUsername(){
        return registerUsername;
    }
    
     public void setLoginUsername(String loginUsername){
        this.loginUsername = loginUsername;
    }
    
    public String getLoginUsername(){
        return loginUsername;
    }
    
    public void setRegisterPassword(String registerPassword){
        this.registerPassword = registerPassword;
    }
    
    public String getRegisterPassword(){
        return registerPassword;
    }
    
    public void setLoginPassword(String LoginPassword){
        this.loginPassword = loginPassword;
    }
    
    public String getLoginPassword(){
        return loginPassword;
    }
    
 

    private String jsf22Bugfix() {
        return "";
    }
    
     /**
    * The method handles the login logic.
    * the method calls and passes entered username and password to
    * loginAccount(username,password) method in the controller.
    * 
    * if the loginAccount method returns false then this method throws an exception,
    * otherwise the method sets up a http session. The session variable contains
    * user information such as user's "username"
    * 
    * @return empty string, to handle jsf bug 
    */
    public String loginUser(){
       startConversation();
        
       //check if the login process went good otherwise throw an exception
       try {
            if(AccHandler.loginAccount(this.getLoginUsername(), this.getLoginPassword()) == false){
                handleException(new Exception("Please make sure Username and Password are correct"));
                return jsf22Bugfix();
            }
        } catch (AccException ex) {
            handleException(ex);
            return jsf22Bugfix();
        }
        
        // set up a http session and set username as an attribute
        HttpSession session = SessionData.getSession();
        session.setAttribute("username", getLoginUsername());

        //reset the jsf fields 
        loginUsername="";
        loginPassword="";
       return jsf22Bugfix();
    }
    
    
    /**
    * The registerUser() handles the logic for creating a new account. 
    * The method takes the entered user information and calls the createAccount method
 in the controller(AccHandler.java) which creates an account for the user by saving
 the information in the database.
 
 After registering process is done successfully the user gets logged in automatically.
    * 
    * @return empty string, to handle jsf bug 
    */
    public String registerUser(){
        startConversation();
        // check if the entered username and password is less than 1 character then throw an exception 
        if(!(this.getRegisterUsername().length() > 0 && this.getRegisterPassword().length() > 0)){   //Check if the name and the password has a size bigger then 0
            handleException(new Exception("The usernamne or the password needs to be atleast one character long"));
            return jsf22Bugfix();
        }
        try{
            // call createAccount in the accountHandler to create an account.
            AccHandler.createAccount(this.getRegisterUsername(),this.getRegisterPassword());
        }
        catch(AccException ex){
            handleException(ex);
            return jsf22Bugfix();
        }
        
        // set up an session for the new user
        HttpSession session = SessionData.getSession();
        session.setAttribute("username", getRegisterUsername()); //Set session attribute

        //clear the fields
        registerUsername ="";
        registerPassword ="";
        return jsf22Bugfix();
    }
    
    /**
    * The logoutUser() kills the user session 
    * 
    * @return empty string, to handle jsf bug 
    */
    public String logoutUser(){
       startConversation();
      // HttpSession session = SessionData.getSession();
       //session.invalidate();  //Invalidating the session will finally logout the user
       return jsf22Bugfix();
    }
    
    
    
    
    
    
    
    
}
