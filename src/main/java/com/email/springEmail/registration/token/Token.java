package com.email.springEmail.registration.token;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.email.springEmail.appuser.User;

@Entity
@Table(name="token")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String token ;
	
	private LocalDateTime confirmDate;
	
	private LocalDateTime expiredTime;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn( name = "app_user_id" , referencedColumnName="Id" )
	private User appuser;

	
	
	public Token() {
		super();
	}

	public Token(String token, LocalDateTime confirmDate, LocalDateTime expiredTime , User appuser) {
		super();
		this.token = token;
		this.confirmDate = confirmDate;
		this.expiredTime = expiredTime;
		this.appuser =appuser;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(LocalDateTime confirmDate) {
		this.confirmDate = confirmDate;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

	public User getAppuser() {
		return appuser;
	}

	public void setAppuser(User appuser) {
		this.appuser = appuser;
	}
	
	

}
