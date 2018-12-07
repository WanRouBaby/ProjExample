package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class CustomerBean {
	@Id
	@Column(name = "custid")
	private String custid;
	@Column(name = "password")
	private byte[] password;
	@Column(name = "email")
	private String email;
	@Column(name = "birth")
	private java.util.Date birth;

	@Override
	public String toString() {
		return "model.CustomerBean [" + custid + "," + email + "," + birth + "]";
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getBirth() {
		return birth;
	}

	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}
}
