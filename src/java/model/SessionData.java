
package Session;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 /**
  * 
  * A helper class for retrieving the session and the request
  */
public class SessionData {
    /**
     * Method to retrieve the current session
     * @return The  current HttpSession, if invalidated or non-existent, it will instead return null
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
    
    /**
     * Retrives the current request
     * @return The current HttpServletRequest, if non-existent it will instead return null
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
}