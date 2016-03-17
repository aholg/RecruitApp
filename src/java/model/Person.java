
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 *
 * An entity class responsible for containing data related to a person.
 */
@Entity
public class Person implements PersonDTO, Serializable {
    @Size(min=1)
    private String name;
    @Pattern(regexp = "\\d{4}\\/(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])-\\d{4}")
    private String ssn;
    @Pattern(regexp = "[a-z0-9]+@[a-z0-9]+\\.(com|net|se)")
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    /**
     *  Creates a new person object.
     */
    public Person(){
        
    }

    /**
     * Get method to retrieve the name of the person.
     * @return  A string containing the person name.
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     *  Get method to retrieve the social security number of the person.
     * @return  A string containing the social security number of the person.
     */
    @Override
    public String getSSN() {
        return ssn;
    }

    /**
     * Get method to retrieve the email of the person.
     * @return  A string containing the email of the person.
     */
    @Override
    public String getEmail() {
        return email;
    }
    
    /**
     * A set method to set the name of the person.  
     * @param name  A string containing the name of the person.
     */
    @Override
    public void setName(String name) {
        this.name=name;
    }

    /**
     *  A set method to set the social security number of the person.
     * @param ssn   String containing the social security number of the person.
     */
    @Override
    public void setSSN(String ssn) {
       this.ssn=ssn;
    }

    /**
     * A set method to set the email of the person.
     * @param email A string containing the email of the person.
     */
    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
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
     * Get method to get the id of the person.
     * @return  An int containing the id of the person.
     */
    @Override
    public int getId() {
       return id;
    }

    /**
     * A set method to set the id of the person.
     * @param id    An int containing the id of the person. 
     */
    @Override
    public void setId(int id) {
        this.id=id;
    }
   
}


