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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user", catalog = "crossworddb")
public class User implements java.io.Serializable {

	private Integer idUser;
	private String userName;
	private String email;
	private Set<Score> scores = new HashSet<Score>(0);
	private Set<Role> roles = new HashSet<Role>(0);

	public User() {
	}

	public User(String userName, String email) {
		this.userName = userName;
		this.email = email;
	}

	public User(String userName, String email, Set<Score> scores,
			Set<Role> roles) {
		this.userName = userName;
		this.email = email;
		this.scores = scores;
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdUser", unique = true, nullable = false)
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Column(name = "UserName", nullable = false, length = 128)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "Email", nullable = false, length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Score> getScores() {
		return this.scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE })
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinTable(name = "usersinroles", catalog = "crossworddb", joinColumns = { @JoinColumn(name = "IdUser", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "IdRole", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
