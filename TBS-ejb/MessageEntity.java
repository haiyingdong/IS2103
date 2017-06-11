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

/**
 *
 * @author donghaiying
 */
@Entity
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long messageId;
    @Temporal (value = TemporalType.DATE)
    private Date date;
    private String content;
    private String status;
    private String comment; 
    @ManyToOne
    private UsersEntity user = new UsersEntity();
    
    public void create(Date date, String content) {
        //this.setComment(comment);
        this.setContent(content);
        this.setDate(date);
        //this.setStatus(status);
    }
    
    public MessageEntity() {
        setMessageId(System.nanoTime());
    } 

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setMessageId(Long MessageId) {
        this.messageId = MessageId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEntity)) {
            return false;
        }
        MessageEntity other = (MessageEntity) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.MessageEntity[ id=" + messageId + " ]";
    }
    
}
