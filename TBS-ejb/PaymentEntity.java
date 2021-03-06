/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 *
 * @author donghaiying
 */
@Entity
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Temporal (value = TemporalType.DATE)
    private Date date;
    private String type;
    private long number;
    private String name;
    
    public PaymentEntity() {
        setId(System.nanoTime());
    }
    
    public void create(Date date, String type, long number, String name) {
        this.setDate(date);
        this.setName(name);
        this.setNumber(number);
        this.setType(type);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PaymentEntity)) {
            return false;
        }
        PaymentEntity other = (PaymentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.PaymentEntity[ id=" + id + " ]";
    }
    
}
