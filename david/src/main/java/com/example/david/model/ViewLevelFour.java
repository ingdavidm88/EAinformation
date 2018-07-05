package com.example.david.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_level_four")
public class ViewLevelFour implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_LEVEL_4")
    private Integer idLevel4;
    
    @Column(name = "ATTACHMENTS")
    private String attachments;
    
    @Column(name = "STATUS")
    private String status;
    
    public Integer getIdLevel4() {
        return idLevel4;
    }

    public void setIdLevel4(Integer idLevel4) {
        this.idLevel4 = idLevel4;
    }
    
    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
