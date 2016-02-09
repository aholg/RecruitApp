/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Anton
 */
@Entity
public class Application implements ApplicationDTO, Serializable{
    CompetenceProfileDTO profile;
    String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int personID;
   Application(int personID,CompetenceProfileDTO profile,String description){
       this.personID=personID;
       this.profile=profile;
       this.description=description;
   }
   @Override
   public String getDescription(){
       return description;
   }

    @Override
    public CompetenceProfileDTO getProfile() {
        return profile;
    }
    
   
}
