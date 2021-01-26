package com.example.demo.crud.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name", nullable = false, length = 150)
	@Size(min = 3, max = 150, message = "Logitud de cadena incorrecta debe estan entre 3 y 150 caracteres")
	@NotBlank(message = "El campo nombre es obligatorio")
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 150)
	@NotBlank(message = "El campo apellidos es obligatorio")
	private String lastName;
	@Column(name = "email_address", nullable = false, length = 150)
	@NotBlank(message = "Debe especificar un correo")
	@Email(message = "Formato de correo no valido")
	private String emailId;

	// #region [Relaciones]
	@ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HistoryEmployee> historyEmployees;

	// #endregion
	public void addHistoryEmployee(HistoryEmployee trace) {
		if (historyEmployees == null) {
			historyEmployees = new ArrayList<>();
		}
		this.historyEmployees.add(trace);
	}
}