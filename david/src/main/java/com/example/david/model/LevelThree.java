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
@Table(name = "level_three")
public class LevelThree implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEVEL_3")
    private Integer idLevel3;
    
    @Column(name = "NAME")
    private String name;
    
    @Lob
    @Column(name = "URL")
    private String url;
    
    @Column(name = "STATUS")
    private String status;
    
    @JoinColumn(name = "ID_LEVEL_2", referencedColumnName = "ID_LEVEL_2", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private LevelTwo levelTwo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "levelThree", fetch = FetchType.LAZY)
    private List<LevelFour> levelFourList;

    public LevelThree() {
    }

    public LevelThree(Integer idLevel3) {
        this.idLevel3 = idLevel3;
    }

    public LevelThree(String name, String url, String status, Integer idLevel2) {
    	this.name = name;
    	this.url = url;
        this.status = status;
        this.levelTwo = new LevelTwo();
        this.levelTwo.setIdLevel2(idLevel2);
    }

    public Integer getIdLevel3() {
        return idLevel3;
    }

    public void setIdLevel3(Integer idLevel3) {
        this.idLevel3 = idLevel3;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LevelTwo getLevelTwo() {
		return levelTwo;
	}

	public void setLevelTwo(LevelTwo levelTwo) {
		this.levelTwo = levelTwo;
	}

	public List<LevelFour> getLevelFourList() {
		return levelFourList;
	}

	public void setLevelFourList(List<LevelFour> levelFourList) {
		this.levelFourList = levelFourList;
	}
}
