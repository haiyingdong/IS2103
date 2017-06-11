/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author donghaiying
 */
@Entity
public class DriverEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String contact;
    private String address;
    private int latitude;
    private int longtitude;
    @OneToOne(mappedBy="driver")
    private CarEntity car;
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="driver")
    private Collection <RideEntity> rides = new ArrayList<RideEntity>();
    
    public void create(String id, String name, String contact, String address, int longtitude, int latitude) {
        this.setId(id);
        this.setName(name);
        this.setContact(contact);
        this.setAddress(address);
        this.setLatitude(latitude);
        this.setLongtitude(longtitude);
    }
    
    public DriverEntity() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongtitude() {
        return longtitude;
    }

    public CarEntity getCar() {
        return car;
    }

    public Collection<RideEntity> getRides() {
        return rides;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(int longtitude) {
        this.longtitude = longtitude;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public void setRides(Collection<RideEntity> rides) {
        this.rides = rides;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DriverEntity)) {
            return false;
        }
        DriverEntity other = (DriverEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DriverEntity[ id=" + id + " ]";
    }
    
}
