
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * A entity class responsible for containing data related to the competence profile table.
 */
@Entity
public class CompetenceProfile implements CompetenceProfileDTO, Serializable {

    private static final long serialVersionUID = 3L;
    int experience;

    @OneToOne(cascade = CascadeType.MERGE)
    Person person;
    @OneToOne(cascade = CascadeType.REFRESH)
    Competence competence;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    /**
     * Creates a new competenceProfile object.
     */
    public CompetenceProfile() {

    }

    /**
     * Creates a new competence profile object with given values.
     * @param experience    An int containing years of experience.
     * @param competence    A competence object that describes a competence.
     * @param person        A person object that contains information about a person. 
     */
    public CompetenceProfile(int experience, Competence competence,Person person) {
        //this.id =id;
        this.experience = experience;
        this.competence = competence;
        this.person=person;
    }
    
    /**
     * Set method to set the person object to be associated with this entity.
     * 
     * @param person    Person object to be set.
     */
    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Get method for returning the person for this entity.
     * @return  Person object associated with this entity.
     */
    @Override
    public Person getPerson() {
        return person;
    }

    /**
     * Get method for returning the competence associated with this profile.
     * @return Competence object containing a description of a competence.
     */
    @Override
    public Competence getCompetence() {
        return competence;
    }
    
    /**
     * Get method for returning the id of the competence profile.
     * @return Int id associated with this entity.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Set method for setting the years of experience for the profile.
     * @param experience int containing the years of experience.
     */
    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Set method for setting a competence to be associated with this entity.
     * @param competences   Competence object to be associated with this entity.
     */
    @Override
    public void setCompetence(Competence competences) {
        this.competence = competences;
    }
    /**
     * A set method to set the id of the person.
     * @param id    An int containing the id of the person. 
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Get method for getting the years of experience for the profile.
     * @return  An int containing years of experience of the profile.
     */
    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceProfile)) {
            return false;
        }
        CompetenceProfile other = (CompetenceProfile) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "model.Availability[ id=" + id + " ]";
    }
}
