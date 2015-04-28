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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Match generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "match", catalog = "crossworddb")
public class Match implements java.io.Serializable {

	private Integer idMatch;
	private Subject subject;
	private Competition competition;
	private String title;
	private Date createDate;
	private Set<Item> items = new HashSet<Item>(0);
	private Set<Score> scores = new HashSet<Score>(0);

	public Match() {
	}

	public Match(Subject subject, Competition competition, String title,
			Date createDate, Set<Item> items, Set<Score> scores) {
		this.subject = subject;
		this.competition = competition;
		this.title = title;
		this.createDate = createDate;
		this.items = items;
		this.scores = scores;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdMatch", unique = true, nullable = false)
	public Integer getIdMatch() {
		return this.idMatch;
	}

	public void setIdMatch(Integer idMatch) {
		this.idMatch = idMatch;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdSubject")
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdCompetition")
	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, mappedBy = "matches")
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
	public Set<Score> getScores() {
		return this.scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

}
