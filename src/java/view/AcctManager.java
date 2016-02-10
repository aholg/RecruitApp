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
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
public class AcctManager {
    
    private AccHandler accountHandler;
    private String registerUsername;
    private String registerPassword;
    private String loginUsername;
    private String loginPassword;
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
    
    public String login(){ // ska returnera nästa sida 
        return "";
    }
    
    public String createPerson(){
        return "";
    }
    
    public void setRegisterUsername(String registerUsername){
        
    }
    
    public String getRegisterUsername(){
        return registerUsername;
    }
    
     public void setLoginUsername(String loginUsername){
        
    }
    
    public String getLoginUsername(){
        return loginUsername;
    }
    
    public void setRegisterPassword(String registerPassword){
        
    }
    
    public String getRegisterPassword(){
        return registerPassword;
    }
    
    public void setLoginPassword(String LoginPassword){
        
    }
    
    public String getLoginPassword(){
        return loginPassword;
    }
    
    
    
    
    
    
    
}
