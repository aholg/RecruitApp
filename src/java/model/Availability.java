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
 * This is an entity class responsible for containing a availability period for a profile.
 */
@Entity
public class Availability implements Serializable,AvailabilityDTO {
    private static final long serialVersionUID = 1L;
    //Date availability;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /**
     *  Creates a new availability object.
     */
    public Availability(){
        
    }

    /**
     * Creates a new availability object with given constructor parameters.
     * @param startDate     A string containing the start date of the period.
     * @param endDate       A string containing the end date of the period.
     */
    public Availability(String startDate,String endDate){
       // availability=new Date();
       // availability.
    }
    
    /**
     * A get method for retrieving the id of this entity.
     * @return  An int containing the id.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Set method for setting the id of the entity.
     * @param id    An int containing the id.
     */
    public void setId(int id) {
        this.id = id;
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
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + id + " ]";
    }

    /**
     * Get method for getting the start date.
     * @return  A string containing the start date.
     */
    @Override
    public String getStartDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get method for getting the end date.
     * @return  Method for getting the end date.
     */
    @Override
    public String getEndDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
}
