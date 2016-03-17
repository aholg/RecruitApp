package view;

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

/**
 * This class is responsible for handling user registration and login. The class
 * is conversation scoped and stays alive for the duration of of the servlet
 * requests.
 *
 */
@Named("AcctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    @EJB
    private AccHandler accHandler;
    @Size(min = 1)
    private String registerUsername;
    @Size(min = 1)
    private String registerPassword;
    @Size(min = 1)
    private String loginUsername;
    @Size(min = 1)
    private String loginPassword;
    private boolean loggedIn = false;
    @Inject
    private Conversation conversation;
    private FacesContext context;
    private HttpServletRequest request;
    private HttpSession session;

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
     * Called by a jsf request to set the values of the entrered input. Called
     * during the Update Model Values Phase.
     *
     * @param registerUsername String containing the username to be registered.
     */
    public void setRegisterUsername(String registerUsername) {
        this.registerUsername = registerUsername;
    }
    
    /**
     * Method to get saved registered username. Used by jsf requests during the render response phase.
     * @return  A string containing the username used for registration.
     */
    public String getRegisterUsername() {
        return registerUsername;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called
     * during the Update Model Values Phase.
     *
     * @param loginUsername String username to be used for login.
     */
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    /**
     * Method to get saved username used for login. Used by jsf requests during the render response phase.
     * @return  A string used for retrieving the username used for login.
     */
    public String getLoginUsername() {
        return loginUsername;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called
     * during the Update Model Values Phase.
     *
     * @param registerPassword String containing password to be used for
     * registration.
     */
    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    /**
     *  Method to get saved password used for registration. Used by jsf requests during the render response phase.
     * @return  A string containing password used for registration. 
     */
    public String getRegisterPassword() {
        return registerPassword;
    }

    /**
     * Called by a jsf request to set the values of the entrered input. Called
     * during the Update Model Values Phase.
     *
     * @param loginPassword String containing password to be used for login.
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    /**
     * Method to get saved password used for login. Used by jsf requests during the render response phase.
     * @return  A string containing the password used for login.
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    private String jsf22Bugfix() {
        return "";
    }

    /**
     * Logs in the user with the username and password supplied by the user from
     * the web view. Form based login is used and is handled through the http
     * servlet request login function. The JDBC realm configured in the
     * glassfish console tells the servlet in which database table the login
     * information can be retrieved.
     *
     * @return A string containing which type of user that was successfully
     * logged in, or fail if login failed.
     */
    public String login() {
        startConversation();
        try {
            request.login(this.loginUsername, this.loginPassword);
            session.setAttribute("username", loginUsername);
        } catch (ServletException | NullPointerException e) {
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
     * The registerUser() handles the logic for creating a new account. The
     * method takes information entered by user and calls the createAccount
     * method in the controller(AccHandler) which creates an account for the
     * user by persisting the information in the database.
     *
     * @return empty string because of jsf bug.
     */
    public String registerUser() {
        startConversation();

        if (!(this.getRegisterUsername().length() > 0 && this.getRegisterPassword().length() > 0)) {
            handleException(new Exception("The usernamne or the password needs to be atleast one character long"));
            return jsf22Bugfix();
        }
        try {

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
        try {
            session.invalidate();
            request.logout();

        } catch (ServletException e) {
            handleException(e);
        }
        stopConversation();

        return "logout";
    }

    /**
     * The sha256(string base) method takes a string as parameter hashes it
     * after the sha256 algorithm.
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
