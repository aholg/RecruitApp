/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Anton
 */
public interface ApplicationDTO {
    
    CompetenceProfileDTO getProfile();
    String getDescription();
    void setProfile(CompetenceProfile profile);
    void setDescription(String description);
    void setApplicationId(int id);
    void setPerson(Person person);
    Person getPerson();
    int getApplicationId();
}
