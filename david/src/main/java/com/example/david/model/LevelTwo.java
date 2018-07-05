package com.example.david.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "level_two")
public class LevelTwo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEVEL_2")
    private Integer idLevel2;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "AMOUNT")
    private int amount;
    
    @Lob
    @Column(name = "URL")
    private String url;
    
    @Column(name = "STATUS")
    private String status;
    
    @JoinColumn(name = "ID_LEVEL_1", referencedColumnName = "ID_LEVEL_1", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private LevelOne levelOne;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "levelTwo", fetch = FetchType.LAZY)
    private List<LevelThree> levelThreeList;

    public LevelTwo() {
    }

    public LevelTwo(Integer idLevel2) {
        this.idLevel2 = idLevel2;
    }

    public LevelTwo(String name, int amount, String url, String status, Integer idLevel1) {
    	this.name = name;
        this.amount = amount;
        this.url = url;
        this.status = status;
        this.levelOne = new LevelOne();
        this.levelOne.setIdLevel1(idLevel1);
    }

    public Integer getIdLevel2() {
        return idLevel2;
    }

    public void setIdLevel2(Integer idLevel2) {
        this.idLevel2 = idLevel2;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public LevelOne getLevelOne() {
		return levelOne;
	}

	public void setLevelOne(LevelOne levelOne) {
		this.levelOne = levelOne;
	}

	public List<LevelThree> getLevelThreeList() {
		return levelThreeList;
	}

	public void setLevelThreeList(List<LevelThree> levelThreeList) {
		this.levelThreeList = levelThreeList;
	}
}
