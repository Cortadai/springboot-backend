package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data							// Used by Lombok to generate getter/setters
@Entity							// Specifies that the class is an entity in our db
@Table(name="employees")		// Specifies the table in the db with which this entity is mapped
public class Employee {
	
	@Id							// Identifies this field as Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
}
