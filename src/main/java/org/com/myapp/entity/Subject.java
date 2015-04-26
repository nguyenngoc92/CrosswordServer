package org.com.myapp.entity;
// Generated Apr 25, 2015 9:03:17 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Subject generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name="subject"
    ,catalog="crossworddb"
)
public class Subject  implements java.io.Serializable {


     private Integer idSubject;
     private String name;
     private Date createDate;
     private Set<Match> matches = new HashSet<Match>(0);

    public Subject() {
    }

	
    public Subject(String name) {
        this.name = name;
    }
    public Subject(String name, Date createDate, Set<Match> matches) {
       this.name = name;
       this.createDate = createDate;
       this.matches = matches;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="IdSubject", unique=true, nullable=false)
    public Integer getIdSubject() {
        return this.idSubject;
    }
    
    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

	@NotEmpty
    @Column(name="Name", nullable=false, length=128)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreateDate", length=19)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="subject")
    public Set<Match> getMatches() {
        return this.matches;
    }
    
    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }




}


