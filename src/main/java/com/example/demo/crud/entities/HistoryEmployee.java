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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "history_employees")
@Data
@Builder
@AllArgsConstructor
public class HistoryEmployee {
    // #region [Columns table]
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 250)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createRow")
    private Date create;
    // #endregion

    // #region [relaciones]
    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rel_history_employees", joinColumns = @JoinColumn(name = "fk_history_employee_id", nullable = false, table = "history_employees"), inverseJoinColumns = @JoinColumn(nullable = false, name = "fk_employee_id", table = "employees"))
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