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

/**
 *
 * @author donghaiying
 */
@Entity(name="Users")
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String userName;
    private String password;
    private String contact;
    private String email;
    private String address;
    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<PaymentEntity> payments = new ArrayList<PaymentEntity>();
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="user")
    private Collection<RideEntity> rides = new ArrayList<RideEntity>();
    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<FeedbackEntity> feedbacks = new ArrayList<FeedbackEntity>();
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="user")
    private Collection<MessageEntity> messages = new ArrayList<MessageEntity>();
    
    public void create(String userName, String password, String contact, String email, String address) {
        this.userName = userName;
        this.password = password;
        this.contact = contact;
        this.email = email;
        this.address = address;
    }

    
    public Collection<PaymentEntity> getPayments() {
        return payments;
    }
    
    public Collection<RideEntity> getRides() {
        return rides;
    }
    
    public Collection<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }
  
    public Collection<MessageEntity> getMessages() {
        return messages;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {    
        return address;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMessages(Collection<MessageEntity> messages) {
        this.messages = messages;
    }

    public void setPayments(Collection<PaymentEntity> payments) {
        this.payments = payments;
    }



    public void setRides(Collection<RideEntity> rides) {
        this.rides = rides;
    }
    
  
    public void setFeedbacks(Collection<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersEntity)) {
            return false;
        }
        UsersEntity other = (UsersEntity) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.UserEntity[ id=" + userName + " ]";
    }
    
}
