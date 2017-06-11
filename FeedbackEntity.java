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
public class FeedbackEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    private int ratings;
    private String comment;
    
    public void create(Date date, int ratings, String comment) {
        this.setComment(comment);
        this.setDate(date);
        this.setRatings(ratings);
    }
    
    public FeedbackEntity() {
        setId(System.nanoTime());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDate() {
        return date;
    }

    public int getRatings() {
        return ratings;
    }

    public String getComment() {
        return comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(object instanceof FeedbackEntity)) {
            return false;
        }
        FeedbackEntity other = (FeedbackEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.FeedbackEntity[ id=" + id + " ]";
    }
    
}
