package model;
 
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

 /**
  * 
  * A filter class for authorization for xhtml files in the /secured/ folder
  */
public class LoginFilter implements Filter {
 
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
             Object o = ((HttpServletRequest)request).getSession().getAttribute("username"); //Retrieve the attribute stored in the session
             String name = (String) o;                                                      
            if (name == null) {

                String contextPath = ((HttpServletRequest) request).getContextPath();
                ((HttpServletResponse) response).sendRedirect(contextPath + "/index.xhtml"); //Redirect path
                return;
            }
        
         
        chain.doFilter(request, response);
             
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
    @Override
    public void destroy() {
        // Nothing to do here!
    }  
     
}