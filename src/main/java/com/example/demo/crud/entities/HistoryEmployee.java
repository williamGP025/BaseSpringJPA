package com.example.demo.crud.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history_employees")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEmployee {
    // #region [Columns table]
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 250, nullable = false)
    @NotBlank(message = "El campo debe tener datos")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createRow")
    private Date create;
    // #endregion

    // #region [relaciones]
    @Valid
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rel_history_employees", joinColumns = {
            @JoinColumn(nullable = false, name = "fk_history_id") }, inverseJoinColumns = {
                    @JoinColumn(nullable = false, name = "fk_employee_id") })
    @JsonBackReference
    private List<Employee> employees;
    // #endregion

    // #region [Atributos calculados]
    @Transient
    private String message;
    // #endregion

    @PrePersist
    public void prePersist() {
        this.create = new Date();
    }

    public String getMessage() {
        return "ejemplo de metodo calculado";
    }
}