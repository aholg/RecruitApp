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
import java.io.Serializable;
import java.security.MessageDigest;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.AccException;

@Named("AcctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    @EJB
    private AccHandler accHandler;
    private Exception accountException;
    private AccHandler accountHandler;
    private String registerUsername;
    private String registerPassword;
    private String loginUsername;
    private String loginPassword;
    private Exception transactionFailure;
    private boolean loggedIn = false;
    @Inject
    private Conversation conversation;
    private Exception loginFailure;
    private String loginResult;

    
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

    public boolean getSuccess() {
        return accountException == null;
    }

    public void setRegisterUsername(String registerUsername) {
        this.registerUsername = registerUsername;
    }

    public String getRegisterUsername() {
        return registerUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    private String jsf22Bugfix() {
        return "";
    }

    public String login() {
        startConversation();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.loginUsername, this.loginPassword);
        } catch (ServletException e) {
            loginResult = "Fail";
            context.addMessage(null, new FacesMessage("Login failed." + e.getMessage() + loginUsername + loginPassword));
            return "fail";
        }
        if (accHandler.checkRole(loginUsername) == true) {
            return "successRecruiter";

        } else {
            return "successApplicant";
        }

    }



    /**
     * The registerUser() handles the logic for creating a new account. The
     * method takes the entered user information and calls the createAccount
     * method in the controller(AccHandler.java) which creates an account for
     * the user by saving the information in the database.
     *
     * After registering process is done successfully the user gets logged in
     * automatically.
     *
     * @return empty string, to handle jsf bug
     */
    public String registerUser() {
        startConversation();
        // check if the entered username and password is less than 1 character then throw an exception 
        if (!(this.getRegisterUsername().length() > 0 && this.getRegisterPassword().length() > 0)) {   //Check if the name and the password has a size bigger then 0
            handleException(new Exception("The usernamne or the password needs to be atleast one character long"));
            return jsf22Bugfix();
        }
        try {
            // call createAccount in the accountHandler to create an account.
            accHandler.createAccount(this.getRegisterUsername(), sha256(this.getRegisterPassword()));
        } catch (AccException ex) {
            handleException(ex);
            return jsf22Bugfix();
        }

        // set up an session for the new user
        // HttpSession session = SessionData.getSession();
        // session.setAttribute("username", getRegisterUsername()); //Set session attribute
        //clear the fields
        registerUsername = "";
        registerPassword = "";
        return jsf22Bugfix();
    }

    /**
     * The logoutUser() kills the user session
     *
     * @return empty string, to handle jsf bug
     */
    public String logoutUser() {
        startConversation();
      // HttpSession session = SessionData.getSession();
        //session.invalidate();  //Invalidating the session will finally logout the user
        return jsf22Bugfix();
    }

    public Exception getException() {
        return loginFailure;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}