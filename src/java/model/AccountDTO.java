package Model;


/**
 * 
 * A read-only interface for accounts
 */
public interface AccountDTO {
        
    String getUsername();
    String getPassword();
    String getRole();
}