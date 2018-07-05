package com.example.david.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "level_four")
public class LevelFour implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEVEL_4")
    private Integer idLevel4;
    
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "PRICE_USD")
    private String priceUsd;

    @Column(name = "PRICE_COP")
    private String priceCop;

    @Lob
    @Column(name = "ATTACHMENTS")
    private String attachments;
    
    @Column(name = "STATUS")
    private String status;
    
    @JoinColumn(name = "ID_LEVEL_3", referencedColumnName = "ID_LEVEL_3", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private LevelThree levelThree;

	public LevelFour() {
	}
	
	public LevelFour(Integer idLevel4) {
		this.idLevel4 = idLevel4;
	}

	public LevelFour(String description, String priceUsd, String priceCop, String attachments, String status, Integer idLevel3) {
		this.description = description;
		this.priceUsd = priceUsd;
		this.priceCop = priceCop;
		this.attachments = attachments;
		this.status = status;
		this.levelThree = new LevelThree();
        this.levelThree.setIdLevel3(idLevel3);
	}

	public Integer getIdLevel4() {
		return idLevel4;
	}

	public void setIdLevel4(Integer idLevel4) {
		this.idLevel4 = idLevel4;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriceUsd() {
		return priceUsd;
	}

	public void setPriceUsd(String priceUsd) {
		this.priceUsd = priceUsd;
	}

	public String getPriceCop() {
		return priceCop;
	}

	public void setPriceCop(String priceCop) {
		this.priceCop = priceCop;
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

	public LevelThree getLevelThree() {
		return levelThree;
	}

	public void setLevelThree(LevelThree levelThree) {
		this.levelThree = levelThree;
	}
}
