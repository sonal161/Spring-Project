package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_TABLE")
public class User {

@Column(name="ID")
private Long id;
@Column(name="NAME")
private String name;
@Column(name="EMAIL")
private String email;
@Column(name="USERNAME")
@Id
private String user1;
@Column(name="PASSWORD")
private String password;
public User() {
	
}
public User(Long id, String name, String email, String user1, String password) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.user1 = user1;
	this.password = password;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUser1() {
	return user1;
}
public void setUser1(String user1) {
	this.user1 = user1;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public void userDetails() {
	System.out.println("NAME::"+name);
	System.out.println("EMAIL::"+email);
	System.out.println("USERNAME::"+user1);
	System.out.println("PASSWORD::"+password);
	
}














}
