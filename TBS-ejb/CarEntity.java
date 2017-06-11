/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author donghaiying
 */
@Entity
public class CarEntity implements Serializable {

    /**
     * @return the myear
     */
    public String getMyear() {
        return myear;
    }

    /**
     * @param myear the myear to set
     */
    public void setMyear(String myear) {
        this.myear = myear;
    }

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String brand;
    private String model;
    private String myear;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private DriverEntity driver;
    
    public void create(String id, String brand, String model, String year) {
        this.setId(id);
        this.setBrand(brand);
        this.setModel(model);
        this.setYear(year);
    }
    public CarEntity() {
 
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String myear) {
        this.setMyear(myear);
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    /*public static long getSerialVersionUID() {
        return serialVersionUID;
    }*/

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return getMyear();
    }

    public DriverEntity getDriver() {
        return driver;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarEntity)) {
            return false;
        }
        CarEntity other = (CarEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CarEntity[ id=" + getId() + " ]";
    }
    
}
