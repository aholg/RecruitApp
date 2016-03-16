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
import javax.validation.constraints.*;
import model.AccException;

@Named("AcctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    @EJB
    private AccHandler accHandler;
    private Exception accountException;
    @Size(min = 1)
    private String registerUsername;
    @Size(min = 1)
    private String registerPassword;
    @Size(min = 1)
    private String loginUsername;
    @Size(min = 1)
    private String loginPassword;
    private Exception transactionFailure;
    private boolean loggedIn = false;
    @Inject
    private Conversation conversation;
    private Exception loginFailure;
    private String loginResult;
    private FacesContext context;
    private HttpServletRequest request;
    private HttpSession session;

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
        //FacesContext context = FacesContext.getCurrentInstance();
        //HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.loginUsername, this.loginPassword);

            session.setAttribute("username", loginUsername);
        } catch (ServletException | NullPointerException e) {
            loginResult = "Fail";

            //context.addMessage(null, new FacesMessage("Login failed." + e.getMessage()));
            handleException(e);
            return "fail";
        }
        stopConversation();
        if (accHandler.checkRole(loginUsername) == true) {
            return "successRecruiter";
            

        } else {
            return "successApplicant";
        }
        
    }

    /**
     * The registerUser() handles the logic for creating a new account.
     * The method takes information entered by user and calls the createAccount method
     * in the controller(AccHandler) which creates an account for the user by persisting the
     * information in the database.
     *
     * @return empty string because of jsf bug.
     */
    public String registerUser() {
        startConversation();
        // check if the entered username and password is less than 1 character then throw an exception 
        if (!(this.getRegisterUsername().length() > 0 && this.getRegisterPassword().length() > 0)) {   //Check if the name and the password has a size bigger then 0
            handleException(new Exception("The usernamne or the password needs to be atleast one character long"));
            return jsf22Bugfix();
        }
        try {
            // call createAccount in the accHandler to create an account.
            accHandler.createAccount(this.getRegisterUsername(), sha256(this.getRegisterPassword()));
        } catch (NullPointerException | AccException ex) {
            handleException(ex);
            return jsf22Bugfix();
        }

        //clear the fields
        registerUsername = "";
        registerPassword = "";
        return jsf22Bugfix();
    }

    /**
     * The logoutUser() kills the user session
     *
     */
    public String logoutUser() {
        startConversation();
        //HttpSession session = SessionData.getSession();
        //context.release();
        try {
            session.invalidate();
            request.logout();
            
            
        } catch (ServletException e) {
            handleException(e);
        }
        stopConversation();
//session.invalidate();  //Invalidating the session will finally logout the user
        return "logout";
    }

    public Exception getException() {
        return loginFailure;
    }

    /**
     * The sha256(string base) method takes a string as parameter hashes
     * it after the sha256 algorithm.
     *
     * @param base the string that is to be hashed.
     * @return the hashed string.
     */
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
