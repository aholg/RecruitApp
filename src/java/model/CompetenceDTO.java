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
public interface CompetenceDTO {
   // void setCompetenceProfile(CompetenceProfile profile);
   // CompetenceProfile getCompetenceProfile();
    int getCompetenceId();
    String getName();
    void setCompetenceId(int id);
    void setName(String name);
}
