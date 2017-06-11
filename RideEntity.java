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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author donghaiying
 */
@Entity
public class RideEntity implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Temporal (value = TemporalType.DATE)
    private Date startDate;
    @Temporal (value = TemporalType.DATE)
    private Date endDate;
    private int startLatitude;
    private int startLongtitude;
    private int endLatitude;
    private int endLongtitude;
    private double fee;
    @OneToOne(cascade={CascadeType.PERSIST})
    private PaymentEntity payment;
    @ManyToOne
    private UsersEntity user = new UsersEntity();
    @OneToOne(cascade={CascadeType.PERSIST})
    private FeedbackEntity feedback;
    @ManyToOne
    private DriverEntity driver = new DriverEntity();
    
    
    
    
    public void create (Date start, int startLatitude, int startLongtitude) {
        /*this.setEndDate(endDate);
        this.setEndLatitude(endLatitude);
        this.setEndLongtitude(endLongtitude);
        this.setFee(fee);*/
        this.setStartDate(startDate);
        this.setStartLatitude(startLatitude);
        this.setStartLongtitude(startLongtitude);
    }
    
    public RideEntity() {
        setId(System.nanoTime());
    }

    /*public static long getSerialVersionUID() {
        return serialVersionUID;
    }*/


    public int getStartLatitude() {
        return startLatitude;
    }

    public int getStartLongtitude() {
        return startLongtitude;
    }

    public int getEndLatitude() {
        return endLatitude;
    }

    public int getEndLongtitude() {
        return endLongtitude;
    }

    public double getFee() {
        return fee;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public UsersEntity getUser() {
        return user;
    }

    public FeedbackEntity getFeedback() {
        return feedback;
    }

    public DriverEntity getDriver() {
        return driver;
    }

   
    public void setStartLatitude(int startLatitude) {
        this.startLatitude = startLatitude;
    }

    public void setStartLongtitude(int startLongtitude) {
        this.startLongtitude = startLongtitude;
    }

    public void setEndLatitude(int endLatitude) {
        this.endLatitude = endLatitude;
    }

    public void setEndLongtitude(int endLongtitude) {
        this.endLongtitude = endLongtitude;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public void setFeedback(FeedbackEntity feedback) {
        this.feedback = feedback;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        if (!(object instanceof RideEntity)) {
            return false;
        }
        RideEntity other = (RideEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.RideEntity[ id=" + id + " ]";
    }
    
}
