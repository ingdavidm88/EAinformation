package com.example.david.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENU", nullable = false)
    private Integer idMenu;
    
    @Column(name = "MENU_NAME", nullable = false, length = 45)
    private String menuName;
    
    @Column(name = "URL_NAME", nullable = false, length = 45)
    private String urlName;
    
    @Column(name = "ORDER", nullable = false)
    private int order;
    
    @JoinColumn(name = "ID_PARENT_MENU", referencedColumnName = "ID_PARENT_MENU", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ParentMenu parentMenu;
    
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    public Menu() {
    }

    public Menu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(Integer idMenu, String menuName, String urlName, int order) {
        this.idMenu = idMenu;
        this.menuName = menuName;
        this.urlName = urlName;
        this.order = order;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ParentMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(ParentMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Role getRole() {
        return role;
    }

    public void setIdRole(Role role) {
        this.role = role;
    }   
}