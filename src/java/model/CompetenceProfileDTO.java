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
    List<Competence> getCompetences();
    int getPersonID();
    int getYearsOfExperience();
    void addCompetence(Competence competence);
}
