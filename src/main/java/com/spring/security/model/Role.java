package com.spring.security.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_details")
public class Role implements Serializable {

	@Id
	@SequenceGenerator(name = "role_sequence", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "role_sequence")
	private Integer roleId;
	private String role;

}
