	package com.cg.ppa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@NotNull(message = "Name cannot be empty")
	@Column(name = "user_name")
	private String userName;
	@NotNull(message = "Role cannot be null")
	@Column(name = "user_role")
	private String role;
	@NotNull(message = "Contact number cannot be empty")
	@Pattern(regexp = "^\\d{10}$", message = "Enter valid contact number")
	@Column(name = "user_contact")
	private String contactNumber;
	@NotNull(message = "Email Id cannot be empty")
	@Email(message = "Email Id should be valid")
	@Column(name = "user_emailid")
	private String emailId;
	@NotNull(message = "Password should not be empty")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password should contain minimum 8 characters, atleast one digit, uppecase, lower case and special character")
	@Column(name = "user_password")
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String role, String contactNumber, String emailId, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.password = password;
	}
}
