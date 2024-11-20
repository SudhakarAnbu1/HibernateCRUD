package com.learn.crud.HibernateCRUD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.bytebuddy.build.HashCodeAndEqualsPlugin.ValueHandling;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "s_roll")})
public class Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int s_id;
	Long s_roll;
	String s_name;
	String s_email;
	Long s_phone;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int s_id, Long s_roll, String s_name, String s_email, Long s_phone) {
		super();
		this.s_id = s_id;
		this.s_roll = s_roll;
		this.s_name = s_name;
		this.s_email = s_email;
		this.s_phone = s_phone;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public Long getS_roll() {
		return s_roll;
	}
	public void setS_roll(Long s_roll) {
		this.s_roll = s_roll;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	public Long getS_phone() {
		return s_phone;
	}
	public void setS_phone(Long s_phone) {
		this.s_phone = s_phone;
	}
	@Override
	public String toString() {
		return "Student [s_id=" + s_id + ", s_roll=" + s_roll + ", s_name=" + s_name + ", s_email=" + s_email
				+ ", s_phone=" + s_phone + "]";
	}

}
