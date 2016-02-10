/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ApplyHandler;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;



/**
 *
 * @author angie
 */
public class ApplicationManager {
    
    private ApplyHandler applyHandler;
    private String descriptionText;
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
        
    }
    
     public String getDescriptionText(){
        return descriptionText;
    }
     
    public void saveApplication(){
        
    }
    
    
    
}
