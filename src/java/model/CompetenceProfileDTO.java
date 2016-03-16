/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;

/**
 *
 * @author Anton
 */
public interface CompetenceProfileDTO {
    Competence getCompetence();
    int getId();
    int getExperience();
    void setCompetence(Competence competence);
    void setId(int id);
    void setExperience(int experience);
    void setPerson(Person person);
    Person getPerson();
}
