package org.com.myapp.entity;
// Generated Apr 25, 2015 9:03:17 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name="role"
    ,catalog="crossworddb"
)
public class Role  implements java.io.Serializable {


     private Integer idRole;
     private String roleName;
     private Set<User> users = new HashSet<User>(0);

    public Role() {
    }

    public Role(String roleName, Set<User> users) {
       this.roleName = roleName;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="IdRole", unique=true, nullable=false)
    public Integer getIdRole() {
        return this.idRole;
    }
    
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    
    @Column(name="RoleName", length=45)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

@ManyToMany(fetch=FetchType.LAZY, mappedBy="roles")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}


