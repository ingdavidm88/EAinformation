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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_detail")
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER_DETAIL", nullable = false)
    private Integer idUserDetail;
    
    @Column(name = "FULL_NAME", nullable = false, length = 50)
    private String fullName;
    
    @Transient
    private String userName;
    
    @Column(name = "BIRTH", nullable = false)
    private String birth;
    
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;
    
    @Transient
    private String password;
    
    @Transient
    private String repeatPassword;
    
    @Transient
    private String privacyPolicy;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetail", fetch = FetchType.LAZY)
    private List<User> userList;

    public UserDetail() {
    }

    public UserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    public UserDetail(Integer idUserDetail, String fullName, String birth, String email) {
        this.idUserDetail = idUserDetail;
        this.fullName = fullName;
        this.birth = birth;
        this.email = email;
    }

    public Integer getIdUserDetail() {
        return idUserDetail;
    }

    public void setIdUserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
    	this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getPrivacyPolicy() {
		return privacyPolicy;
	}

	public void setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}

	public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }   
}